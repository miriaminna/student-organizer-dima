package app.studentorganizer.db.dao;

import java.util.List;

import app.studentorganizer.entities.Task;

public interface TaskDAO extends GenericDAO<Task> {
    List<Task> getBySubjectId(Long subjectId);
    void deleteBySubjectId(Long subjectId);
}
