public class BowlingGame implements PinGame{
    private final int[] frames = new int[10];
    private int nFrame, score = 0;
    private boolean spare = false;
    private boolean strike = false;

    private boolean throwOne = true;
    private boolean throwTwo = false;


    private boolean isStrike(int pins) {
        return throwOne && pins <= 10;
    }
    private boolean isSpare() {
        return frames[nFrame] == 10;
    }
    private boolean isFrameFull() {
        return frames[nFrame] == 30 && nFrame < (frames.length-1);
    }
    private boolean lastFrame() {
        return (nFrame + 1) == 9;
    }


    private void spareBonus(int pins) {
        nFrame++;
        frames[nFrame] += pins;
        spare = false;
    }
    private void strikeBonus(int pins) {
        if (throwOne) {
            frames[nFrame + 1] += pins;
            //3 Strikes in a row!
            if(isFrameFull()) {
                nFrame++;
                frames[nFrame + 1] += pins;
            }
        } else {
            nFrame++;
            frames[nFrame] += pins;
            strike = false;
        }
    }

    public void roll(int pins) {
        if(isFrameFull())
            nFrame++;
        frames[nFrame] += pins;
        if(spare)
            spareBonus(pins);
        if(strike)
            strikeBonus(pins);

        if(throwOne && pins < 10) {
            throwOne = false;
            throwTwo = true;
        } else if(throwTwo) {
            if(isSpare())
                spare = true;
            else
                nFrame++;
            throwOne = true;
            throwTwo = false;
        } else if(isStrike(pins)) {
            strike = true;
        }
    }

    public int score() {
        int i = 1;
        for(int frameValue : frames) {
            score += frameValue;
            System.out.println("frame " + i + ":\t" + frameValue + "\t->\t" + score);
            i++;
        }
        return score;
    }
}
