package app.studentorganizer.entities;

import app.studentorganizer.com.ContentParent;
import app.studentorganizer.com.ContentType;

/**
 * Created by dmytroberezin on 12/4/15.
 */
public class ContentItem extends IDable {
    protected Long mParentId;
    protected ContentParent mParentType;
    protected ContentType mType;
    protected String mSource;
    protected String mText;

    public ContentParent getParentType() {
        return mParentType;
    }

    public void setParentType(ContentParent parentType) {
        this.mParentType = parentType;
    }

    public Long getParentId() {
        return mParentId;
    }

    public void setParentId(Long parentId) {
        this.mParentId = parentId;
    }

    public ContentType getType() {
        return mType;
    }

    public void setType(ContentType type) {
        mType = type;
    }

    public String getSource() {
        return mSource;
    }

    public void setSource(String source) {
        mSource = source;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    @Override
    public String toString() {
        return getType().toString();
    }
}
