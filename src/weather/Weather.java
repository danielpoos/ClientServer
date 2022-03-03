package weather;

public class Weather {
    private String county;
    private Forecast weatherToday, weatherTomorrow;
    public Weather(String row) {
        String[] data = row.split("\\t+",-1);
        this.county = data[0].trim();
        this.weatherToday = new Forecast(data[1].trim(),data[2].trim());
        this.weatherTomorrow = new Forecast(data[3].trim(),data[4].trim());
    }
    @Override public String toString() {
        return county + "\n\ttoday: "+weatherToday+"\n\ttomorrow: "+weatherTomorrow+"\n";
    }
    public String getCounty() {
        return county;
    }
    public void setCounty(String county) {
        this.county = county;
    }
    public Forecast getWeatherToday() {
        return weatherToday;
    }
    public void setWeatherToday(Forecast weatherToday) {
        this.weatherToday = weatherToday;
    }
    public Forecast getWeatherTomorrow() {
        return weatherTomorrow;
    }
    public void setWeatherTomorrow(Forecast weatherTomorrow) {
        this.weatherTomorrow = weatherTomorrow;
    }
}
