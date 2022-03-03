package middle;

import org.w3c.dom.ls.LSOutput;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Service implements Runnable{
    Socket socket;

    public Service(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            DataInputStream i = new DataInputStream(socket.getInputStream());
            DataOutputStream o = new DataOutputStream(socket.getOutputStream());
            while (true){
                int a = i.readInt();
                int b = i.readInt();
                int menu;
                do {
                    menu = i.readInt();
                    switch (menu){
                        case 1 : o.writeUTF(perimeter(a,b)); break;
                        case 2 : o.writeUTF(area(a,b)); break;
                        case 3 : o.writeUTF(isSquare(a,b)); break;
                        case 4 : o.writeUTF(diagonal(a,b));; break;
                    }
                    o.flush();
                }while (menu != 5);

            }
        }catch (IOException e){
            System.out.println(e);
        }
    }

    private String diagonal(int a, int b) {
        return "The diagonal is: "+(Math.sqrt((Math.pow(a,2)+Math.pow(b,2))));
    }


    private String isSquare(int a, int b) {
        return (a == b) ? "This is a square":"This is not a square";
    }

    private String area(int a, int b) {
        return "The area is: "+(a*b);
    }

    private String perimeter(int a,int b) {
        return "The perimeter is: "+(2*(a+b));
    }
}
