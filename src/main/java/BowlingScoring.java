import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class BowlingScoring {

    public int calculate(String inputScores) {
        List<Integer> scores = Stream.of(inputScores.split(" "))
                .map(Integer::parseInt)
                .collect(toList());
        return constructLinkedFrames(scores).finalScore();
    }

    private Frame constructLinkedFrames(List<Integer> scores) {
        Frame pointer = new Frame(0);
        Frame head = pointer;
        int number = 1;
        for (Iterator<Integer> it = scores.iterator(); it.hasNext(); ) {
            Frame frame = new Frame(number++, it.next());

            if (!frame.isStrike() && it.hasNext()) {
                frame.setSecondRoll(it.next());
            }

            pointer.next(frame);
            pointer = frame;
        }
        return head;
    }

}
