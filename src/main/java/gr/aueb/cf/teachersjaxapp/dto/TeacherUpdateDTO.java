package gr.aueb.cf.teachersjaxapp.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object for the update operation
 * of a {@link gr.aueb.cf.teachersjaxapp.model.Teacher} entity
 * in the database.
 *
 * @author Thanasis Chousiadas
 */
public class TeacherUpdateDTO extends BaseDTO {
    @NotNull
    @Size(min = 3, max = 52, message = "Firstname must be between 3 - 52 characters")
    private String firstname;
    @NotNull
    @Size(min = 3, max = 52, message = "Lastname must be between 3 - 52 characters")
    private String lastname;

    public TeacherUpdateDTO() { }

    public TeacherUpdateDTO(Long id, String firstname, String lastname) {
        this.setId(id);
        this.firstname = firstname;
        this.lastname = lastname;
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
