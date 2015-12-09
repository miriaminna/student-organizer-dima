package app.studentorganizer.db.dao;

import java.util.List;

import app.studentorganizer.entities.Task;

/**
 * Created by Vitalii on 08-Dec-15.
 */
public interface TaskDAO extends GenericDAO<Task> {
    List<Task> getBySubjectId(Long subjectId);
}
