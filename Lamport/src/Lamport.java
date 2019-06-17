import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Lamport {
    private static String[] peerList;

    public static void main(String[] args) throws IOException, InterruptedException {


//        if (args.length != 2) {
//            System.out.println("syntax: java Lamport <relogio> <host> <port>");
//            return;
//        }

       Thread.sleep(2000);

        new LamportThread("4700").start();
        new LamportThread("4701").start();

    }
}
