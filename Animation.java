import biuoop.DrawSurface;

/**
 * interface include template-methods to run animation.
 */
public interface Animation {
    /**
     * call this method to run one frame of animation.
     * @param d - surface to draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * return to stop animation if required.
     * @return true if need to stop animation. false otherwise.
     */
    boolean shouldStop();
}