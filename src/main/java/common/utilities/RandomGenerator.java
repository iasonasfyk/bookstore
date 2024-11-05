package common.utilities;

import java.util.Random;

public class RandomGenerator {

    protected static Random randomInt = new Random();

    public static int generateRandomIntGivenTopBound(int bound) {
        return randomInt.nextInt(bound);
    }
}
