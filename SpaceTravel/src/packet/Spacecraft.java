package packet;

import java.text.ParseException;

public class Spacecraft {
    private String name;
    private String departurePlanet;
    private String destinationPlanet;
    private String departureDate;
    private double distanceInHours;
    private double remainingHours;
    private String status;
    private String estimatedArrivalDate;
    
    public Spacecraft(String name, String departurePlanet, String destinationPlanet, 
                      String departureDate, double distanceInHours) {
        this.name = name;
        this.departurePlanet = departurePlanet;
        this.destinationPlanet = destinationPlanet;
        this.departureDate = departureDate;
        this.distanceInHours = distanceInHours;
        this.remainingHours = distanceInHours;
        this.status = "Bekliyor";
        this.estimatedArrivalDate = "Hesaplanıyor";
    }
    
    public void calculateEstimatedArrivalDate(Planet destinationPlanet) {
        int hoursInDay = destinationPlanet.getHoursInDay();//varis gezegen 
        int daysToAdd = (int) (distanceInHours / hoursInDay); //eklenecek gün
        
        // Varış gezegenindeki tarihi al ve buna gün ekle
        this.estimatedArrivalDate = destinationPlanet.getDateAfterDays(daysToAdd);
    }
    
    public void updateStatus(Planet departurePlanet, Planet destinationPlanet) throws ParseException {
    	
    	
        // Eğer araç hala beklemede ise ve bulunduğu gezegendeki tarih, çıkış tarihine eşitse
        if (status.equals("Bekliyor") && this.departurePlanet.equals(departurePlanet.getName()) && departurePlanet.isSameDate(departureDate)) {
            status = "Yolda";
         // Varış zamanını hesapla ve güncelle
            calculateEstimatedArrivalDate(destinationPlanet);
            
        }
    }
    
    public void travel(int hours) {
        if (status.equals("Yolda")) {
            remainingHours -= hours;
            if (remainingHours <= 0) {
                remainingHours = 0;
                status = "Vardı";
            }
        }
    }
    
    public String getName() {
        return name;
    }
    
    public String getDeparturePlanet() {
        return departurePlanet;
    }
    
    public String getDestinationPlanet() {
        return destinationPlanet;
    }
    
    public String getDepartureDate() {
        return departureDate;
    }
    
    public double getDistanceInHours() {
        return distanceInHours;
    }
    
    public double getRemainingHours() {
        return remainingHours;
    }
    
    public String getStatus() {
        return status;
    }
    
    public boolean hasArrived() {
        return status.equals("Varış");
    }
    
    public String getEstimatedArrivalDate() {
        return estimatedArrivalDate;
    }
    
    
    
}