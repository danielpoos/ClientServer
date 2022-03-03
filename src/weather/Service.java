package weather;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Service implements Runnable {
    private HashMap<String, Weather> weatherHashMap;
    Socket socket;
    DataInputStream i;DataOutputStream o;
    public Service(Socket socket) throws IOException{
        this.socket = socket;
        weatherHashMap = new HashMap<>();
        this.i = new DataInputStream(socket.getInputStream());
        this.o = new DataOutputStream(socket.getOutputStream());
    }
    @Override
    public void run() {
        try {
            read();
            int menu;
            do {
                menu = i.readInt();
                switch (menu){
                    case 1: o.writeUTF(listWeathers()); break;
                    case 2: o.writeUTF(listCities()); break;
                    case 3: o.writeUTF(selectCity(i.readUTF())); break;
                    case 4: o.writeUTF(selectMin(i.readUTF())); break;
                    case 5: o.writeUTF(selectMax(i.readUTF())); break;
                    default:break;
                }
            } while (menu != 6);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }/*
    private String data(String input) throws IOException{
        o.writeUTF("REQUEST");
        o.writeUTF(input);
        return i.readUTF();
    }*/
    private String selectMax(String input) {
        int max = 0;
        if (input.toLowerCase().equals("today")){
            max = weatherHashMap.get("Antrim").getWeatherToday().getTempMax();
            for(Map.Entry<String, Weather> entry:weatherHashMap.entrySet()){
                if (max< entry.getValue().getWeatherToday().getTempMax())max = entry.getValue().getWeatherToday().getTempMax();
            }
        }
        if (input.toLowerCase().equals("tomorrow")){
            max = weatherHashMap.get("Antrim").getWeatherTomorrow().getTempMax();
            for(Map.Entry<String, Weather> entry:weatherHashMap.entrySet()){
                if (max< entry.getValue().getWeatherTomorrow().getTempMax())max = entry.getValue().getWeatherTomorrow().getTempMax();
            }
        }
        return String.format("The maximum temperature of %s is %d",input,max);
    }
    private String selectMin(String input) {
        int min = 0;
        if (input.toLowerCase().equals("today")){
            min = weatherHashMap.get("Antrim").getWeatherToday().getTempMax();
            for(Map.Entry<String, Weather> entry:weatherHashMap.entrySet()){
                if (min> entry.getValue().getWeatherToday().getTempMax())min = entry.getValue().getWeatherToday().getTempMax();
            }
        }
        if (input.toLowerCase().equals("tomorrow")){
            min = weatherHashMap.get("Antrim").getWeatherTomorrow().getTempMax();
            for(Map.Entry<String, Weather> entry:weatherHashMap.entrySet()){
                if (min> entry.getValue().getWeatherTomorrow().getTempMax())min = entry.getValue().getWeatherTomorrow().getTempMax();
            }
        }
        return String.format("The minimum temperature of %s is %d",input,min);
    }
    private String selectCity(String input) {
        return weatherHashMap.get(input).toString();
    }
    private String listWeathers() {
        StringBuilder listString = new StringBuilder();
        for(Map.Entry<String, Weather> entry:weatherHashMap.entrySet()){
            listString.append(entry.getValue()).append("\n");
        }
        return listString.toString();
    }
    private String listCities() {
        String listString = "";
        for(Weather w:weatherHashMap.values()){
            listString += w.getCounty()+"\n";
        }
        return listString;
    }
    public void read() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("weather.txt"));
            br.readLine();
            String row = br.readLine();
            while (row != null) {
                Weather w = new Weather(row);
                String country = w.getCounty();
                weatherHashMap.put(country, w);
                row = br.readLine();
            }
            br.close();
            for (Map.Entry<String, Weather> entry : weatherHashMap.entrySet()) {
                System.out.println(entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
