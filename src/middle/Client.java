package middle;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
        public static void main(String[] args){
            try{
                Socket conn = new Socket("localhost", 8080);
                DataInputStream i = new DataInputStream(conn.getInputStream());
                DataOutputStream o = new DataOutputStream(conn.getOutputStream());
                Scanner sc = new Scanner(System.in);
                while(true){
                    System.out.println("give me 'a'");
                    int a = sc.nextInt();
                    System.out.println("give me 'b'");
                    int b = sc.nextInt();
                    o.writeInt(a);
                    o.writeInt(b);
                    o.flush();
                    int menu;
                    do {
                        System.out.println("choose what you want to do:");
                        System.out.println("1:Perimeter\n2:Area\n3:Is it a square\n4:Diagonal\n5:Exit");
                        menu = sc.nextInt();
                        o.writeInt(menu);
                        o.flush();
                        System.out.println(i.readUTF());
                    }while (menu != -1);
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }

}
