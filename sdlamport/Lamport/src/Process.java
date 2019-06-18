import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Process {

    long m = System.currentTimeMillis();
    int i;
    private int c;
    private String[] peerList;
    private ArrayList<String> ipList = new ArrayList<>();
    ArrayList<String[]> ips = new ArrayList<String[]>();

    public void initialize() {

//        BufferedReader buffRead = null;
//        try {
//            buffRead = new BufferedReader(new FileReader("C:\\Users\\17120064\\Downloads\\Lamport\\src\\Raiz\\arquivo.txt"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        String line = "";
//        while (true) {
//            try {
//                while ((line = buffRead.readLine()) != null) {
//
//                    peerList = line.split(" ");
//                    ips.add(peerList);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            if (buffRead != null) {
//                //         peerList = buffRead.readLine().split("\n");
//            }
//            break;
//        }
//        try {
//            buffRead.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        for (int i = 0; i <= peerList.length; i++) {

            ips.add(String.valueOf(peerList[0]).split(" "));
            System.out.println(ips.get(0)[0]);
            System.out.println(ips.get(0)[1]);
            System.out.println(ips.get(0)[2]);
        }
//
  }

    public void local() {

        c++;
        System.out.println(m);
        System.out.println("Evento Local. Relógio incrementado: " + c);

    }

    public void send() {

        byte[] send = new byte[1024];
        System.out.println(m);
    }

    public void receive(int t) {
        if (c < t) {
            c = t + 1;
        } else {
            c++;
        }
        System.out.println(m);
        System.out.println("mensagem");
    }

}
