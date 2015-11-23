package app.studentorganizer.entities;

import java.io.File;
import java.util.List;

/**
 * Created by Vitalii on 23-Nov-15.
 */
public interface IContent {
    List<File> getFiles();
    List<String> getLiterature();
}
