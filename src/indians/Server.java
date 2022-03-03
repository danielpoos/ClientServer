package indians;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args){
        ExecutorService exeService = Executors.newCachedThreadPool();
        try{
            ServerSocket socket =new ServerSocket(8080);
            while(true){
                Socket conn = socket.accept();
                InetAddress client = conn.getInetAddress();
                System.out.println(client.getHostAddress());
                Service clientService = new Service(conn);
                exeService.submit(clientService);
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
