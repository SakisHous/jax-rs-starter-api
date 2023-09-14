package gr.aueb.cf.teachersjaxapp.service.exceptions;


import gr.aueb.cf.teachersjaxapp.model.Teacher;

/**
 * An exception class that handle errors
 * that occurred when a client tries
 * to update or delete a record in the database
 * that it does not exists.
 *
 * @author Thanasis Chousiadas
 */
public class TeacherNotFoundException extends Exception {
    private static final long serialVersionUID = 2023L;

    public TeacherNotFoundException(Teacher teacher) {
        super("Teacher with id: " + teacher.getId() + " not found");
    }

    public TeacherNotFoundException(String s) {
        super(s);
    }
}
