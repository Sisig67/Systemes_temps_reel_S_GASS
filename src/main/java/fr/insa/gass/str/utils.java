package fr.insa.gass.str;

public class utils {
    public static void randPause(long maxMillis) {
        long millis = (long) ((Math.random() * 1.5 + 0.5) * maxMillis);
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
