import java.util.Random;

public class LamportPeer {

    Process p = new Process();


    public void run() throws InterruptedException {

        while (true) {

            Thread.sleep(500);

            Random random = new Random();
            p.initialize();
            int i = random.nextInt(3);
            if(i ==0){i++;}
            switch (i) {
                case 1:
                    p.local();
                    break;
                case 2:
                    p.send();
                    break;
            }
        }
    }
}
