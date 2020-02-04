import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue<Message> queue;
    private boolean running = true;
    private int id;
    private int noOfMessages = 0;

    public Producer(BlockingQueue<Message> q, int n, int count) {
        queue = q;
        id = n;
        noOfMessages = count;
    }

    public void stop() {
        running = false;
    }

    public void run() {
        int count = 0;
        while (running) {
            int n = RandomUtils.randomInteger();
            try {
                Thread.sleep(n);
                Message msg = new Message("message-" + n);
                queue.put(msg); // Put the message in the queue
                count++;
                RandomUtils.print("Produced " + msg.get(), id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Put the stop message in the queue
        Message msg = new Message("stop");
        try {
            for (int i = 0; i < noOfMessages; i++) {
                queue.put(msg); // Put this final message in the queue
            }

//            synchronized (messageCount) {
//                if (messageCount < noOfConsumers) {
//                    queue.put(msg); // Put this final message in the queue
//                    messageCount++;
//                }
//            }

//            while (stopMessageCount == 0) {
//                if (stopMessageCount > 0) {
//                    queue.put(msg);
//                    stopMessageCount--;
//                } else {
//                    queue.take();
//                    stopMessageCount++;
//                }
//            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RandomUtils.print("Messages sent: " + count, id);
    }
}
