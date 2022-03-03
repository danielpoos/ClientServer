package indians;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Service implements Runnable{
    private Socket socket;
    private List<Indian> indians;
    public Service(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            read();
            DataInputStream i = new DataInputStream(socket.getInputStream());
            DataOutputStream o = new DataOutputStream(socket.getOutputStream());
                int menu;
                do {
                    menu = i.readInt();
                    switch (menu){
                        case 1 : o.writeUTF(listAllIndians()); break;
                        case 2 : o.writeUTF(String.valueOf(differentTools())); break;
                        case 3 : o.writeUTF(tribes()); break;
                        case 4 : o.writeUTF(String.valueOf(tribeCount())); break;
                        /*case 5 : o.writeUTF(); break;
                        case 6 : o.writeUTF(); break;*/
                    }
                    o.flush();
                }while (menu != -1);
        }catch (IOException e){
            System.out.println(e);
        }
    }

    private int tribeCount() {ArrayList<String> tribes = null;
        for(Indian i : indians) if (!tribes.contains(i.getTribe())) tribes.add(i.getTribe());
        return tribes.size();

    }

    private String tribes() {
        ArrayList<String> tribes = null;
        String s ="";
        for(Indian i : indians) if (!tribes.contains(i.getTribe())) tribes.add(i.getTribe());
        indians.stream().filter(i -> i.getTribe() == tribes.get(0));
        return s;
    }

    private int differentTools() {
        ArrayList<String> tools = null;
        for(Indian i : indians) for(String item:i.getTools()) if (!tools.contains(item)) tools.add(item);
        return tools.size();
    }

    private String listAllIndians() {
        String s = "";
        for(Indian i: indians) s+=i+"\n";
        return s;
    }

    private void read(){
        indians = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader("indianok.txt"));
            String row = br.readLine();
            while (row != null){
                indians.add(new Indian(row));
                row = br.readLine();
            }
            br.close();
        }catch(Exception e ){
            e.printStackTrace();
        }
    }
}
