package gr.aueb.cf.teachersjaxapp.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object for insert
 * operation of a new {@link gr.aueb.cf.teachersjaxapp.model.Teacher}
 * object in the database.
 *
 * @author Thanasis Chousiadas
 */
public class TeacherInsertDTO {
    @NotNull
    @Size(min = 3, max = 52, message = "Firstname must be between 3 - 52 characters")
    private String firstname;
    @NotNull
    @Size(min = 3, max = 52, message = "Lastname must be between 3 - 52 characters")
    private String lastname;

    public TeacherInsertDTO() { }

    public TeacherInsertDTO(String firstname, String lastname) {
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
