package example.graphicsdemo.activity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;

import java.util.Random;

import example.graphicsdemo.animation.Animation;

public class Bounce implements Animation {

    private static final Interpolator interpolator = new BounceInterpolator();
    private static final Random random = new Random();
    private static final int FRAMES = 80;
    private static final int DIAMETER = 50;

    private final Paint paint;

    private float left;
    private int frame;

    public Bounce(int canvasWidth) {
        this(random.nextFloat() * (canvasWidth - DIAMETER),
                random.nextInt(FRAMES),
                Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255)));
    }

    public Bounce(float left, int frame, int color) {
        paint = new Paint();
        paint.setColor(color);

        this.left = left;
        this.frame = frame;
    }

    @Override
    public void draw(Canvas canvas) {
        float fraction = interpolator.getInterpolation((float) frame / FRAMES);

        float right = left + DIAMETER;
        float top = fraction * canvas.getHeight() - DIAMETER;
        float bottom = top + DIAMETER;

        canvas.drawOval(left, top, right, bottom, paint);

        frame = (frame + 1) % (FRAMES + 1);
    }

}
