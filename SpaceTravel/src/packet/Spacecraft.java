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
    
    public Spacecraft(String name, String departurePlanet, String destinationPlanet, 
                      String departureDate, double distanceInHours) {
        this.name = name;
        this.departurePlanet = departurePlanet;
        this.destinationPlanet = destinationPlanet;
        this.departureDate = departureDate;
        this.distanceInHours = distanceInHours;
        this.remainingHours = distanceInHours;
        this.status = "Beklemede";
    }
    
    public void updateStatus(Planet planet) throws ParseException {
        // Eğer araç hala beklemede ise ve bulunduğu gezegendeki tarih, çıkış tarihine eşitse
        if (status.equals("Beklemede") && departurePlanet.equals(planet.getName()) && planet.isSameDate(departureDate)) {
            status = "Yolda";
        }
    }
    
    public void travel(int hours) {
        if (status.equals("Yolda")) {
            remainingHours -= hours;
            if (remainingHours <= 0) {
                remainingHours = 0;
                status = "Varış";
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
    
    // Verilen gezegene göre varış tarihini hesapla
    public String calculateArrivalDate(Planet destinationPlanetObj) {
        int hoursInDay = destinationPlanetObj.getHoursInDay();
        return destinationPlanetObj.getDateAfterHours((int)distanceInHours);
    }
}