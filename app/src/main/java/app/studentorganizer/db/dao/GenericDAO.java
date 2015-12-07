package app.studentorganizer.db.dao;

import java.util.List;

import app.studentorganizer.entities.Teacher;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public interface GenericDAO<Entity> {
    List<Entity> getAllEntities();
    Entity getByID(long id);
    boolean updateEntity(Entity entity);
    boolean deleteEntity(long id);
    long addEntity(Entity entity);
}
