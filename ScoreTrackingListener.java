//209583038 Shani Sar Shalom

/**
 * update counter when blocks are being hit and removed.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor of class.
     * @param scoreCounter - the counter to update.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * update the score after hit event.
     * @param beingHit - the block that have been hit.
     * @param hitter - the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(5);
    }
}