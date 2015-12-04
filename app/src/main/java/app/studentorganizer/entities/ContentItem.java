package app.studentorganizer.entities;

import app.studentorganizer.com.ContentType;

/**
 * Created by dmytroberezin on 12/4/15.
 */
public class ContentItem {
    protected Integer mId;
    protected Content mContent;
    protected Integer mContentId;
    protected ContentType mType;
    protected String mSource;
    protected String mText;

    public ContentItem() {
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Content getContent() {
        return mContent;
    }

    public void setContent(Content content) {
        mContent = content;
    }

    public Integer getContentId() {
        return mContentId;
    }

    public void setContentId(Integer contentId) {
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
