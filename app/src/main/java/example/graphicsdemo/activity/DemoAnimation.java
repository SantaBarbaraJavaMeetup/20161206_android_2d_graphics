package example.graphicsdemo.activity;

import android.graphics.Canvas;

import example.graphicsdemo.animation.Animation;

public class DemoAnimation implements Animation {

    private static final int COUNT = 50;

    private Circle[] circles;

    @Override
    public void draw(Canvas canvas) {
        if (circles == null) {
            circles = new Circle[COUNT];
            for (int i = 0; i< circles.length; i++) {
                circles[i] = new Circle(canvas.getWidth());
            }
        }

        canvas.drawARGB(255, 0, 0, 0);

        for (Circle circle : circles) {
            circle.draw(canvas);
        }
    }

}
