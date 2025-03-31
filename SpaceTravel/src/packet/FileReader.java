package packet;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileReader {
    
    public static Map<String, Planet> readPlanets(String filePath) {
        Map<String, Planet> planets = new HashMap<>();
        
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("#");
                if (parts.length == 3) {
                    String name = parts[0];
                    int hoursInDay = Integer.parseInt(parts[1]);
                    String date = parts[2];
                    
                    planets.put(name, new Planet(name, hoursInDay, date));
                }
            }
        } catch (IOException | ParseException e) {
            System.err.println("Gezegen dosyası okunurken hata oluştu: " + e.getMessage());
        }
        
        return planets;
    }
    
    public static List<Spacecraft> readSpacecrafts(String filePath) {
        List<Spacecraft> spacecrafts = new ArrayList<>();
        
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("#");
                if (parts.length == 5) {
                    String name = parts[0];
                    String departurePlanet = parts[1];
                    String destinationPlanet = parts[2];
                    String departureDate = parts[3];
                    double distanceInHours = Double.parseDouble(parts[4]);
                    
                    spacecrafts.add(new Spacecraft(name, departurePlanet, destinationPlanet, 
                                                   departureDate, distanceInHours));
                }
            }
        } catch (IOException e) {
            System.err.println("Uzay araçları dosyası okunurken hata oluştu: " + e.getMessage());
        }
        
        return spacecrafts;
    }
}