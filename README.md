# OpenTriviaAPI

Java βιβλιοθήκη (Maven project) που επικοινωνεί με το [Open Trivia DB API](https://opentdb.com/api_config.php).  

## Δυνατότητες
- Ανάκτηση ερωτήσεων με φίλτρα (amount, category, difficulty, type)
- Λήψη λίστας κατηγοριών
- Αποσειριοποίηση JSON σε Java POJOs με Jackson
- Χειρισμός εξαιρέσεων μέσω `OpenTriviaException`
- Unit tests με JUnit

## Εκτέλεση
```bash
mvn clean install
