package fr.insa.gass.str;

public class PairRunnable implements Runnable {

    public String nom;

    public PairRunnable(String nom) {
        this.nom = nom;
    }

    public PairRunnable() {
        this.nom = "Thread sans nom";
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            utils.randPause(100);
            if (i % 2 == 0) {
                System.out.println(nom + ": " + i);
            }
        }
        System.out.println("Fin du thread");
    }

    public static void main(String[] args) {
        PairRunnable pairRunnable = new PairRunnable();
        Thread thread = new Thread(pairRunnable);
        thread.start();
        Thread thread2 = new Thread(new PairRunnable("Thread 2"));
        thread2.start();
    }
}
