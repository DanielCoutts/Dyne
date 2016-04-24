package com.team18.teamproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.team18.teamproject.Application;
import com.team18.teamproject.R;

/**
 * The guide activity uses a different xml file depending on the selected guide.
 */
public class GuideActivity extends AppCompatActivity {

    /**
     * The toolbar. This contains a back button and a title.
     */
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout based on current ID.
        setLayout(Application.getCurrentGuideId());

        // Set up toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * sets the content view using on an id argument.
     *
     * @param id the id of the desired essential layout.
     */
    private void setLayout(int id) {
        switch (id) {
            case 1:
                setTitle(R.string.guide1_title);
                setContentView(R.layout.guide_1);
                return;
            // TODO Uncomment these
            case 2:
                setTitle(R.string.guide2_title);
                setContentView(R.layout.guide_2);
                return;
            case 3:
                setTitle(R.string.guide3_title);
                setContentView(R.layout.guide_3);
                return;
            case 4:
                setTitle(R.string.guide4_title);
                setContentView(R.layout.guide_4);
                return;
//            case 5:
//                setTitle(R.string.guide5_title);
//                setContentView(R.layout.guide_5);
//                return;
//            case 6:
//                setTitle(R.string.guide6_title);
//                setContentView(R.layout.guide_6);
//                return;
//            case 7:
//                setTitle(R.string.guide7_title);
//                setContentView(R.layout.guide_7);
//                return;
//            case 8:
//                setTitle(R.string.guide8_title);
//                setContentView(R.layout.guide_8);
//                return;
//            case 9:
//                setTitle(R.string.guide9_title);
//                setContentView(R.layout.guide_9);
//                return;
//            case 10:
//                setTitle(R.string.guide10_title);
//                setContentView(R.layout.guide_10);
//                return;
            default:
                onBackPressed();
                return;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            // Back button in action bar has the same behaviour as the system back button
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
