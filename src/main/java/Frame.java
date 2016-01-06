public class Frame {
    private static final int STRIKE_SCORE = 10;
    private static final int TEN_FRAMES = 10;
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

    public void setSecondRoll(Integer secondRoll) {
        this.secondRoll = secondRoll;
    }

    public void next(Frame frame) {
        this.next = frame;
    }

    public int finalScore() {
        final int basicScore = firstRoll + secondRoll;

        if (next == null) {
            return basicScore;
        }

        if (number > TEN_FRAMES) {
            return 0;
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

    private boolean isStrike() {
        return firstRoll == STRIKE_SCORE;
    }

    private boolean isSpare() {
        return !isStrike() && firstRoll + secondRoll == STRIKE_SCORE;
    }
}
