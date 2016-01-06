import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class BowlingScoring {
    public int calculate(String inputScores) {
        return constructLinkedFrames(inputScores).finalScore();
    }

    private Frame constructLinkedFrames(String inputScores) {
        List<Integer> scores = Stream.of(inputScores.split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        Frame head = new Frame();
        Frame h = head;
        for (Iterator<Integer> it = scores.iterator(); it.hasNext(); ) {
            Integer next = it.next();
            Frame frame = new Frame(next);

            if (next != 10 && it.hasNext()) {
                frame.setSecondRoll(it.next());
            }

            head.next(frame);
            head = frame;
        }
        return h;
    }
}
