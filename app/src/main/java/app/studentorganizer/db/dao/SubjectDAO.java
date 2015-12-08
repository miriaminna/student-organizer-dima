package app.studentorganizer.db.dao;

import java.util.List;

import app.studentorganizer.entities.Subject;

/**
 * Created by Vitalii on 08-Dec-15.
 */
public interface SubjectDAO extends GenericDAO<Subject> {
    List<Subject> getByTeacherId(Long teacherId);
}
