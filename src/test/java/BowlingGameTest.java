import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BowlingGameTest {

    @Test
    void calculate_more_strikes_in_a_row() {
        BowlingGame game = new BowlingGame();
        game.roll(10);
        game.roll(10);
        game.roll(10);

        game.roll(10);
        game.roll(10);
        game.roll(10);

        game.roll(10);
        int result = game.score();
        assertEquals(result, 70);
    }

    @Test
    void calculate_one_frame() {
        BowlingGame game = new BowlingGame();
        game.roll(3);
        game.roll(4);

        int frame1 = game.score();
        assertEquals(frame1, (3+4));
    }

    @Test
    void calculate_two_frame() {
        BowlingGame game = new BowlingGame();
        game.roll(3);
        game.roll(4);

        game.roll(5);
        game.roll(1);

        int result = game.score();

        assertEquals(result, ((3+4) + (5+1)));
    }

    @Test
    void calculate_spare_frame() {
        BowlingGame game = new BowlingGame();
        game.roll(7);
        game.roll(3);   //spare
        game.roll(5);   //spare-Bonus
        game.roll(2);
        //new Throw
        game.roll(1);
        game.roll(4);

        int result = game.score();

        assertEquals(result, (7+3+5) + (5+2) + (1+4));
    }

    @Test
    void calculate_strike_frame() {
        BowlingGame game = new BowlingGame();
        game.roll(10);
        game.roll(4);
        game.roll(1);

        int result = game.score();
        assertEquals(result, (10+4+1) + (4+1));
    }

    @Test
    void calculate_strike_after_spare() {
        BowlingGame game = new BowlingGame();
        game.roll(7);
        game.roll(3);   //spare

        game.roll(10);  //strike

        game.roll(5);
        game.roll(1);

        int result = game.score();
        assertEquals(result, (7+3+10) + (10+5+1) + (5+1));
    }

    @Test
    void calculate_one_example_game() {
        BowlingGame game = new BowlingGame();
        game.roll(1);
        game.roll(4);

        game.roll(4);
        game.roll(5);

        game.roll(6);
        game.roll(4); //Spare

        game.roll(5);
        game.roll(5); //spare

        game.roll(10); //strike

        game.roll(0);
        game.roll(1);

        game.roll(7);
        game.roll(3); //spare

        game.roll(6);
        game.roll(4); //spare

        game.roll(10); //strike

        game.roll(2);
        game.roll(8); //spare
        //game.roll(6);

        int result = game.score();
        assertEquals(result, 133-6);
    }
}
