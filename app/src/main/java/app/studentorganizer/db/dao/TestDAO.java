package app.studentorganizer.db.dao;

import java.util.List;

import app.studentorganizer.entities.Test;

public interface TestDAO extends GenericDAO<Test> {
    List<Test> getBySubjectId(Long subjectId);
    void deleteBySubjectId(Long subjectId);
}
