import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BowlingScoringTest {

    private BowlingScoring bowlingScoring;

    @Before
    public void setUp() throws Exception {
        bowlingScoring = new BowlingScoring();
    }

    @Test
    public void should_add_up_all_numbers_when_there_is_basic_score_keeping() throws Exception {

        int finalScore = bowlingScoring.calculate("1 2 3 4");

        assertThat(finalScore, is(10));
    }

    @Test
    public void should_plus_the_next_ball_roll_when_there_has_the_spare() throws Exception {

        int finalScore = bowlingScoring.calculate("9 1 9 1");

        assertThat(finalScore, is(29));
    }

    @Test
    public void should_plus_the_next_two_ball_roll_when_there_has_the_strike() throws Exception {

        int finalScore = bowlingScoring.calculate("1 1 1 1 10 1 1");

        assertThat(finalScore, is(18));
    }

    @Test
    public void should_score_300_pins_when_all_10_frames_are_strike() throws Exception {

        int finalScore = bowlingScoring.calculate("10 10 10 10 10 10 10 10 10 10 10 10");

        assertThat(finalScore, is(300));
    }
}
