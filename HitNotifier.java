//209583038 Shani Sar Shalom

/**
 * indicate that objects that implement it send notifications when being hit.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * @param hl - hit listener.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl - hit listener.
     */
    void removeHitListener(HitListener hl);
}
