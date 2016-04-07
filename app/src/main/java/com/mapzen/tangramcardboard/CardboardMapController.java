package com.mapzen.tangramcardboard;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.google.vrtoolkit.cardboard.CardboardView;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.google.vrtoolkit.cardboard.Viewport;
import com.mapzen.tangram.MapController;

import javax.microedition.khronos.egl.EGLConfig;

public class CardboardMapController extends MapController implements CardboardView.StereoRenderer {

    CardboardMapController(Context context, String sceneFilePath) {
        super(context, sceneFilePath);
    }

    @Override
    public void onNewFrame(HeadTransform headTransform) {
        // Empty
    }

    @Override
    public void onDrawEye(Eye eye) {

        super.setOverrideMatrices(eye.getEyeView(), eye.getPerspective(1f, 1e4f));
        super.onDrawFrame(null);

    }

    @Override
    public void onFinishFrame(Viewport viewport) {
        // Empty
    }

    @Override
    public void onSurfaceChanged(int width, int height) {

        super.onSurfaceChanged(null, width, height);

    }

    @Override
    public void onSurfaceCreated(EGLConfig eglConfig) {

        super.onSurfaceCreated(null, eglConfig);

    }

    @Override
    public void onRendererShutdown() {
        // Empty
    }
}
