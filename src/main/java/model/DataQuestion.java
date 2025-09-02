package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * POJO κλάση που αναπαριστά μία ερώτηση όπως επιστρέφεται από το Open Trivia DB API.
 *
 * Κάθε ερώτηση περιλαμβάνει:
 * - την κατηγορία (π.χ. "General Knowledge"),
 * - τον τύπο ("multiple" ή "boolean"),
 * - το επίπεδο δυσκολίας ("easy", "medium", "hard"),
 * - το κείμενο της ερώτησης,
 * - τη σωστή απάντηση,
 * - καθώς και τις λανθασμένες απαντήσεις (για ερωτήσεις πολλαπλής επιλογής).
 *
 * Η κλάση αυτή χρησιμοποιείται για την αποσειριοποίηση (deserialization) του JSON response
 * σε Java αντικείμενα, ώστε να είναι εύκολη η αξιοποίησή τους μέσα στην εφαρμογή.
 */
public class DataQuestion {

    /** Κατηγορία της ερώτησης (π.χ. "General Knowledge"). */
    private String category;

    /** Τύπος της ερώτησης: "multiple" (πολλαπλής επιλογής) ή "boolean" (Σωστό/Λάθος). */
    private String type;

    /** Βαθμός δυσκολίας της ερώτησης: "easy", "medium" ή "hard". */
    private String difficulty;

    /** Το κείμενο της ερώτησης όπως επιστρέφεται από το API. */
    private String question;

    /** Η σωστή απάντηση στην ερώτηση. */
    @JsonProperty("correct_answer")
    private String correctAnswer;

    /** Οι λανθασμένες απαντήσεις (για πολλαπλής επιλογής). */
    @JsonProperty("incorrect_answers")
    private List<String> incorrectAnswers;

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }

    public List<String> getIncorrectAnswers() { return incorrectAnswers; }
    public void setIncorrectAnswers(List<String> incorrectAnswers) { this.incorrectAnswers = incorrectAnswers; }
}
