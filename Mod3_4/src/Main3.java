import java.util.concurrent.*;

public class Main3 {
    static Semaphore semaphore = new Semaphore(1);

    private static void nap(int millisecs) {
        try {
            Thread.sleep(millisecs);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void addProc(HighLevelDisplay d) throws InterruptedException {

        // Add a sequence of addRow operations with short random naps.
        for (int i = 0; i < 20; i++) {
            semaphore.acquire();
            d.addRow("AAAAAAAAAAAA  " + i);
            semaphore.release();
            nap(800);
        }

    }

    private static void deleteProc(HighLevelDisplay d) throws InterruptedException {

        // Add a sequence of deletions of row 0 with short random naps.
        for (int i = 0; i < 20; i++) {
            semaphore.acquire();
            d.deleteRow(0);
            semaphore.release();
            nap(800);
        }
    }

    public static void main(String[] args) {
        final HighLevelDisplay d = new JDisplay2();

        new Thread() {
            public void run() {
                try {
                    addProc(d);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        new Thread() {
            public void run() {
                try {
                    deleteProc(d);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}