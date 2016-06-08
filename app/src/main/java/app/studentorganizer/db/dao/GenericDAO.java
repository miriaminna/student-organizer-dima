package app.studentorganizer.db.dao;

import java.util.List;

import app.studentorganizer.entities.IDable;
import app.studentorganizer.entities.Teacher;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public interface GenericDAO<Entity extends IDable> {
    List<Entity> getAllEntities();
    Entity getByID(Long id);
    boolean updateEntity(Entity entity);
    boolean deleteEntity(Long id);
    Long addEntity(Entity entity);
    boolean clear();
}
