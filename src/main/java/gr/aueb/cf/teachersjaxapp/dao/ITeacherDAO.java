package gr.aueb.cf.teachersjaxapp.dao;

import gr.aueb.cf.teachersjaxapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.teachersjaxapp.model.Teacher;

import java.util.List;

/**
 * This interface provides the Public API for
 * Data Access Object Layer of this app. Implements
 * the main operations, such as CRUD operations and
 * some more specific queries.
 *
 * @author Thanasis Chousiadas
 */
public interface ITeacherDAO {

    Teacher insert(Teacher teacher) throws TeacherDAOException;
    Teacher update(Teacher teacher) throws TeacherDAOException;
    void delete(long id) throws TeacherDAOException;
    List<Teacher> getByLastname(String lastname) throws TeacherDAOException;
    Teacher getById(long id) throws TeacherDAOException;
}
