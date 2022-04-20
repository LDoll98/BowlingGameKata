public class BowlingGame implements PinGame{
    private final int[] frames = new int[10];
    private final int MAX_PINS = 10;
    private final int MAX_FRAME_POINTS = 30;
    private int frameIdx, score = 0;
    private boolean spare = false;
    private boolean strike = false;

    private boolean throwOne = true;
    private boolean throwTwo = false;


    private boolean isStrike(int pins) {
        return throwOne && pins == MAX_PINS;
    }
    private boolean isSpare() {
        return frames[frameIdx] == MAX_PINS;
    }
    private boolean isFrameFull() {
        return frames[frameIdx] == MAX_FRAME_POINTS && frameIdx < (frames.length-1);
    }
    private boolean lastFrame() {
        return (frameIdx) == 9;
    }


    private void spareBonus(int pins) {
        frameIdx++;
        frames[frameIdx] += pins;
        spare = false;
    }
    private void strikeBonus(int pins) {
        if (throwOne) {
            frames[frameIdx + 1] += pins;
            if(isFrameFull()) {
                frameIdx++;
                if(lastFrame())
                    frames[frameIdx] += pins;
                else
                    frames[frameIdx + 1] += pins;
            }
        } else {
            frameIdx++;
            frames[frameIdx] += pins;
            strike = false;
        }
    }
    private void changeThrow(boolean changeToSecond) {
        if(changeToSecond) {
            throwOne = false;
            throwTwo = true;
        } else {
            throwOne = true;
            throwTwo = false;
        }
    }

    public void roll(int pins) {
        if(isFrameFull())
            frameIdx++;
        frames[frameIdx] += pins;
        if(spare && !lastFrame())
            spareBonus(pins);
        if(strike)
            strikeBonus(pins);

        if(throwOne && pins < 10) {
            changeThrow(true);
        } else if(throwTwo) {
            if(isSpare())
                spare = true;
            else
                frameIdx++;
            changeThrow(false);
        } else if(isStrike(pins)) {
            strike = true;
        }
    }

    public int score() {
        int frameNumber = 1;
        for(int frameValue : frames) {
            score += frameValue;
            System.out.println("frame " + frameNumber + ":\t" + frameValue + "\t->\t" + score);
            frameNumber++;
        }
        return score;
    }
}
