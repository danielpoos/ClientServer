package weather;

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
            String input;
            do {
                System.out.println("Please choose from the menu");
                System.out.println("1:List the weather\n2:List cities\n3:Select a city\n4:Select min temperature\n5:Select max temperature\n6:Exit");
                menu = Integer.parseInt(sc.nextLine());
                o.writeInt(menu);
                /*String result = i.readUTF();
                while (result == "REQUEST"){
                    System.out.println(i.readUTF());
                    input = sc.nextLine();
                    o.writeUTF(input);
                    result = i.readUTF();
                }*/
                switch (menu) {
                    case 3 -> {
                        System.out.println("Name a city: (Case sensitive)");
                        input = sc.nextLine();
                        o.writeUTF(input);
                    }
                    case 4, 5 -> {
                        System.out.println("Today or tomorrow?");
                        input = sc.nextLine();
                        o.writeUTF(input);
                    }
                    case 6 -> System.out.println("Kiléptél");
                    default -> {
                    }
                }
                o.flush();
                System.out.println(i.readUTF());
            } while (menu != 6);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
