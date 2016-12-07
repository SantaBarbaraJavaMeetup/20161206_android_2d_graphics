package example.graphicsdemo.activity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;

import java.util.Random;

public class Circle {

    private static final Interpolator interpolator = new BounceInterpolator();

    private static final Random random = new Random();

    private static final int FRAMES = 80;
    private static final int DIAMETER = 50;

    private final Paint paint;

    private float left;
    private int frame;

    public Circle(int canvasWidth) {
        this(random.nextFloat() * (canvasWidth - DIAMETER), random.nextInt(FRAMES));
    }

    public Circle(float left, int frame) {
        paint = new Paint();
        paint.setColor(Color.WHITE);

        this.left = left;
        this.frame = frame;
    }

    public void draw(Canvas canvas) {
        float fraction = interpolator.getInterpolation((float) frame / FRAMES);

        float right = left + DIAMETER;
        float top = fraction * canvas.getHeight() - DIAMETER;
        float bottom = top + DIAMETER;

        canvas.drawOval(left, top, right, bottom, paint);

        frame = (frame + 1) % (FRAMES + 1);
    }

}
