package indians;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket conn = new Socket("localhost", 8080);
            DataInputStream i = new DataInputStream(conn.getInputStream());
            DataOutputStream o = new DataOutputStream(conn.getOutputStream());
            Scanner sc = new Scanner(System.in);
            int menu;
            do {
                System.out.println("Please choose from the menu");
                System.out.println("1:List\n2:How many different tools\n3:How many people are there in this tribe\n4:Most people in tribe\n5:Seminole ratio\n6:\n7:");
                menu = sc.nextInt();
                o.writeInt(menu);
                o.flush();
                System.out.println(i.readUTF());
            } while (menu != -1);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
