public class BowlingGame implements PinGame{
    public int[] frames = new int[10];
    public int nFrame, nThrow, score = 0;
    public boolean spare = false;
    public boolean strike = false;

    public void roll(int pins) {
        if(nThrow == 0 && pins < 10) {
            frames[nFrame] += pins;
            if(spare || strike) {
                frames[nFrame+1] += pins;
                if(spare) {
                    nFrame++;
                    spare = false;
                }
            }
            nThrow++;
        } else if(nThrow == 1 && pins < 10) {
            frames[nFrame] += pins;
            if(strike) {
                nFrame++;
                frames[nFrame] += pins;
                strike = false;
            }
            if(frames[nFrame] == 10)
                spare = true;
            else {
                nFrame++;
                spare = false;
            }
            if(nFrame == 9 && spare) nThrow = 1;
            else nThrow = 0;
        } else if(nThrow == 0 && pins == 10) {
            if(frames[nFrame] == 30 && nFrame < (frames.length-1)) {
                nFrame++;
            }
            frames[nFrame] += pins;
            if(spare) {
                nFrame++;
                frames[nFrame] += pins;
                spare = false;
            }
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
