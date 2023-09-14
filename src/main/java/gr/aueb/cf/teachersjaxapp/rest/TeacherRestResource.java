package gr.aueb.cf.teachersjaxapp.rest;

import gr.aueb.cf.teachersjaxapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.teachersjaxapp.dto.TeacherInsertDTO;
import gr.aueb.cf.teachersjaxapp.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.teachersjaxapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.teachersjaxapp.model.Teacher;
import gr.aueb.cf.teachersjaxapp.service.ITeacherService;
import gr.aueb.cf.teachersjaxapp.service.exceptions.TeacherNotFoundException;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Teachers controller class.
 * Handles API calls (requests) and
 * manages the responses.
 *
 * @author Thanasis Chousiadas
 */
@Path("/teachers")
public class TeacherRestResource {
    @Inject
    private ITeacherService teacherService;
    private final Validator validator;

    /**
     * Initiate a validator to validate the incoming data due to some criteria.
     */
    public TeacherRestResource() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }


    /**
     * Handles the GET requests with a query parameter
     * in the path '/api/teachers?lastname='. The
     * API provide {@link Teacher} objects in JSON format.
     *
     * @param lastname the query parameter
     * @return a JSON object with the result for a successful
     * response.
     */
    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeacherByLastname(@QueryParam("lastname") String lastname) {
        try {
            List<Teacher> teachers = teacherService.getTeachersByLastname(lastname);

            if (teachers.size() == 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Bad Request")
                        .build();
            }
            return Response.status(Response.Status.OK).entity(teachers).build();
        } catch (TeacherDAOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Service Error")
                    .build();
        }
    }

    @Path("/{teacherId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeacher(@PathParam("teacherId") long teacherId) {
        try {
            Teacher teacher = teacherService.getTeacherById(teacherId);

            if (teacher == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Bad Request").build();
            }

            TeacherReadOnlyDTO dto = mapFrom(teacher);

            return Response.status(Response.Status.OK).entity(dto).build();
        } catch (TeacherDAOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Service Error")
                    .build();
        }
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTeacher(TeacherInsertDTO dto, @Context UriInfo uriInfo) {

        Set<ConstraintViolation<TeacherInsertDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            List<String> errors = new ArrayList<>();
            for (ConstraintViolation<TeacherInsertDTO> violation : violations) {
                errors.add(violation.getMessage());
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();
        }

        try {
            Teacher teacher = teacherService.insertTeacher(dto);
            if (teacher == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Bad Request").build();
            }

            TeacherReadOnlyDTO readOnlyDTO = mapFrom(teacher);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            return Response.created(uriBuilder.path(Long.toString(readOnlyDTO.getId())).build())
                    .entity(readOnlyDTO)
                    .build();
        } catch (TeacherDAOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Service Error")
                    .build();
        }

    }

    @Path("/{teacherId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTeacher(@PathParam("teacherId") long teacherId) {
        try {
            Teacher teacher = teacherService.getTeacherById(teacherId);
            teacherService.deleteTeacher(teacherId);

            TeacherReadOnlyDTO readOnlyDTO = mapFrom(teacher);

            return Response.status(Response.Status.OK).entity(readOnlyDTO).build();
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Service Error")
                    .build();
        }
    }

    @Path("/{teacherId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTeacher(@PathParam("teacherId") long teacherId, TeacherUpdateDTO dto) {
        try {
            dto.setId(teacherId);

            Set<ConstraintViolation<TeacherUpdateDTO>> violations = validator.validate(dto);

            if (!violations.isEmpty()) {
                List<String> errors = new ArrayList<>();
                for (ConstraintViolation<TeacherUpdateDTO> violation : violations) {
                    errors.add(violation.getMessage());
                }

                return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();
            }

            Teacher teacher = teacherService.updateTeacher(dto);
            if (teacher == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Bad Request").build();
            }

            TeacherReadOnlyDTO readOnlyDTO = mapFrom(teacher);
            return Response.status(Response.Status.OK).entity(readOnlyDTO).build();
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal Service Error")
                    .build();
        }
    }

    private TeacherReadOnlyDTO mapFrom(Teacher teacher) {
        return new TeacherReadOnlyDTO(teacher.getId(), teacher.getFirstname(), teacher.getLastname());
    }
}
