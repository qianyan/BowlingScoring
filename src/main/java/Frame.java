public class Frame {
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
        if (next == null) {
            return firstRoll + secondRoll;
        }

        if(number > 10) {
            return 0;
        }

        if (isSpare()) {
            return firstRoll + secondRoll + next.firstRoll + next.finalScore();
        }

        if (isStrike()) {
            return firstRoll + next.firstRoll + secondBallRoll() + next.finalScore();
        }

        return firstRoll + secondRoll + next.finalScore();
    }

    private Integer secondBallRoll() {
        if(!next.isStrike()) {
            return next.secondRoll;
        }

        if (next.next != null) {
            return next.next.firstRoll;
        }

        return next.secondRoll;
    }

    private boolean isStrike() {
        return firstRoll == 10;
    }

    private boolean isSpare() {
        return !isStrike() && firstRoll + secondRoll == 10;
    }
}
