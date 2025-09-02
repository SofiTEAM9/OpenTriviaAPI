/**
 * Module descriptor για τη βιβλιοθήκη OpenTriviaAPI.
 *
 * Σκοπός:
 * - Ορισμός των πακέτων που είναι ορατά σε άλλες εφαρμογές (service, model, exception).
 * - Δήλωση των εξαρτήσεων που χρειάζονται για την επικοινωνία με το Open Trivia DB
 *   (HTTP client, Jackson για JSON).
 * - Ενεργοποίηση του μηχανισμού reflection για το πακέτο model, ώστε ο Jackson να
 *   μπορεί να αποσειριοποιεί (deserialize) σωστά τα JSON responses σε POJOs.
 */
module OpenTriviaAPI {
    // Τα πακέτα που είναι διαθέσιμα προς χρήση από εξωτερικές εφαρμογές
    exports service;
    exports model;
    exports exception;

    // Εξαρτήσεις: Java HTTP client και Jackson για JSON parsing
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;

    // Επιτρέπει στον Jackson να χρησιμοποιεί reflection πάνω στα POJOs του πακέτου model
    opens model to com.fasterxml.jackson.databind;
}
