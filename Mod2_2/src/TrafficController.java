public class TrafficController {
    Boolean isPresent = false;

    TrafficController() {
        isPresent = false;
    }

    public synchronized void enterLeft() {
        try {
            while (isPresent) {
                Thread.sleep(100);
            }
            isPresent = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void enterRight() {
        try {
            while (isPresent) {
                Thread.sleep(100);
            }
            isPresent = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void leaveLeft() {
        isPresent = false;
    }

    public void leaveRight() {
        isPresent = false;
    }
}