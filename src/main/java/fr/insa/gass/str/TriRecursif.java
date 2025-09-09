package fr.insa.gass.str;

public class TriRecursif {
    public static int[] generateRandomIntArray(int size) {
        int[] arr = new int[size];
        java.util.Random rand = new java.util.Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(100) + 1; // 1 to 100 inclusive
        }
        return arr;
    }

    int[] arr = generateRandomIntArray(10);

    public static class Tri extends Thread {

        public int arr[];

        public Tri(int[] arr) {
            this.arr = arr;
        }

        public int[] getArr() {
            return arr;
        }

        @Override
        public void run() {
            if (this.arr.length <= 1) {
                // System.out.println(this.arr[0]);
                return;
            }
            int mid = this.arr.length / 2;
            int[] left = new int[mid];
            int[] right = new int[this.arr.length - mid];

            for (int i = 0; i < mid; i++) {
                left[i] = this.arr[i];
            }
            for (int i = mid; i < this.arr.length; i++) {
                right[i - mid] = this.arr[i];
            }
            Tri leftThread = new Tri(left);
            Tri rightThread = new Tri(right);
            leftThread.start();
            rightThread.start();
            try {
                leftThread.join();
                rightThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            joinSort(leftThread.getArr(), rightThread.getArr(), this.arr);
        }
    }

    public static void joinSort(int[] left, int[] right, int[] arr) {
        int right_index = 0;
        int left_index = 0;
        boolean left_done = false;
        boolean right_done = false;
        for (int i = 0; i < left.length + right.length; i++) {
            left_done = left_index > left.length - 1;
            right_done = right_index > right.length - 1;
            if (!left_done && !right_done) {
                if (left[left_index] < right[right_index]) {
                    arr[i] = left[left_index];
                    left_index++;
                } else {
                    arr[i] = right[right_index];
                    right_index++;
                }
            } else if (!left_done) {
                arr[i] = left[left_index];
                left_index++;
            } else if (!right_done) {
                arr[i] = right[right_index];
                right_index++;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = generateRandomIntArray(15);
        System.out.println("Avant tri:");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        Tri triThread = new Tri(arr);
        triThread.start();
        try {
            triThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Après tri:");
        for (int i : triThread.getArr()) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
