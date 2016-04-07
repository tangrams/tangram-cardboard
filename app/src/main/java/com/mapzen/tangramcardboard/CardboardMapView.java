package com.mapzen.tangramcardboard;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.google.vrtoolkit.cardboard.CardboardView;
import com.mapzen.tangram.ConfigChooser;
import com.mapzen.tangram.MapView;

public class CardboardMapView extends MapView {

    public CardboardMapView(Context context) {

        super(context);

        configureGLSurfaceView();

    }

    public CardboardMapView(Context context, AttributeSet attrs) {

        super(context, attrs);

        configureGLSurfaceView();

    }

    GLSurfaceView getSurfaceView() {

        return glSurfaceView;

    }

    @Override
    protected void configureGLSurfaceView() {

        glSurfaceView = new CardboardView(getContext()) {
            @Override
            public void setOnTouchListener(OnTouchListener listener) {
                // Don't use the touch interface
            }
            @Override
            public void setRenderer(GLSurfaceView.Renderer renderer) {
                setRenderer((StereoRenderer)renderer);
            }
        };
        glSurfaceView.setEGLContextClientVersion(2);
        glSurfaceView.setPreserveEGLContextOnPause(true);
        glSurfaceView.setEGLConfigChooser(new ConfigChooser(8, 8, 8, 0, 16, 0));

    }
}
