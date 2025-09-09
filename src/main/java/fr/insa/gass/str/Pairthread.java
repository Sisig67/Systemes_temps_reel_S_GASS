package fr.insa.gass.str;

public class Pairthread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
        System.out.println("Fin du thread");
    }

    public static void main(String[] args) {
        Pairthread pairthread = new Pairthread();
        pairthread.start();
    }
}
