package client_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
        public static void main(String[] args){
            System.out.println("on");
            try{
                Socket conn = new Socket("localhost", 8080);
                DataInputStream i = new DataInputStream(conn.getInputStream());
                DataOutputStream o = new DataOutputStream(conn.getOutputStream());
                Scanner sc = new Scanner(System.in);
                while(true){
                    System.out.println("give me the radius");
                    int r = sc.nextInt();
                    o.writeInt(r);
                    o.flush();
                    System.out.printf("Ker: %.2f\n", i.readDouble());
                    System.out.printf("Ter: %.2f\n", i.readDouble());
                }
            }catch (IOException e){
                System.out.println(e);
            }
        }

}
