package com.mapzen.tangramcardboard;

import android.os.Bundle;
import android.view.Window;

import com.google.vrtoolkit.cardboard.CardboardActivity;
import com.google.vrtoolkit.cardboard.CardboardView;
import com.mapzen.tangram.HttpHandler;
import com.mapzen.tangram.LngLat;

import java.io.File;

public class MainActivity extends CardboardActivity {

    CardboardMapController mapController;
    CardboardView view;

    int locationIndex = 0;
    LngLat[] locationCoordinates = {
            new LngLat(-74.00976419448854, 40.70532700869127), // Manhattan
            new LngLat(-122.39901, 37.79241), // San Francisco
            new LngLat(-0.11870, 51.50721), // London
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.main);

        view = (CardboardView)findViewById(R.id.map);
        mapController = new CardboardMapController(this, "gotham.yaml", view);

        view.setRenderer(mapController);

        HttpHandler handler = new HttpHandler();
        File cacheDir = getExternalCacheDir();
        if (cacheDir != null && cacheDir.exists()) {
            handler.setCache(new File(cacheDir, "tile_cache"), 30 * 1024 * 1024);
        }

        mapController.setHttpHandler(handler);

        setCardboardView(view);
        setConvertTapIntoTrigger(true);

        goToLocation(locationIndex);
    }

    @Override
    public void onCardboardTrigger() {
        locationIndex++;
        goToLocation(locationIndex);
    }

    void goToLocation(int index) {

        index %= locationCoordinates.length;

        mapController.setZoom(18);
        mapController.setPosition(locationCoordinates[index]);

    }

}

