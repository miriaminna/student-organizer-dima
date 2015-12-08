package app.studentorganizer.entities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.com.ContentParent;

/**
 * Created by Vitalii on 23-Nov-15.
 */
public class Content extends IDable {
    protected List<String> mFiles;
    protected List<String> mLiterature;
    protected ContentParent mParentType;
    protected Long mParentId;

    public Long getParentId() {
        return mParentId;
    }

    public void setParentId(Long parentId) {
        this.mParentId = parentId;
    }

    public ContentParent getParentType() {
        return mParentType;
    }

    public void setParentType(ContentParent parentType) {
        this.mParentType = parentType;
    }

    public Content() {
        mFiles = new ArrayList<>();
        mLiterature = new ArrayList<>();
    }

    public List<String> getFiles() {
        return mFiles;
    }

    public void setFiles(List<String> mFiles) {
        this.mFiles = mFiles;
    }

    public List<String> getLiterature() {
        return mLiterature;
    }

    public void setLiterature(List<String> mLiterature) {
        this.mLiterature = mLiterature;
    }

    public void addFile(String file) {
        mFiles.add(file);
    }

    public void addLiterature(String lit) {
        mLiterature.add(lit);
    }


    @Override
    public String toString() {
        return getParentType().toString() + "'s content";
    }
}
