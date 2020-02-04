import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class MessageQueue {
    private static int n_ids;

    public static void main(String[] args) {
        BlockingQueue<Message> queue = new LinkedBlockingDeque<>(10);
        int m, n;
        m = Integer.parseInt(args[0]);  //producers
        n = Integer.parseInt(args[1]);  //consumers
        int extraMessages = n % m;
//        System.out.println("Per Producer: " + (n / m));
//        System.out.println("Extra: " + extraMessages);

        List<Producer> producers = new ArrayList();
        for (int i = 0; i < m; i++) {
            Producer p = new Producer(queue, n_ids++, n / m);
            producers.add(p);
        }

        List<Consumer> consumers = new ArrayList();
        for (int i = 0; i < n; i++) {
            Consumer c = new Consumer(queue, n_ids++);
            consumers.add(c);
        }

        for (int i = 0; i < m; i++) {
            new Thread(producers.get(i)).start();
        }

        for (int i = 0; i < n; i++) {
            new Thread(consumers.get(i)).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < m; i++) {
            producers.get(i).stop();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < extraMessages; i++) {
            try {
                queue.put(new Message("stop"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
