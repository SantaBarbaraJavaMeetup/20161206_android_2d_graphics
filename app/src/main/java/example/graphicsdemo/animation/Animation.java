package example.graphicsdemo.animation;

import android.graphics.Canvas;

/**
 * Simple animation abstraction.
 */
public interface Animation {

    /**
     * Paint animation on {@link Canvas}.
     */
    void draw(Canvas canvas);

}
