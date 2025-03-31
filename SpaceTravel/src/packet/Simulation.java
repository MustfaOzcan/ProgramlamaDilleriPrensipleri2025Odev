package packet;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class Simulation {
    
    public static void main(String[] args) throws ParseException, InterruptedException {
        // Dosyaları oku
        Map<String, Planet> planets = FileReader.readPlanets("src/resources/Gezegenler.txt");
        List<Spacecraft> spacecrafts = FileReader.readSpacecrafts("src/resources/Araclar.txt");
        
        int iteration = 0;
        boolean allArrived = false;
        
        while (!allArrived) {
            iteration++;
            
            // Ekranı temizle (Java'da konsolu temizleme)
            clearScreen();
            
            // Gezegenlerin zamanını ilerlet
            for (Planet planet : planets.values()) {
                planet.advanceTime(1); // Her iterasyonda 1 saat ilerle
            }
            
            // Uzay araçlarının durumunu güncelle
            for (Spacecraft spacecraft : spacecrafts) {
                // Çıkış gezegenindeki tarih kontrol et
                Planet departurePlanet = planets.get(spacecraft.getDeparturePlanet());
                spacecraft.updateStatus(departurePlanet);
                
                // Yolculuk süresini güncelle
                spacecraft.travel(1);
            }
            
            // Ekrana bilgileri yaz
            displayInformation(iteration, planets, spacecrafts);
            
            // Tüm araçlar hedeflerine ulaştı mı kontrol et
            allArrived = checkAllArrived(spacecrafts);
            
            // Simülasyonun hızını ayarla (1 saniye bekle)
            Thread.sleep(1000);
        }
        
        // Program bitiminde son durumu göster
        clearScreen();
        displayInformation(iteration, planets, spacecrafts);
        System.out.println("\nSimülasyon tamamlandı. Tüm uzay araçları hedeflerine ulaştı.");
    }
    
    private static void displayInformation(int iteration, Map<String, Planet> planets, List<Spacecraft> spacecrafts) {
        // İterasyon bilgisini göster
        System.out.println("iterasyon (saat) : " + iteration);
        
        // Gezegenleri yazdır
        System.out.print("Gezegenler : ");
        for (Planet planet : planets.values()) {
            System.out.print(planet + " ");
        }
        System.out.println();
        
        // Gezegenlerin tarihlerini yazdır
        System.out.print("Tarih: ");
        for (Planet planet : planets.values()) {
            System.out.print(planet.getCurrentDateAsString() + " ");
        }
        System.out.println("\n\n");
        
        // Uzay araçları tablosunu yazdır
        System.out.println("Uzay Aracı\tÇıkış Gezegeni\tVarış Gezegeni\tHedefe Kalan Saat\tDurum");
        for (Spacecraft spacecraft : spacecrafts) {
            System.out.printf("%-10s\t%-15s\t%-15s\t%-15.2f\t%-10s%n", 
                   spacecraft.getName(), 
                   spacecraft.getDeparturePlanet(),
                   spacecraft.getDestinationPlanet(),
                   spacecraft.getRemainingHours(),
                   spacecraft.getStatus());
        }
        System.out.println("---------------------------------------------------------------------");
    }
    
    private static boolean checkAllArrived(List<Spacecraft> spacecrafts) {
        for (Spacecraft spacecraft : spacecrafts) {
            if (!spacecraft.hasArrived()) {
                return false;
            }
        }
        return true;
    }
    
    private static void clearScreen() {
        try {
            // Windows için
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            // Unix/Linux/MacOS için
            else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Ekran temizleme başarısız olursa, boş satırlar ile temizle
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}