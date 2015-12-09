package app.studentorganizer.db.dao;

import java.util.List;

import app.studentorganizer.entities.Test;

/**
 * Created by Vitalii on 08-Dec-15.
 */
public interface TestDAO extends GenericDAO<Test> {
    List<Test> getBySubjectId(Long subjectId);
}
