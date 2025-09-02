package model;

/**
 * POJO κλάση που αναπαριστά μία κατηγορία ερωτήσεων του Open Trivia DB.
 *
 * Κάθε κατηγορία περιγράφεται από:
 * - ένα μοναδικό αναγνωριστικό (id),
 * - το όνομα της κατηγορίας (name).
 *
 * Η κλάση αυτή χρησιμοποιείται κατά την αποσειριοποίηση του JSON response
 * και επιτρέπει την εύκολη πρόσβαση στις διαθέσιμες κατηγορίες μέσα από
 * Java αντικείμενα.
 */
public class Category {

    /** Μοναδικό αναγνωριστικό της κατηγορίας (όπως ορίζεται από το API). */
    private int id;

    /** Ονομασία της κατηγορίας (π.χ. "General Knowledge"). */
    private String name;

    public int getId() { 
        return id; 
    }
    public void setId(int id) { 
        this.id = id; 
    }

    public String getName() { 
        return name; 
    }
    public void setName(String name) { 
        this.name = name; 
    }
}
