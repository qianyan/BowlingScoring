public class Frame {
    private final Integer firstRoll;
    private Integer secondRoll;
    private Frame next;

    public Frame(Integer firstRoll) {
        this.firstRoll = firstRoll;
        this.secondRoll = 0;
    }

    public Frame() {
        this(0);
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

        if(isSpare()) {
            return firstRoll + secondRoll + next.firstRoll + next.finalScore();
        }
        return firstRoll + secondRoll + next.finalScore();
    }


    private boolean isSpare() {
        return firstRoll != 10 && firstRoll + secondRoll == 10;
    }
}
