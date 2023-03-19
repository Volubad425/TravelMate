package com.example.travelmateapp;

import com.google.android.gms.maps.GoogleMap;

public class NearbyPlacesFinder {
    public void exec(GoogleMap mMap, String url, String placeType){
        Object dataFetch[] = new Object[3];
        dataFetch[0] = mMap;
        dataFetch[1] = url;
        dataFetch[2] = placeType;

        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
        getNearbyPlacesData.execute(dataFetch);
    }
}