package gr.aueb.cf.teachersjaxapp.dto;

/**
 * Abstract class that declares
 * the common field (id) with
 * getters and setters to be used
 * by the most Data Transfer Objects.
 *
 * @author Thanasis Chousiadas
 */
public abstract class BaseDTO {
    private Long id;

    /**
     * Default constructor.
     */
    public BaseDTO() { }

    /**
     * Getter for the field id.
     *
     * @return the id in {@link Long} type.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for the field id.
     *
     * @param id the value of id to be inserted.
     */
    public void setId(Long id) {
        this.id = id;
    }
}
