package packet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Spacecraft {
    private String name;
    private Planet departurePlanet; // 
    private Planet destinationPlanet; // 
    private LocalDate departureDate;
    private double travelHours;
    private double remainingHours;
    private boolean hasDeparted = false; // Kalkış yapıldı mı?
    

    public Spacecraft(String name, Planet departurePlanet, Planet destinationPlanet, 
            String departureDate, double travelHours) {
		this.name = name;
		this.departurePlanet = departurePlanet;
		this.destinationPlanet = destinationPlanet;
		this.departureDate = LocalDate.parse(departureDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		this.travelHours = travelHours;
		this.remainingHours = travelHours;
    }
    
    // Hareket etme fonksiyonu (her iterasyonda çağrılır)
    public void move() {
        if (hasDeparted) return; // Zaten hareket ettiyse çık

        // Kalkış tarihi kontrolü: çıkış gezegeninin güncel tarihi ile uzay aracının kalkış tarihi eşit mi?
        if (departurePlanet.getLocalDate().isEqual(departureDate)) {
            System.out.printf("[%s] %s gezegeninden %s gezegenine hareket ediyor!\n",
                              name, departurePlanet.getName(), destinationPlanet.getName());
            hasDeparted = true;
        }
    }
    
 // Varış tarihini hesapla (varış gezegeninin saat/gün bilgisi kullanılarak)
    public LocalDate calculateArrivalDate() {
        double daysNeeded = remainingHours / destinationPlanet.getHoursPerDay();
        return departureDate.plusDays((long) daysNeeded);
    }

    

    // Getter
    public String getName() { return name; }
    public boolean hasDeparted() {return hasDeparted;}
    public Planet getDeparturePlanet() { return departurePlanet;}
    public Planet getDestinationPlanet() {return destinationPlanet;}
    public double getTravelHours() {return travelHours; }
    //Setter
    public void setDeparturePlanet(Planet planet) {
        this.departurePlanet = planet;
    }
   
}