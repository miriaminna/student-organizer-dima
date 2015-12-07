package app.studentorganizer.db.dao_test;

import app.studentorganizer.db.dao.GenericDAO;

/**
 * Created by Vitalii on 07-Dec-15.
 */
public abstract class BaseDAOTest<Entity> implements GenericDAO<Entity> {
    protected static long id = 0;

    protected static long getNewId() {
        try {
            return id;
        } finally {
            id += 1;
        }
    }
}
