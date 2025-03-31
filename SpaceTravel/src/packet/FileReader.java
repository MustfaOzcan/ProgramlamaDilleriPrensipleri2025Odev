package packet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    
    public List<String> readLinesFromResource(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        if (is == null) {
            throw new IOException("Dosya bulunamadı! Kontrol edin: " + fileName 
                + "\nClasspath'te aranan konum: " + getClass().getClassLoader().getResource(fileName));
        }
     
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new IOException("Dosya okunurken hata oluştu: " + e.getMessage(), e);
        }
        
        return lines;
    }
    
    
    public List<Planet> readPlanets(String fileName) throws IOException {
        List<Planet> planets = new ArrayList<>();
        
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            
            if (is == null) {
                throw new IOException("Dosya bulunamadı: " + fileName);
            }
            
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("#");
                if (data.length != 3) {
                    throw new IOException("Geçersiz dosya formatı: " + line);
                }
                
                String name = data[0].trim();
                int hours = Integer.parseInt(data[1].trim());
                String date = data[2].trim();
                
                planets.add(new Planet(name, hours, date));
            }
        }
        
        return planets;
    }
    
 
    public List<Spacecraft> readSpacecrafts(String fileName, List<Planet> planets) throws IOException {
        List<Spacecraft> spacecrafts = new ArrayList<>();
        
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            
            if (is == null) throw new IOException("Dosya bulunamadı: " + fileName);
            
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("#");
                if (data.length != 5) throw new IOException("Geçersiz format: " + line);
                
                // Dosyadaki veriler:
                // [0] = Uzay aracı ismi
                // [1] = Çıkış gezegeni ismi
                // [2] = Varış gezegeni ismi
                // [3] = Kalkış tarihi (dd.MM.yyyy)
                // [4] = Seyahat saati (double)
                String name = data[0].trim();
                String depName = data[1].trim();
                String destName = data[2].trim();
                String departureDate = data[3].trim();
                double travelHours = Double.parseDouble(data[4].trim());
                
                // Çıkış ve varış gezegenlerini gezegenler listesinden buluyoruz.
                Planet departure = planets.stream()
                    .filter(p -> p.getName().equals(depName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Gezegen bulunamadı: " + depName));
                Planet destination = planets.stream()
                    .filter(p -> p.getName().equals(destName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Gezegen bulunamadı: " + destName));
                
                spacecrafts.add(new Spacecraft(name, departure, destination, departureDate, travelHours));
            }
        }
        
        return spacecrafts;
    }
    
}