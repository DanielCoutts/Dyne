package com.team18.teamproject;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class RecentFragment extends Fragment {

    public RecentFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recent, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        setHeights();
    }

    // STUFF FOR RESIZING IMAGES THAT I SHOULD BE ABLE TO DELETE ONCE IMAGES ARE THE RIGHT SIZE
    private void setHeights() {
        ImageButton mostRecent = (ImageButton) getView().findViewById(R.id.most_recent);
        int width = mostRecent.getWidth();

        ViewGroup.LayoutParams params = mostRecent.getLayoutParams();
        params.height = (width/3)*2;
        mostRecent.requestLayout();

        squareButton((ImageButton) getView().findViewById(R.id.button_1a) );
        squareButton((ImageButton) getView().findViewById(R.id.button_1b) );
//        squareButton((ImageButton) getView().findViewById(R.id.button_2a));
//        squareButton((ImageButton) getView().findViewById(R.id.button_2b));
    }

    private void squareButton(ImageButton tile) {
        int width = tile.getWidth();

        ViewGroup.LayoutParams params = tile.getLayoutParams();
        params.height = width;
        tile.requestLayout();
    }
}
