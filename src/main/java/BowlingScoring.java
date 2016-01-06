import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class BowlingScoring {

    private static final int STRIKE_SCORE = 10;

    public int calculate(String inputScores) {
        List<Integer> scores = Stream.of(inputScores.split(" "))
                .map(Integer::parseInt)
                .collect(toList());
        return constructLinkedFrames(scores).finalScore();
    }

    private Frame constructLinkedFrames(List<Integer> scores) {
        Frame pointerOfFrame = new Frame();
        Frame head = pointerOfFrame;
        for (Iterator<Integer> it = scores.iterator(); it.hasNext(); ) {
            Integer score = it.next();
            Frame frame = new Frame(score);

            if (score != STRIKE_SCORE && it.hasNext()) {
                frame.setSecondRoll(it.next());
            }

            pointerOfFrame.next(frame);
            pointerOfFrame = frame;
        }
        return head;
    }
}
