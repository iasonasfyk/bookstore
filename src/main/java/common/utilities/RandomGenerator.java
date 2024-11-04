package common.utilities;

import java.util.Random;

public class RandomGenerator {

    protected static Random randomInt = new Random();

    public static int generateRandomIntGivenTopBound(int bound) {
        int randInt = randomInt.nextInt(bound);
        while (randInt==0) {
            randInt = randomInt.nextInt(bound);
        }
        return randInt;
    }
}
