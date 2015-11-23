package app.studentorganizer.entities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitalii on 23-Nov-15.
 */
public class Content {
    private List<File> mFiles;
    private List<String> mLiterature;

    public Content() {
        mFiles = new ArrayList<>();
        mLiterature = new ArrayList<>();
    }

    public List<File> getFiles() {
        return mFiles;
    }

    public void setFiles(List<File> mFiles) {
        this.mFiles = mFiles;
    }

    public List<String> getLiterature() {
        return mLiterature;
    }

    public void setLiterature(List<String> mLiterature) {
        this.mLiterature = mLiterature;
    }

    public void addFile(File file) {
        mFiles.add(file);
    }

    public void addLiterature(String lit) {
        mLiterature.add(lit);
    }
}
