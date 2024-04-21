//209583038 Shani Sar Shalom

/**
 * charge of removing balls and updating an available balls counter.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor of the class.
     * @param game - the current game.
     * @param remainingBalls - counter of balls in the game.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * if the ball hit the death-region block - remove it.
     * @param deathRegion - the block that indicate that the ball should remove.
     * @param ball - ball from the current game.
     */
    public void hitEvent(Block deathRegion, Ball ball) {
        ball.removeFromGame(this.game);
        // update the counter.
        remainingBalls.decrease(1);
    }
}
