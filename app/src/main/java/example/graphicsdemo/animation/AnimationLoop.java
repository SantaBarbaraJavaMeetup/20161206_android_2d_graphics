package example.graphicsdemo.animation;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class AnimationLoop implements Runnable {

    /**
     * Target frames per second.
     */
    private static final int FPS = 30;

    /**
     * Target frame period milliseconds.
     */
    private static final int FRAME_PERIOD = 1000 / FPS;

    /**
     * Holds a display surface and provides access to {@link Canvas} for drawing.
     */
    private final SurfaceHolder surfaceHolder;

    /**
     * {@link Animation} that paints canvas.
     */
    private final Animation animation;

    /**
     * Synchronization utility for pausing animation loop.
     */
    private final AnimationSync sync;

    /**
     * Worker thread.
     */
    private final Thread thread;

    public AnimationLoop(SurfaceHolder surfaceHolder, Animation animation, AnimationSync sync) {
        this.surfaceHolder = surfaceHolder;
        this.animation = animation;
        this.sync = sync;

        thread = new Thread(this);
    }

    public void start() {
        thread.start();
    }

    private void drawFrame() {
        Canvas canvas = surfaceHolder.lockCanvas();

        // surface not ready
        if (canvas == null) {
            return;
        }

        try {
            animation.draw(canvas);
        } finally {
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void run() {
        int frame = 0;
        long time = System.currentTimeMillis();
        long iterationTimeSum = 0;

        while (sync.await()) {
            long beginTime = System.currentTimeMillis();

            drawFrame();

            // calculate how long did the cycle take
            long timeDiff = System.currentTimeMillis() - beginTime;
            iterationTimeSum += timeDiff;

            // calculate sleep time
            long sleepTime = FRAME_PERIOD - timeDiff;

            // sleep for remainder of frame period
            if (sleepTime > 0) {
                try {Thread.sleep(sleepTime); } catch (InterruptedException ignore) {}
            }

            frame++;

            if (frame % FPS == 0) {
                long now = System.currentTimeMillis();
                long elapsed = now - time;
                float fps = (FPS / (float)elapsed) * 1000.0f;
                float iterationTime = iterationTimeSum / FPS;
                Log.i("AnimationLoop", "fps-target: " + FPS + ", fps-observed: " + fps + ", iteration-time: " + iterationTime + ", frame-period: " + FRAME_PERIOD);
                frame = 0;
                iterationTimeSum = 0;
                time = now;
            }
        }
    }

}
