package gr.aueb.cf.teachersjaxapp.service;

import gr.aueb.cf.teachersjaxapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.teachersjaxapp.dto.TeacherInsertDTO;
import gr.aueb.cf.teachersjaxapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.teachersjaxapp.model.Teacher;
import gr.aueb.cf.teachersjaxapp.service.exceptions.TeacherNotFoundException;

import java.util.List;

/**
 * This interface provides the Public API for the
 * Service Layer of the app. It implements the Business Logic
 * of this app.
 *
 * @author Thanasis Chousiadas
 */
public interface ITeacherService {
    Teacher insertTeacher(TeacherInsertDTO dto) throws TeacherDAOException;
    Teacher updateTeacher(TeacherUpdateDTO dto) throws TeacherDAOException, TeacherNotFoundException;
    void deleteTeacher(long id) throws TeacherDAOException, TeacherNotFoundException;
    List<Teacher> getTeachersByLastname(String lastname) throws TeacherDAOException;
    Teacher getTeacherById(long id) throws TeacherDAOException;
}
