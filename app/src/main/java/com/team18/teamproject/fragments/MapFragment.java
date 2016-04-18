package com.team18.teamproject.fragments;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.team18.teamproject.R;


/**
 * Created by Alex on 17/04/2016.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    private View fragmentView;
    private GoogleMap mMap;
    private LatLngBounds NEWCASTLE = new LatLngBounds(new LatLng(54.962844, -1.619661), new LatLng(55.012540, -1.571301));

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.fragment_map, container, false);

        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(NEWCASTLE,0));

        if (ContextCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }

        mMap.addMarker(new MarkerOptions().position(new LatLng(54.974834,-1.615512)).title("Nando's"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(54.972388,-1.618548)).title("TGIFridays"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(54.987903,-1.577277)).title("Domino's"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(54.977769,-1.611792)).title("The Five Swans"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(54.987999,-1.577738)).title("Chunky Chicken"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(54.972410,-1.618538)).title("Zaza's Bazaar"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(54.976784,-1.613356)).title("KFC"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(54.975929,-1.612549)).title("McDonald's"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(54.983601,-1.576014)).title("Pizza Pizza"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(543*77179,-1.161597)).title("Zapatista"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(54.970952, -1.609899)).title("Longhorn's"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(54.977728,-1.613009)).title("Greggs"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(54.976818,-1.613224)).title("Eat4Less"));
    }

}
