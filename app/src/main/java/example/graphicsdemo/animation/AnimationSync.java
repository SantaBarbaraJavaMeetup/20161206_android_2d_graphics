package example.graphicsdemo.animation;

public class AnimationSync {

    private boolean paused;

    public synchronized void pause() {
        paused = true;
    }

    public synchronized void resume() {
        paused = false;
        notifyAll();
    }

    public synchronized boolean await() {
        while (paused) {
            try {
                wait();
            } catch (InterruptedException e) {
                return false;
            }
        }

        return true;
    }

}
