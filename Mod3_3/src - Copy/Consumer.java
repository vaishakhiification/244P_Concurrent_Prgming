import sun.applet.Main;

import javax.swing.text.html.HTMLDocument;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<Message> queue;
    private int id;
    private boolean isUneven = false;
    public Consumer(BlockingQueue<Message> q, int n, boolean isUneven) {
        queue = q;
        id = n;
        this.isUneven = isUneven;
    }

    public void run() {
        Message msg = null;
        int count = 0;
//        boolean flag = false;
        do {
//            flag = false;
            try {
                msg = queue.take(); // Get a message from the queue

//                if (msg.get().equals("stop") && !isUneven) {
//                    queue.put(new Message("stop"));
////                    flag = true;
//                } else {
                    count++;
//                }
                RandomUtils.print("Consumed " + msg.get(), id);
                Thread.sleep(RandomUtils.randomInteger());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (msg.get() != "stop");
        // Don't count the "stop" message
        //  if (!flag)
        count--;
//        while (queue.peek() != null){
//
//        }
        RandomUtils.print("Messages received: " + count, id);
    }

    public boolean checkProducerMessages() throws InterruptedException {
//        if (queue.size() == 0)
//            return false;
//        Message msg = queue.peek();
//        if (msg.equals("stop")) {
//            if (queue.size() == 1) {
//                return false;
//            } else {
//                msg = queue.take();
//                return checkProducerMessages();
//            }
//        }

//        do {}
        return false;
    }
}
