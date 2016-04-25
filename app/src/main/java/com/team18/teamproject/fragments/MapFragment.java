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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.team18.teamproject.R;


/**
 * Class to handle and initialise the map fragment with an integrated map to display various
 * eateries to the user and link to google maps app if they want directions.
 *
 * Created by Alex
 *
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    private View fragmentView;
    private GoogleMap mMap;
    private LatLngBounds NEWCASTLE = new LatLngBounds(new LatLng(54.960879,-1.632811), new LatLng(55.012540,-1.571301));

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

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(NEWCASTLE, 0));

        if (ContextCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
        populateMap();
    }

    /**
     * Populates the map with markers for chosen locations including
     * location name, brief description of what's there and a colour coded marker.
     */
    private void populateMap(){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.974834,-1.615512))
                .title("Nando's")
                .snippet("Spicy Afro-Portuguese Chicken Chain")
                .icon(BitmapDescriptorFactory.defaultMarker(210)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.972388, -1.618548))
                .title("TGIFridays")
                .snippet("Lively American Restaurant & Bar Chain")
                .icon(BitmapDescriptorFactory.defaultMarker(270)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.987903,-1.577277))
                .title("Domino's")
                .snippet("Longtime Pizza Chain Known for Delivery")
                .icon(BitmapDescriptorFactory.defaultMarker(0)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.977769,-1.611792))
                .title("The Five Swans")
                .snippet("Whetherspoons Chain Pub Showing Big-Screen Sports")
                .icon(BitmapDescriptorFactory.defaultMarker(210)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.987999,-1.577738))
                .title("Chunky Chicken")
                .snippet("Fast-Food Piri-Piri Chicken Chain")
                .icon(BitmapDescriptorFactory.defaultMarker(0)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.972410,-1.618538))
                .title("Zaza's Bazaar")
                .snippet("Huge, Buzzing Global Buffet Restaurant")
                .icon(BitmapDescriptorFactory.defaultMarker(210)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.976784,-1.613356))
                .title("KFC")
                .snippet("Fast-Food Fried Chicken Chain")
                .icon(BitmapDescriptorFactory.defaultMarker(0)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.975929,-1.612549))
                .title("McDonald's")
                .snippet("Iconic Fast-Food Burger and Fries Chain")
                .icon(BitmapDescriptorFactory.defaultMarker(0)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.983601,-1.576014))
                .title("Pizza Pizza")
                .snippet("Fast-Food Pizza Chain")
                .icon(BitmapDescriptorFactory.defaultMarker(0)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(543*77179,-1.161597))
                .title("Zapatista")
                .snippet("Trendy Spot for Mexican Fast-Food")
                .icon(BitmapDescriptorFactory.defaultMarker(210)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.970952,-1.609899))
                .title("Longhorn's")
                .snippet("Flame-Grilled Barbecue Steakhouse")
                .icon(BitmapDescriptorFactory.defaultMarker(210)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.977728,-1.613009))
                .title("Greggs")
                .snippet("Bakery for Sweet & Savoury Goods")
                .icon(BitmapDescriptorFactory.defaultMarker(60)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.976818,-1.613224))
                .title("Eat4Less")
                .snippet("Low-Price sandwiches, Paninis & Baguettes")
                .icon(BitmapDescriptorFactory.defaultMarker(60)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.974657,-1.611834))
                .title("Burger King")
                .snippet("Fast-Food Chain for Grilled Burgers")
                .icon(BitmapDescriptorFactory.defaultMarker(0)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.974530,-1.615906))
                .title("Wagamama")
                .snippet("Bustling Japanese Canteen Chain")
                .icon(BitmapDescriptorFactory.defaultMarker(210)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.983531,-1.602312))
                .title("Fratellos")
                .snippet("Chic Spot for Italian Pizza & Pasta")
                .icon(BitmapDescriptorFactory.defaultMarker(120)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.992257,-1.606700))
                .title("Fat Hippo")
                .snippet("Gourmet US-Style Burgers & Hot-Dogs")
                .icon(BitmapDescriptorFactory.defaultMarker(270)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.990785,-1.604552))
                .title("Scalini's")
                .snippet("Rustic Room & Italian/Mediterranean Food")
                .icon(BitmapDescriptorFactory.defaultMarker(120)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.991839,-1.608684))
                .title("Dabbawal")
                .snippet("Indian Street Food Tapas-Style Dishes")
                .icon(BitmapDescriptorFactory.defaultMarker(30)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.987547,-1.593828))
                .title("Jesmond Tandoori")
                .snippet("Traditional Tandoori Indian Cuisine")
                .icon(BitmapDescriptorFactory.defaultMarker(30)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.969168,-1.607722))
                .title("Rani Indian Restaurant")
                .snippet("Tandooris, Biryanis & Classic Curries")
                .icon(BitmapDescriptorFactory.defaultMarker(30)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.971139,-1.625676))
                .title("Salt N Pepper")
                .snippet("Classic English Cafe Serving a Variety of Foods")
                .icon(BitmapDescriptorFactory.defaultMarker(210)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.971193,-1.625979))
                .title("Moulin Rouge Cafe & Grill")
                .snippet("Variety Foods Take-away/Eat-In")
                .icon(BitmapDescriptorFactory.defaultMarker(210)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.970884,-1.623933))
                .title("Coastline Fish & Chips")
                .snippet("Traditional English Chippy")
                .icon(BitmapDescriptorFactory.defaultMarker(210)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.971596,-1.611241))
                .title("Zizzi's")
                .snippet("Casual Pizza & Pasta Chain")
                .icon(BitmapDescriptorFactory.defaultMarker(120)));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.972880,-1.614160))
                .title("Subway")
                .snippet("Build-your-Own Sandwich Chain")
                .icon(BitmapDescriptorFactory.defaultMarker(60)));
    }

}
