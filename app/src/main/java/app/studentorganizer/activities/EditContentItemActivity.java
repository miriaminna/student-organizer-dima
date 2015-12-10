package app.studentorganizer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import app.studentorganizer.R;
import app.studentorganizer.com.ContentParent;
import app.studentorganizer.com.ContentType;
import app.studentorganizer.db.DBFactory;
import app.studentorganizer.entities.ContentItem;

public class EditContentItemActivity extends BaseActivity {

    /**Parent id extra. Should be passed as long.*/
    public static final String PARENT_ID_EXTRA = "_PARENT_ID";
    /** */
    private static final Long PARENT_ID_DEFAULT = -1L;
    /**Parent type extra. Should be passed as String got from toString() method.*/
    public static final String PARENT_TYPE_EXTRA = "_PARENT_TYPE";

    protected Long mParentId;
    protected ContentParent mContentParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mParentId = getIntent().getLongExtra(PARENT_ID_EXTRA, PARENT_ID_DEFAULT);
        try {
            mContentParent = ContentParent.valueOf(
                    getIntent().getStringExtra(PARENT_TYPE_EXTRA));
        } catch (IllegalArgumentException e) {
            mParentId = PARENT_ID_DEFAULT;
        }

        // If one of extras has not been passed, finish activity
        if (mParentId.equals(PARENT_ID_DEFAULT)) {
            finish();
        }

        List<String> contentTypes = new ArrayList<>();
        for (ContentType t : ContentType.values()) {
            contentTypes.add(t.toString());
        }
        ((Spinner) findViewById(R.id.content_type)).setAdapter(
                new ArrayAdapter<>(this, R.layout.spinner_item, contentTypes)
        );

        findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new content item and fill its fields
                ContentItem contentItem = new ContentItem();
                contentItem.setParentType(mContentParent);
                contentItem.setParentId(mParentId);
                contentItem.setType(ContentType.valueOf(
                        ((Spinner) findViewById(R.id.content_type)).
                                getSelectedItem().toString()));
                contentItem.setText(
                        ((EditText) findViewById(R.id.content_text)).getText().toString());

                // Persist content item to database
                DBFactory.getFactory().getContentItemDAO().addEntity(contentItem);

                finish();
            }
        });
    }

    @Override
    public int getContentView() {
        return R.layout.content_item_edit;
    }

    @Override
    public void loadDataFromDB() {
    }
}
