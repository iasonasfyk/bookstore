package common.utilities;

import java.util.Random;

public class RandomGenerator {

    protected static Random randomInt = new Random();

    public static Integer generateRandomIntegerGivenTopBound(int bound) {
        return randomInt.nextInt(bound);
    }
}
