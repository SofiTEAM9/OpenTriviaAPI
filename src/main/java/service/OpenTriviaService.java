package service;

import exception.OpenTriviaException;
import model.CategoriesResponse;
import model.Category;
import model.DataQuestion;
import model.DataResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Υπηρεσία πρόσβασης στο Open Trivia DB API.
 *
 * Ρόλος:
 * - Δημιουργεί HTTP αιτήματα με τα κριτήρια (amount, category, difficulty, type).
 * - Διαβάζει το JSON response και το αποσειριοποιεί σε POJOs (DataResponse, DataQuestion).
 * - Επιστρέφει καθαρά domain objects ή ρίχνει ελεγχόμενη εξαίρεση {@link OpenTriviaException}
 *   ώστε τα ανώτερα επίπεδα (UI/Controller) να χειρίζονται ενιαία τα σφάλματα.
 */
public class OpenTriviaService {

    /** Βασικό endpoint του Open Trivia DB. */
    private static final String BASE = "https://opentdb.com";

    /** HTTP client της Java 11 για τις κλήσεις προς το API. */
    private final HttpClient http = HttpClient.newHttpClient();

    /** Jackson ObjectMapper για αποσειριοποίηση JSON -> POJOs. */
    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    /**
     * Ανακτά λίστα ερωτήσεων βάσει κριτηρίων.
     *
     * @param amount     πλήθος ερωτήσεων (1..50 όπως ορίζει το API)
     * @param categoryId id κατηγορίας ή {@code null} για όλες
     * @param difficulty "easy" | "medium" | "hard" ή {@code null}
     * @param type       "multiple" | "boolean" ή {@code null}
     * @return λίστα ερωτήσεων σε μορφή {@link DataQuestion}
     * @throws OpenTriviaException αν υπάρξει σφάλμα HTTP, parsing ή μη-μηδενικός response_code
     */
    public List<DataQuestion> fetchQuestions(int amount,
                                             Integer categoryId,
                                             String difficulty,
                                             String type) throws OpenTriviaException {
        // Έλεγχος business κανόνα του API για το εύρος του amount
        if (amount < 1 || amount > 50) {
            throw new OpenTriviaException("Amount must be between 1 and 50");
        }

        // Σύνθεση URL με προαιρετικά query params
        StringBuilder url = new StringBuilder(BASE + "/api.php?amount=" + amount);
        if (categoryId != null) url.append("&category=").append(categoryId);
        if (difficulty != null && !difficulty.isBlank()) url.append("&difficulty=").append(difficulty);
        if (type != null && !type.isBlank()) url.append("&type=").append(type);

        HttpRequest req = HttpRequest.newBuilder(URI.create(url.toString()))
                .header("Accept-Charset", StandardCharsets.UTF_8.name())
                .GET()
                .build();

        try {
            HttpResponse<String> resp = http.send(req, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

            // Βασικός έλεγχος HTTP (2xx αναμένονται)
            if (resp.statusCode() / 100 != 2) {
                throw new OpenTriviaException("HTTP error: " + resp.statusCode());
            }

            // JSON -> POJO
            DataResponse dr = mapper.readValue(resp.body(), DataResponse.class);

            // Έλεγχος κωδικού απόκρισης του API (0 = επιτυχία)
            if (dr.getResponseCode() != 0) {
                throw new OpenTriviaException("API error code: " + dr.getResponseCode());
            }

            return dr.getResults();
        } catch (IOException | InterruptedException e) {
            // Ενοποίηση τεχνικών σφαλμάτων κάτω από μία ελεγχόμενη εξαίρεση domain
            throw new OpenTriviaException("Failed to call OpenTDB: " + e.getMessage(), e);
        }
    }

    /**
     * Επιστρέφει τις διαθέσιμες κατηγορίες ερωτήσεων από το API.
     *
     * @return λίστα {@link Category}
     * @throws OpenTriviaException αν αποτύχει η κλήση ή η αποσειριοποίηση
     */
    public List<Category> getCategories() throws OpenTriviaException {
        String url = BASE + "/api_category.php";

        HttpRequest req = HttpRequest.newBuilder(URI.create(url))
                .header("Accept-Charset", StandardCharsets.UTF_8.name())
                .GET()
                .build();

        try {
            HttpResponse<String> resp = http.send(req, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

            // Βασικός έλεγχος HTTP (2xx αναμένονται)
            if (resp.statusCode() / 100 != 2) {
                throw new OpenTriviaException("HTTP error: " + resp.statusCode());
            }

            // JSON -> POJO
            CategoriesResponse cr = mapper.readValue(resp.body(), CategoriesResponse.class);
            return cr.getTriviaCategories();
        } catch (IOException | InterruptedException e) {
            throw new OpenTriviaException("Failed to get categories: " + e.getMessage(), e);
        }
    }
}
