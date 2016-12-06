package example.graphicsdemo.activity;

import android.graphics.Canvas;

import example.graphicsdemo.animation.Animation;

public class DemoAnimation implements Animation {

    private static final int COUNT = 10;

    private Bounce[] balls;

    @Override
    public void draw(Canvas canvas) {
        if (balls == null) {
            balls = new Bounce[COUNT];
            for (int i=0; i<balls.length; i++) {
                balls[i] = new Bounce(canvas.getWidth());
            }
        }

        canvas.drawARGB(255, 0, 0, 0);

        for (Bounce ball : balls) {
            ball.draw(canvas);
        }
    }

}
