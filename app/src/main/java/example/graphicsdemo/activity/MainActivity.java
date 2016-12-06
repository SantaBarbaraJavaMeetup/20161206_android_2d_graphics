package example.graphicsdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

import example.graphicsdemo.animation.AnimationLoop;
import example.graphicsdemo.animation.AnimationSync;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // requesting to turn the title OFF
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // making it full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SurfaceView surfaceView = new SurfaceView(this);

        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        DemoAnimation animation = new DemoAnimation();
        final AnimationSync sync = new AnimationSync();

        AnimationLoop loop = new AnimationLoop(surfaceHolder, animation, sync);
        loop.start();

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                sync.resume();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                sync.pause();
            }
        });

        setContentView(surfaceView);
    }

}
