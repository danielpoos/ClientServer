package weather;

public class Forecast {
    private String forecast;

    private int tempMin, tempMax;
    public Forecast(String forecast, String temp){
        this.forecast = forecast;
        String[] st = temp.split("/");
        this.tempMin = Integer.parseInt(st[0]);
        this.tempMax = Integer.parseInt(st[1]);
    }

    @Override
    public String toString() {
        return "forecast: " +forecast+ ", minimum: "+ tempMin+", minimum: "+tempMax;
    }

    public String getForecast() {
        return forecast;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
    }

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }
}
