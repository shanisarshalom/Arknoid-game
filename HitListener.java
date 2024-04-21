//209583038 Shani Sar Shalom

/**
 * implement when objects want to be notified of hit events.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit - the block that have been hit.
     * @param hitter - the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}