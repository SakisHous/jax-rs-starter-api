package gr.aueb.cf.teachersjaxapp.dao.exceptions;

/**
 * A wrapper exception to {@link java.sql.SQLException}
 * exception. It provides a convenient way
 * to pass custom messages for error regarding
 * database operations for the entity
 * {@link gr.aueb.cf.teachersjaxapp.model.Teacher}.
 *
 * @author Thanasis Chousiadas
 */
public class TeacherDAOException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * The default constructor that passes the message to the
     * {@link Exception} class.
     * @param s the message to be shown.
     */
    public TeacherDAOException(String s) {
        super(s);
    }
}
