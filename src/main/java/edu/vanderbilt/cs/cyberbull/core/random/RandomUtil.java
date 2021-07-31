package edu.vanderbilt.cs.cyberbull.core.random;

public class RandomUtil {
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public static String randomNumberString(int length){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++){
            builder.append(getRandomNumber(0,10));
        }
        return builder.toString();
    }
}
