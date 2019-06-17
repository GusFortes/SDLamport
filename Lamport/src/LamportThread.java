import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

public class LamportThread extends Thread {

    protected DatagramSocket socket;
    DatagramPacket packet;
    protected String service;
    String[] peerList;
    ArrayList<String[]> ips = new ArrayList<>();
    Random random = new Random();
    int relogio = 1;
    int id;


    public LamportThread() throws IOException {
        this("LamportThread");
    }

    public LamportThread(String name) throws IOException {
        super(name);
        service = name;
        socket = new DatagramSocket(Integer.parseInt(name));
    }


    public void run() {


        BufferedReader buffRead = null;
        try {
            buffRead = new BufferedReader(new FileReader("/home/17120064/Downloads/Lamport/src/Raiz/arquivo.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String linha;
        while (true) {
            try {
                while ((linha = buffRead.readLine()) != null) {

                    peerList = linha.split(" ");
                    ips.add(peerList);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
        try {
            buffRead.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        while (true) {
            while (true) {


//                ips.add(String.valueOf(peerList[0]).split(" "));
//                System.out.println(ips.get(0)[0]);
//                System.out.println(ips.get(0)[1]);
//                System.out.println(ips.get(0)[2]);

                //random para envio e evento local

                System.out.println("Porta: "+socket.getLocalPort());
                //recebe mensagem e processa
                if (socket.getLocalPort() == 4701) {
                    byte[] buf = new byte[1024];
                    packet = new DatagramPacket(buf, buf.length);
                    try {
                        socket.receive(packet);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String msgReceive = new String(packet.getData());
                    System.out.println("Mensagem recebida");
                    System.out.println(msgReceive);

                    String[] recebido = msgReceive.split(" ");
                    int relogioReceive = Integer.parseInt(recebido[2]);

                    System.out.print("Relógio incrementado para: ");

                    if (relogioReceive > relogio) {

                        relogio = relogioReceive + 1;
                    } else {
                        relogio++;
                    }
                    System.out.println(relogio);
                } else {
                    if (socket.getLocalPort() == 4700) {
                        byte[] buf = new byte[1024];
                        packet = new DatagramPacket(buf, buf.length);
                        while (true) {
                            int i = random.nextInt(3);
                            if (i == 0) {
                                i++;
                            }
                            switch (i) {
                                case 1:
                                    relogio++;
                                    System.out.println("Evento local. Relógio incrementado: " + relogio);
                                    break;
                                case 2:
                                    //variável random com o tamanho igual a quantidade de nodos
                                    int nodos = random.nextInt(peerList.length);



                                    String msg = System.currentTimeMillis() + " " + id + " " + relogio + " " + nodos;
                                    buf = msg.getBytes();
                                    packet = new DatagramPacket(buf, buf.length);
                                    InetAddress address = null;
                                    try {
                                        address = InetAddress.getByName(ips.get(nodos)[1]);
                                    } catch (UnknownHostException e) {
                                        e.printStackTrace();
                                    }
                                    int port = Integer.parseInt(ips.get(nodos)[2]);
                                    packet = new DatagramPacket(buf, buf.length, address, port);
                                    try {
                                        socket.send(packet);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    break;
                            }
                        }
                    }
                }
                socket.close();
            }

        }
    }
}





