package client_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        System.out.println("on");
        try{
            ServerSocket socket =new ServerSocket(8080);
            Socket conn = socket.accept();
            DataInputStream i = new DataInputStream(conn.getInputStream());
            DataOutputStream o = new DataOutputStream(conn.getOutputStream());
            InetAddress client = conn.getInetAddress();
            while(true){
                int radius = i.readInt();
                double k = 2*radius*Math.PI;
                o.writeDouble(k);
                double a = Math.pow(radius,2)*Math.PI;
                o.writeDouble(a);
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
