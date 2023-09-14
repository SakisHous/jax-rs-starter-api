package gr.aueb.cf.teachersjaxapp.model;

/**
 * Entity class that represents attributes of
 * the table TEACHERS in the database.
 *
 * @author Thanasis Chousiadas
 */
public class Teacher {
    private Long id;
    private String firstname;
    private String lastname;

    public Teacher() { }

    public Teacher(Long id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return id + ", " + firstname + ", " + lastname;
    }
}
