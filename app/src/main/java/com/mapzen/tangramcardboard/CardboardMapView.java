package com.mapzen.tangramcardboard;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.google.vrtoolkit.cardboard.CardboardView;
import com.mapzen.tangram.ConfigChooser;

public class CardboardMapView extends CardboardView {

    public CardboardMapView(Context context) {

        super(context);

        configureGLSurfaceView();

    }

    public CardboardMapView(Context context, AttributeSet attrs) {

        super(context, attrs);

        configureGLSurfaceView();

    }

    @Override
    public void setOnTouchListener(OnTouchListener listener) {
        // Don't use the tangram-es touch interface
    }

    @Override
    public void setRenderer(GLSurfaceView.Renderer renderer) {
        setRenderer((StereoRenderer)renderer);
    }

    private void configureGLSurfaceView() {

        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);
        setEGLConfigChooser(new ConfigChooser(8, 8, 8, 0, 16, 0));

    }
}
