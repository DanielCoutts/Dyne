package com.team18.teamproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setHeights();
    }

    private void setHeights() {
        ImageButton mostRecent = (ImageButton) findViewById(R.id.most_recent);
        int width = mostRecent.getWidth();
        mostRecent.setMaxHeight((width/3) * 2);
    }
}
