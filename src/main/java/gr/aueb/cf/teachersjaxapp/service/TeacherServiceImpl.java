package gr.aueb.cf.teachersjaxapp.service;


import gr.aueb.cf.teachersjaxapp.dao.ITeacherDAO;
import gr.aueb.cf.teachersjaxapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.teachersjaxapp.dto.TeacherInsertDTO;
import gr.aueb.cf.teachersjaxapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.teachersjaxapp.model.Teacher;
import gr.aueb.cf.teachersjaxapp.service.exceptions.TeacherNotFoundException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ext.Provider;
import java.sql.SQLException;
import java.util.List;

/**
 * This interface implements the Public API
 * of the {@link ITeacherService} interface, for
 * the Service Layer of this application.
 * It implements services for CRUD operations in
 * {@link Teacher} objects.
 *
 * @author Thanasis Chousiadas
 */
@Provider
@ApplicationScoped
public class TeacherServiceImpl implements ITeacherService {

    @Inject
    private ITeacherDAO teacherDAO;

    /**
     * This method inserts a new teacher in the database.
     *
     * @param dto the Data Transfer Object with the data for insert the record.
     * @return the inserted {@link Teacher} entity.
     * @throws TeacherDAOException wrapper exception to {@link SQLException}
     *                             that is thrown if an error is occurred
     *                             during database operations.
     */
    @Override
    public Teacher insertTeacher(TeacherInsertDTO dto) throws TeacherDAOException {
        if (dto == null) return null;
        Teacher teacher;
        try {
            teacher = map(dto);

            return teacherDAO.insert(teacher);
        } catch (TeacherDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * This method updates an old teacher record with a new one.
     *
     * @param dto the Data Transfer Object with the data for update the record.
     * @return the updated {@link Teacher} object.
     * @throws TeacherDAOException      is thrown if the teacher with an id to be updated
     *                                  in the database is not found.
     * @throws TeacherNotFoundException wrapper exception to {@link SQLException}
     *                                  that is thrown if an error is occurred
     *                                  during database operations.
     */
    @Override
    public Teacher updateTeacher(TeacherUpdateDTO dto) throws TeacherDAOException, TeacherNotFoundException {
        if (dto == null) return null;
        Teacher teacher;
        try {
            teacher = map(dto);

            if (teacherDAO.getById(teacher.getId()) == null) {
                throw new TeacherNotFoundException(teacher);
            }

            return teacherDAO.update(teacher);
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * This method deletes a teacher with an id given
     * by the user.
     *
     * @param id the id given by the user.
     * @throws TeacherDAOException      is thrown if the teacher with an id to be updated
     *                                  in the database is not found.
     * @throws TeacherNotFoundException wrapper exception to {@link SQLException}
     *                                  that is thrown if an error is occurred
     *                                  during database operations.
     */
    @Override
    public void deleteTeacher(long id) throws TeacherDAOException, TeacherNotFoundException {
        Teacher teacher;

        try {
            teacher = teacherDAO.getById(id);

            if (teacher == null) {
                throw new TeacherNotFoundException("Delete Error: Teacher with id: " + id + " was not found");
            }

            teacherDAO.delete(id);
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * This method returns the teachers where their lastname
     * begins with the parameter given by the client.
     *
     * @param lastname the parameter for searching the teachers' lastname.
     * @return an {@link java.util.ArrayList} with {@link Teacher} objects.
     * @throws TeacherDAOException wrapper exception to {@link SQLException}
     *                             that is thrown if an error is occurred
     *                             during database operations.
     */
    @Override
    public List<Teacher> getTeachersByLastname(String lastname) throws TeacherDAOException {
        List<Teacher> teachers;

        try {
            teachers = teacherDAO.getByLastname(lastname);
            return teachers;
        } catch (TeacherDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * This method returns a teacher with a certain id (primary key).
     *
     * @param id the id of the student.
     * @return a {@link Teacher} object.
     * @throws TeacherDAOException wrapper exception to {@link SQLException}
     *                             that is thrown if an error is occurred
     *                              during database operations.
     */
    @Override
    public Teacher getTeacherById(long id) throws TeacherDAOException {
        Teacher teacher;

        try {
            teacher = teacherDAO.getById(id);
            return teacher;
        } catch (TeacherDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * This method maps the {@link TeacherInsertDTO} object
     * to {@link Teacher} object for insert operation in the DB.
     *
     * @param dto a {@link TeacherInsertDTO} object.
     * @return a {@link Teacher} object.
     */
    private Teacher map(TeacherInsertDTO dto) {
        return new Teacher(null, dto.getFirstname(), dto.getLastname());
    }

    /**
     * This method maps the {@link TeacherUpdateDTO} object
     * to {@link Teacher} object for update operation in the DB.
     *
     * @param dto a {@link TeacherUpdateDTO} object.
     * @return a {@link Teacher} object.
     */
    private Teacher map(TeacherUpdateDTO dto) {
        return new Teacher(dto.getId(), dto.getFirstname(), dto.getLastname());
    }
}
