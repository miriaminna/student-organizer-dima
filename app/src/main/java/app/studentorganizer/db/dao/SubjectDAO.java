package app.studentorganizer.db.dao;

import java.util.List;

import app.studentorganizer.entities.Subject;

public interface SubjectDAO extends GenericDAO<Subject> {
    List<Subject> getByTeacherId(Long teacherId);
    void deleteByTeacherId(Long teacherId);
}
