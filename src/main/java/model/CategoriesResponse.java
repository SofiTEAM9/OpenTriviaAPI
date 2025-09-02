package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * POJO κλάση που αναπαριστά το JSON response από το endpoint του Open Trivia DB
 * για την ανάκτηση των διαθέσιμων κατηγοριών ερωτήσεων.
 *
 * Το API επιστρέφει ένα αντικείμενο με property "trivia_categories",
 * το οποίο αντιστοιχίζεται σε λίστα αντικειμένων {@link Category}.
 *
 * Η κλάση αυτή χρησιμοποιείται αποκλειστικά για την αποσειριοποίηση
 * (deserialization) του JSON σε Java αντικείμενα, ώστε να είναι εύκολη η
 * διαχείριση των δεδομένων μέσα στη βιβλιοθήκη.
 */
public class CategoriesResponse {

    /** Λίστα κατηγοριών όπως επιστρέφεται από το API. */
    @JsonProperty("trivia_categories")
    private List<Category> triviaCategories;

    public List<Category> getTriviaCategories() {
        return triviaCategories;
    }

    public void setTriviaCategories(List<Category> triviaCategories) {
        this.triviaCategories = triviaCategories;
    }
}
