public interface PinGame {
    /*
    int[] frames = new int[10];
    int nFrame = 0;
    int nThrow = 0;
    int score = 0;
    boolean spare = false;
    boolean strike = false;
    */
    void roll(int pins);
    int score();
}
