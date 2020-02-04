import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Multi implements Runnable {

    @Override
    public void run() {
        try {
            while (true) { //thread.interrupted()
                System.out.println("Hello World! I'm thread " + Thread.currentThread().getName() + ". The time is " + LocalDateTime.now());
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread Killed!");
        }
    }
}


public class Exercise_1 {
    static List<Thread> threadList = new ArrayList<>();

    public static Thread getThreadByName(String threadName) {
        Thread temp = null;
        for (Thread t : threadList) {
            if (t.getName().equals(threadName)) {
                temp = t;
            }
        }
        return temp;
    }

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);

        String ch;
        String[] ip;
        Integer count = 0;
        do {
            System.out.println("Here are your options: ");
            System.out.println("a - Create a new thread");
            System.out.println("b - Stop a given thread");
            System.out.println("c - Stop all threads and exit this program");
            ch = input.nextLine();

            ip = ch.trim().split(" ");


            switch (ip[0]) {
                case "a":
                    Thread t = new Thread(new Multi());
                    threadList.add(t);
                    t.setName(count.toString());
                    t.start();
                    count++;
                    break;
                case "b":
                    Thread ts = getThreadByName(ip[1]);
                    ts.interrupt();
                    break;
                case "c":
                    for (Thread ti : threadList) {
                        ti.interrupt();
                    }
                    break;
                default:
                    break;
            }
        } while (!ip[0].equals("c"));
    }
}
