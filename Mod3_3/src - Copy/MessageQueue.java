import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue {
    private static int n_ids = 1;

    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        int m, n;
        BlockingQueue<Message> queue = new LinkedBlockingQueue<>(10);
        System.out.println("Enter no of Producers: ");
        m = input.nextInt();
        System.out.println("Enter no of Consumers: ");
        n = input.nextInt();

        List<Producer> producers = new ArrayList();
        for (int i = 0; i < m; i++) {
            Producer p = new Producer(queue, n_ids++);
            producers.add(p);
        }

        List<Consumer> consumers = new ArrayList();
        for (int i = 0; i < n; i++) {
            Consumer c = new Consumer(queue, n_ids++, m>n);
            consumers.add(c);
        }

        List<Thread> pthreads = new ArrayList<Thread>();
        for (int i = 0; i < m; i++) {
            Thread t = new Thread(producers.get(i));
            pthreads.add(t);
            t.setDaemon(true);
            t.start();
        }

        List<Thread> cthreads = new ArrayList<Thread>();
        for (int i = 0; i < n; i++) {
            Thread t = new Thread(consumers.get(i));
            cthreads.add(t);
            t.setDaemon(true);
            t.start();
        }

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < m; i++) {
            producers.get(i).stop();
        }
//        List<Thread> lthread = null;
//        if (m > n)
//            lthread = cthreads;
//        else
//            lthread = pthreads;
//        for (Thread t : lthread){
//            t.join();
//        }
    }
}
