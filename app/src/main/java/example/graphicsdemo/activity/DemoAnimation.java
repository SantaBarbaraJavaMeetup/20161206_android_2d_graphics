package example.graphicsdemo.activity;

import android.graphics.Canvas;

import example.graphicsdemo.animation.Animation;

public class DemoAnimation implements Animation {

    private static final int COUNT = 10;

    private Ball[] array;

    @Override
    public void draw(Canvas canvas) {
        if (array == null) {
            array = new Ball[COUNT];
            for (int i = 0; i< array.length; i++) {
                array[i] = new Ball(canvas.getWidth());
            }
        }

        canvas.drawARGB(255, 0, 0, 0);

        for (Ball ball : array) {
            ball.draw(canvas);
        }
    }

}
