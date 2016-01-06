import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BowlingScoringTest {
    @Test
    public void should_add_up_all_numbers_when_there_is_basic_score_keeping() throws Exception {
        BowlingScoring bowlingScoring = new BowlingScoring();

        int finalScore = bowlingScoring.calculate("1 2 3 4");

        assertThat(finalScore, is(10));
    }
}
