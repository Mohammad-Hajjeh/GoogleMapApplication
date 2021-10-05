package com.example.googlemapapplication;


import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.example.googlemapapplication.databinding.ActivityMapsBinding;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity {
    private RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    MapAdapter mapAdapter;

    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayList<City> cityArrayList = new ArrayList<>();
        cityArrayList.add(new City("sydney", -34, 151));
        cityArrayList.add(new City("vannes", 47.66, -2.75));
        cityArrayList.add(new City("sydney", -34, 151));
        cityArrayList.add(new City("vannes", 47.66, -2.75));
        recyclerView = findViewById(R.id.rv_map);
        layoutManager = new LinearLayoutManager(MapsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        mapAdapter = new MapAdapter(cityArrayList);
        recyclerView.setAdapter(mapAdapter);

    }

}