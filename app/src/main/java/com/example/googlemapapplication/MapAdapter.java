package com.example.googlemapapplication;



import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapAdapter extends RecyclerView.Adapter<MapAdapter.MyViewHolders>/* extends FragmentActivity implements OnMapReadyCallback*/ {
    private ArrayList<City> cities;
//    private GoogleMap mMap;

    public MapAdapter(ArrayList<City> cities) {
        this.cities = cities;
    }

    @NonNull
    @Override
    public MapAdapter.MyViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.map_item,parent,false);
        return new MapAdapter.MyViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MapAdapter.MyViewHolders holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText("Site #"+String.valueOf(position+1)+" "+cities.get(position).getName());
        holder.mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                LatLng sydney = new LatLng(cities.get(position).getLatitude(), cities.get(position).getLongitude());
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in"+cities.get(position).getName()));


                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(sydney)
                        .zoom(12.0f)
                        .bearing(90)
                        .tilt(30)
                        .build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            }
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }
    public void clear(){
        cities.clear();
        notifyDataSetChanged();
    }
    public static class MyViewHolders extends RecyclerView.ViewHolder{
        TextView title;
        MapView mapView;


        public MyViewHolders(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            mapView = (MapView) itemView.findViewById(R.id.mapView);
            mapView.onCreate(null);
            mapView.onResume();


        }
    }
}

