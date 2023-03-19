package com.example.travelmateapp;

import android.os.AsyncTask;

import androidx.loader.content.AsyncTaskLoader;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class GetNearbyPlacesData extends AsyncTask<Object, String, String> {
    String googlePlacesData;
    GoogleMap googleMap;
    String url;
    String placeType;

    @Override
    protected String doInBackground(Object... objects) {
        googleMap = (GoogleMap) objects[0];
        url = (String) objects[1];
        placeType = (String) objects[2];
        DownloadUrl downloadUrl = new DownloadUrl();

        try {
            googlePlacesData = downloadUrl.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s){
        try {
            JSONObject parentObject = new JSONObject(s);
            JSONArray resultArray = parentObject.getJSONArray("results");

            for (int i = 0; i < resultArray.length(); i++){
                JSONObject jsonObject = resultArray.getJSONObject(i);
                JSONObject locationObj = jsonObject.getJSONObject("geometry").getJSONObject("location");

                String latitude = locationObj.getString("lat");
                String longitude = locationObj.getString("lng");

                JSONObject nameObject = resultArray.getJSONObject(i);

                String name = nameObject.getString("name");

                LatLng latLng = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.title(name);

                if(placeType.equals("restaurant"))
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_restaurant));
                else if(placeType.equals("hobbies"))
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_basketball));
                else if(placeType.equals("lodging"))
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_hotel));
                else if(placeType.equals("store"))
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_shopping_cart));
                else if(placeType.equals("park"))
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_park));

                markerOptions.position(latLng);

                googleMap.addMarker(markerOptions);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}
