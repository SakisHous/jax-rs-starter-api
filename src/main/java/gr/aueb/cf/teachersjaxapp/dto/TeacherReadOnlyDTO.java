package gr.aueb.cf.teachersjaxapp.dto;

/**
 * Data Transfer Object for sending
 * {@link gr.aueb.cf.teachersjaxapp.model.Teacher} entity's
 * information in the front-end of the app.
 * In non-trivial situations some fields need mapping
 * or even not passing them in the front-end.
 *
 * @author Thanasis Chousiadas
 */
public class TeacherReadOnlyDTO {
    private Long id;
    private String firstname;
    private String lastname;

    public TeacherReadOnlyDTO(Long id, String firstname, String lastname) {
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
}
