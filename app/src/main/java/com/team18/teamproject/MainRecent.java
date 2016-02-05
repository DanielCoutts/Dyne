package com.team18.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class MainRecent extends BaseActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_recent);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
        }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        setHeights();
    }

    private void setHeights() {
        ImageButton mostRecent = (ImageButton) findViewById(R.id.most_recent);
        int width = mostRecent.getWidth();

        ViewGroup.LayoutParams params = mostRecent.getLayoutParams();
        params.height = (width/3)*2;
        mostRecent.requestLayout();

        squareButton( (ImageButton) findViewById(R.id.button_1a) );
        squareButton( (ImageButton) findViewById(R.id.button_1b) );
        squareButton((ImageButton) findViewById(R.id.button_2a));
        squareButton((ImageButton) findViewById(R.id.button_2b));
    }

    private void squareButton(ImageButton tile) {
        int width = tile.getWidth();

        ViewGroup.LayoutParams params = tile.getLayoutParams();
        params.height = width;
        tile.requestLayout();
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, RecipeTemplate.class);
        startActivity(intent);
    }
}
