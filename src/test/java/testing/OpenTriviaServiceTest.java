package testing;

import org.junit.Test;
import static org.junit.Assert.*;

import service.OpenTriviaService;
import exception.OpenTriviaException;
import model.Category;
import model.DataQuestion;

import java.util.List;

/**
 * Unit tests για την κλάση {@link OpenTriviaService}.
 *
 * Στόχος:
 * - Να ελεγχθεί ότι οι κλήσεις προς το Open Trivia DB API επιστρέφουν
 *   σωστά δεδομένα (λίστα κατηγοριών και ερωτήσεων).
 * - Να διασφαλιστεί ότι τα βασικά πεδία των αντικειμένων που δημιουργούνται
 *   δεν είναι null και περιέχουν έγκυρες τιμές.
 *
 * Σημείωση: Τα tests αυτά εκτελούν πραγματικές κλήσεις στο εξωτερικό API,
 * οπότε απαιτείται ενεργή σύνδεση στο διαδίκτυο και ενδέχεται να αποτύχουν
 * αν το API είναι προσωρινά μη διαθέσιμο.
 */
public class OpenTriviaServiceTest {

    /**
     * Έλεγχος ότι η μέθοδος getCategories() επιστρέφει μη κενή λίστα.
     */
    @Test
    public void testGetCategories() throws OpenTriviaException {
        OpenTriviaService service = new OpenTriviaService();
        List<Category> categories = service.getCategories();

        assertNotNull("Categories list is null", categories);
        assertFalse("Categories list is empty", categories.isEmpty());

        // Προαιρετική εκτύπωση για οπτικό έλεγχο:
        // categories.forEach(c -> System.out.println(c.getId() + " - " + c.getName()));
    }

    /**
     * Έλεγχος ότι η μέθοδος fetchQuestions() επιστρέφει σωστή λίστα ερωτήσεων.
     * Χρησιμοποιούμε amount=5, difficulty="easy", type="multiple".
     */
    @Test
    public void testFetchQuestions() throws OpenTriviaException {
        OpenTriviaService service = new OpenTriviaService();
        List<DataQuestion> questions = service.fetchQuestions(5, null, "easy", "multiple");

        assertNotNull("Questions list is null", questions);
        assertFalse("Questions list is empty", questions.isEmpty());

        // Έλεγχος ότι τα βασικά πεδία της πρώτης ερώτησης είναι μη κενά
        DataQuestion q = questions.get(0);
        assertNotNull("Question text is null", q.getQuestion());
        assertNotNull("Correct answer is null", q.getCorrectAnswer());
        assertNotNull("Incorrect answers list is null", q.getIncorrectAnswers());
    }
}
