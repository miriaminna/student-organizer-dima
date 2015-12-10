package app.studentorganizer.db.dao;

import java.util.List;

import app.studentorganizer.com.ContentParent;
import app.studentorganizer.entities.ContentItem;

/**
 * Created by Vitalii on 08-Dec-15.
 */
public interface ContentItemDAO extends GenericDAO<ContentItem> {
    List<ContentItem> getByParent(Long parentId, ContentParent parentType);
}
