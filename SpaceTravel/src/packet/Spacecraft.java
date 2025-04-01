/** 
* 
* @author mustafa ozcan ve mustafa.ozcan8@ogr.sakarya.edu.tr 
* @since 01.04.2025
* <p> 
*  Uzay Aracı
* </p> 
*/ 
package packet;

import java.text.ParseException;
import java.util.List;

public class Spacecraft {
    private String name;
    private String departurePlanet;
    private String destinationPlanet;
    private String departureDate;
    private double distanceInHours;
    private double remainingHours;
    private String status;
    private String estimatedArrivalDate;
    private boolean isDestroyed;
    
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
        this.isDestroyed=false;
    }
    
 
    
    public void updateStatus(Planet departurePlanet, Planet destinationPlanet) throws ParseException {
    	
    	if(isDestroyed)	{
    		
    		status = "İMHA";
            return;
    	}
    	
    	if (estimatedArrivalDate.equals("Hesaplanıyor")){
    		
    	        calculateInitialEstimatedArrivalDate(departurePlanet, destinationPlanet);
    	}
    	
        // Eğer araç hala beklemede ise ve bulunduğu gezegendeki tarih, çıkış tarihine eşitse
        if (status.equals("Bekliyor") && this.departurePlanet.equals(departurePlanet.getName()) && departurePlanet.isSameDate(departureDate)) {
            status = "Yolda";
      
        }
    }
    
    public void travel(int hours) {
    	if (isDestroyed) {
            return;
        }
        if (status.equals("Yolda")) {
            remainingHours -= hours;
            if (remainingHours <= 0) {
                remainingHours = 0;
                status = "Vardı";
            }
        }
    }
    
    public void checkCrewStatus(List<Person> people) {
        // Aracın içinde yaşayan biri var mı kontrol et
        boolean anyAlive = false;
        
        for (Person person : people) {
            if (person.getSpacecraftName().equals(this.name) && person.isAlive()) {
                anyAlive = true;
                break;
            }
        }
        
        // Eğer kimse hayatta değilse aracı imha et
        if (!anyAlive) {
            isDestroyed = true;
            status = "İMHA";
        }
    }
    
    //  varış tarihini hesaplamak için
    public void calculateInitialEstimatedArrivalDate(Planet departurePlanet, Planet destinationPlanet) throws ParseException {
        // TimeCalculation sınıfını kullanarak varış tarihini hesapla
        String arrivalDate = TimeCalculation.calculateCompleteJourneyDate(
            departurePlanet.getCurrentDateAsString(),  // Kalkış gezegenindeki mevcut tarih
            this.departureDate,                        // Uzay aracının kalkış tarihi
            departurePlanet.getHoursInDay(),           // Kalkış gezegenindeki bir günün kaç saat olduğu
            this.distanceInHours,                      // İki gezegen arasındaki mesafe
            destinationPlanet.getCurrentDateAsString(),// Varış gezegenindeki mevcut tarih
            destinationPlanet.getHoursInDay()          // Varış gezegenindeki bir günün kaç saat olduğu
        );
        
        this.estimatedArrivalDate = arrivalDate;
    }
    
   
    public String getCurrentLocation() {
        if (status.equals("Bekliyor")) {
            return departurePlanet;
        } else if (status.equals("Vardı")) {
            return destinationPlanet;
        } else {
            return "Yolda";
        }
    }   
        
    public String getName() {return name;}
    public String getDeparturePlanet() { return departurePlanet;}
    public String getDestinationPlanet() {return destinationPlanet;}
    public String getDepartureDate() {return departureDate;}
    public double getDistanceInHours() { return distanceInHours;}
    public double getRemainingHours() {return remainingHours;}
    public String getStatus() {return status;}
    public boolean hasArrived() {return status.equals("Vardı") || isDestroyed;}
    public String getEstimatedArrivalDate() {return isDestroyed ? "İMHA" : estimatedArrivalDate;} 
    public boolean isDestroyed() {return isDestroyed;}
    
    
    
   
    
    
}