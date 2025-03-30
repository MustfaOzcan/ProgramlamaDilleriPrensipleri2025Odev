package packet;
import java.time.LocalDate;

public class Spacecraft {
    private String name;
    private Planet currentPlanet;
    private Planet destinationPlanet;
    private LocalDate departureDate;
    private double travelDistanceHours; // Distance in hours

    public Spacecraft(String name, Planet currentPlanet, Planet destinationPlanet,
                     LocalDate departureDate, double travelDistanceHours) {
        this.name = name;
        this.currentPlanet = currentPlanet;
        this.destinationPlanet = destinationPlanet;
        this.departureDate = departureDate;
        this.travelDistanceHours = travelDistanceHours;
    }

    // Move the spacecraft if departure date matches the planet's current date
    public void move() {
        if (currentPlanet.getLocalDate().equals(departureDate)) {
            System.out.printf("[%s] Departing from %s to %s!\n",
                name, currentPlanet.getName(), destinationPlanet.getName());
            currentPlanet = destinationPlanet;
        }
    }

    // Getters
    public Planet getCurrentPlanet() { return currentPlanet; }
    public Planet getDestinationPlanet() { return destinationPlanet; }
    

    @Override
    public String toString() {
        return String.format("%s | Current Location: %s | Departure Date: %s",
            name, currentPlanet.getName(), departureDate);
    }
}
