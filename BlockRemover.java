//209583038 Shani Sar Shalom

/**
 * charge of removing blocks from the game, as well as keeping count of the
 * number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor of the class.
     * @param game - the current game.
     * @param remainingBlocks - counter of the blocks in the game.
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * remove blocks that are hit from the game.
     * @param beingHit - blocks that are hit.
     * @param hitter - the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
    }
}