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
            new LngLat(-74.01321, 40.70589),    // Begining of broadway
            new LngLat(-73.99144, 40.73171),    // Begining of broadway
            new LngLat(-73.98912, 40.74133),    // Begining of broadway
            new LngLat(-73.98914, 40.74261),    // Flatiron
            new LngLat(-73.986551, 40.755644),  // Flatiron
            new LngLat(-73.98628, 40.75576),    // Flatiron
            new LngLat(-73.985544, 40.757943),  // Flatiron
    };

    int[] locationAnimationDurations = {
            1500, 15000, 5000, 1000, 3000, 2000, 3000
    };

    float[] zooms = {
            18.49f, 20.f, 18.f, 18.f, 17.f, 19.f, 17.f
    };

    int[] zoomAnimationDurations = {
            1500, 15000, 5000, 1000, 3000, 2000, 5000
    };

    double[] tilts = {
            82.88, 87.53, 82.78, 82.05, 77.48, 85.96, 52.80
    };

    int[] tiltAnimationDurations = {
            1500, 15500, 5000, 1000, 3000, 2000, 5000
    };

    double[] rotations = {
            327.7184, 327.7184, 351.2359, 352.027, 346.53, 343.42, 163.17
    };

    int[] rotationAnimationDurations = {
            1500, 15000, 1500, 1000, 300, 2000, 10000
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

        mapController.setPosition(locationCoordinates[index], locationAnimationDurations[index]);
        mapController.setZoom(zooms[index], zoomAnimationDurations[index]);

        float tiltRadians = (float) (Math.toRadians(tilts[index]));
        mapController.setTilt(tiltRadians, tiltAnimationDurations[index]);

        float rotationRadians = (float) Math.toRadians(rotations[index]);
        mapController.setRotation(rotationRadians, rotationAnimationDurations[index]);


    }

}

