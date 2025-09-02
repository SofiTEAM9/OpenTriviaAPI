package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * POJO κλάση που αναπαριστά το συνολικό JSON response του Open Trivia DB
 * όταν ζητάμε ερωτήσεις με συγκεκριμένα κριτήρια.
 *
 * Το response περιέχει:
 * - έναν κωδικό κατάστασης (response_code) που δείχνει αν το αίτημα εκτελέστηκε σωστά,
 * - τη λίστα με τις ερωτήσεις ({@link DataQuestion}).
 *
 * Χρησιμοποιείται για την αποσειριοποίηση (deserialization) του JSON σε Java αντικείμενα,
 * ώστε η βιβλιοθήκη να μπορεί να ελέγχει τον κωδικό απόκρισης και να παραδίδει
 * τις ερωτήσεις έτοιμες προς χρήση στην εφαρμογή.
 */
public class DataResponse {

    /** Κωδικός κατάστασης του API (0 = επιτυχία, άλλες τιμές = σφάλμα/ειδικές περιπτώσεις). */
    @JsonProperty("response_code")
    private int responseCode;

    /** Λίστα με τις ερωτήσεις που επιστράφηκαν από το API. */
    private List<DataQuestion> results;

    public int getResponseCode() { return responseCode; }
    public void setResponseCode(int responseCode) { this.responseCode = responseCode; }

    public List<DataQuestion> getResults() { return results; }
    public void setResults(List<DataQuestion> results) { this.results = results; }
}
