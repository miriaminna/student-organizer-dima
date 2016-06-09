package app.studentorganizer.db.dao;

import app.studentorganizer.entities.StudentScheduleEntry;

public interface StudentScheduleDAO extends GenericDAO<StudentScheduleEntry> {
    void deleteBySubjectId(Long subjectId);
}
