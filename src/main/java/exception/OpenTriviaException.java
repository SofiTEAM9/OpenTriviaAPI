package exception;

/**
 * Εξαίρεση ειδικά για τη βιβλιοθήκη OpenTriviaAPI.
 *
 * Χρησιμοποιείται για να τυποποιεί τα σφάλματα που προκύπτουν κατά την επικοινωνία
 * με το Open Trivia DB API ή κατά την επεξεργασία των δεδομένων (π.χ. σφάλμα HTTP,
 * πρόβλημα κατά την αποσειριοποίηση JSON).
 *
 * Με αυτόν τον τρόπο, οι εφαρμογές που θα χρησιμοποιούν τη βιβλιοθήκη μπορούν
 * να χειρίζονται ενιαία όλα τα σφάλματα μέσω αυτής της εξαίρεσης, χωρίς να εκτίθενται
 * σε λεπτομέρειες χαμηλού επιπέδου.
 */
public class OpenTriviaException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Δημιουργεί εξαίρεση με μήνυμα περιγραφής.
     * @param message περιγραφή του σφάλματος
     */
    public OpenTriviaException(String message) {
        super(message);
    }

    /**
     * Δημιουργεί εξαίρεση με μήνυμα περιγραφής και αρχική αιτία.
     * @param message περιγραφή του σφάλματος
     * @param cause   η αρχική εξαίρεση που προκάλεσε το σφάλμα
     */
    public OpenTriviaException(String message, Throwable cause) {
        super(message, cause);
    }
}
