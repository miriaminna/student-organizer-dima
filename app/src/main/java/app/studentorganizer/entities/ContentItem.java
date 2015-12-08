package app.studentorganizer.entities;

import app.studentorganizer.com.ContentType;

/**
 * Created by dmytroberezin on 12/4/15.
 */
public class ContentItem extends IDable {
    protected Long mContentId;
    protected ContentType mType;
    protected String mSource;
    protected String mText;

    public ContentItem() {
    }

    public Long getContentId() {
        return mContentId;
    }

    public void setContentId(Long contentId) {
        mContentId = contentId;
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
}
