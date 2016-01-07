import exception.InvalidScoreSequenceException;

public class Frame {
    private static final int STRIKE_SCORE = 10;
    private static final int TOTAL_FRAMES = 10;
    private final int number;
    private final Integer firstRoll;
    private Integer secondRoll;
    private Frame next;

    public Frame(int number) {
        this(number, 0);
    }

    public Frame(int number, Integer firstRoll) {
        this.number = number;
        this.firstRoll = firstRoll;
        this.secondRoll = 0;
    }

    private Integer guard(Integer scoreOfFrame) {
        if (scoreOfFrame > STRIKE_SCORE) {
            throw new InvalidScoreSequenceException();
        }
        return scoreOfFrame;
    }

    public void setSecondRoll(Integer secondRoll) {
        guard(firstRoll + secondRoll);
        this.secondRoll = secondRoll;
    }

    public void next(Frame frame) {
        this.next = frame;
    }

    public int finalScore() {
        final int basicScore = firstRoll + secondRoll;

        if (number > TOTAL_FRAMES) {
            return 0;
        }

        if (next == null) {
            return basicScore;
        }

        if (isSpare()) {
            return basicScore + next.firstRoll + next.finalScore();
        }

        if (isStrike()) {
            return basicScore + next.firstRoll + secondBallRoll() + next.finalScore();
        }

        return basicScore + next.finalScore();
    }

    private Integer secondBallRoll() {
        if (!next.isStrike()) {
            return next.secondRoll;
        }

        return next.next.firstRoll;
    }

    public boolean isStrike() {
        return firstRoll == STRIKE_SCORE;
    }

    private boolean isSpare() {
        return !isStrike() && firstRoll + secondRoll == STRIKE_SCORE;
    }
}
