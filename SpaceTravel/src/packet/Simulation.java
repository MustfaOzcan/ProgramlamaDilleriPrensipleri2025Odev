/** 
* 
* @author mustafa ozcan ve mustafa.ozcan8@ogr.sakarya.edu.tr 
* @since 01.04.2025
* <p> 
*  Main Class diğer sınıfları burada kullanıyoruz.
* </p> 
*/ 
package packet;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class Simulation {
    
    public static void main(String[] args) throws ParseException, InterruptedException {
        // Dosyaları oku
        Map<String, Planet> planets = FileReader.readPlanets("src/resources/Gezegenler.txt");
        List<Spacecraft> spacecrafts = FileReader.readSpacecrafts("src/resources/Araclar.txt");
        List<Person> people = FileReader.readPeople("src/resources/Kisiler.txt");
        
        int iteration = 0;
        boolean allArrived = false;
        
        
        while (!allArrived) {
            iteration++;
            
            // Ekranı temizle
            clearScreen();
            
            // Gezegenlerin zamanını ilerlet
            for (Planet planet : planets.values()) {
                planet.advanceTime(1); // Her iterasyonda 1 saat ilerle
            }
            
            // Kişilerin durumunu güncelle
            for (Person person : people) {
                person.passTime(1); // Her kişinin zamanını ilerlet
            }
            
            // Uzay araçlarının durumunu güncelle
            for (Spacecraft spacecraft : spacecrafts) {
            	
            	// Mürettebatın durumunu kontrol et
                spacecraft.checkCrewStatus(people);
                
            	// Çıkış ve varış gezegenlerini al
                Planet departurePlanet = planets.get(spacecraft.getDeparturePlanet());
                Planet destinationPlanet = planets.get(spacecraft.getDestinationPlanet());
                
                // Durumu güncelle
                spacecraft.updateStatus(departurePlanet,destinationPlanet);
                
                // Yolculuk süresini güncelle
                spacecraft.travel(1);
            }
            
            // Ekrana bilgileri yaz
            displayInformation(iteration, planets, spacecrafts, people);
            
            // Tüm araçlar hedeflerine ulaştı mı kontrol et
            allArrived = checkAllArrived(spacecrafts);
            
            // Simülasyonun hızını ayarla (1 saniye bekle)
            Thread.sleep(1000);
        }
        
        // Program bitiminde son durumu göster
        //clearScreen();
        displayInformation(iteration, planets, spacecrafts, people);
        System.out.println("\nSimülasyon tamamlandı. Tüm uzay araçları hedeflerine ulaştı.");
    }
    
    private static void displayInformation(int iteration, Map<String, Planet> planets, List<Spacecraft> spacecrafts, List<Person> people ) {
        // İterasyon bilgisini göster
    	System.out.println("-------------------------------------");//silinebilir
        System.out.println("iterasyon (saat) : " + iteration);//silinebilir
        int planetFieldWidth = 16; // Gezegen sütunu genişliği 
        int dateFieldWidth = 15;
      
        // Gezegenleri yazdır
        System.out.print("Gezegenler :\t");
        for (Planet planet : planets.values()) {
            // Gezegen ismini 3 karakterlik bir alan içinde ortalayıp --- ekliyoruz.
            String planetName = planet.toString();
            String formattedPlanet = "" + center(planetName, 3) + "";
            System.out.printf("%-" + planetFieldWidth  + "s", formattedPlanet);
        }
        System.out.println();
        
        // Tarih bilgisi
        System.out.print("Tarih: \t\t");
        for (Planet planet : planets.values()) {
            String date = planet.getCurrentDateAsString();
            System.out.printf("%-" + dateFieldWidth  + "s", date);
        }
        System.out.println();
        // Nüfus bilgisi
        System.out.print("Nüfus: \t\t");
        for (Planet planet : planets.values()) {
            int population = calculatePlanetPopulation(planet.getName(), spacecrafts, people);
            System.out.printf("%-" + dateFieldWidth  + "d", population);
        }
        
        System.out.println("\n\n");
        
        // Uzay araçları tablosunu yazdır
        System.out.println("Uzay Araçlari:");
        System.out.println("Araç Adı\tDurum\t\tÇıkış\t\tVarış\t\tHedefe Kalan Saat\tHedefe Varacağı Tarih");
        for (Spacecraft spacecraft : spacecrafts) {
        	System.out.printf("%-15s\t%-10s\t%-15s\t%-15s\t%-25s\t%-50s%n",  
        		       spacecraft.getName(),
        		       spacecraft.getStatus(),
        		       spacecraft.getDeparturePlanet(),
        		       spacecraft.getDestinationPlanet(),
        		       spacecraft.isDestroyed() ? "--" : String.format("%.0f", spacecraft.getRemainingHours()),
        		       spacecraft.isDestroyed() ? "--" : spacecraft.getEstimatedArrivalDate(),
        		       countCrewMembers(spacecraft.getName(), people)
        		      );
        }
        
        /* NOT  : BURADA YOLCU BİLGİLERİNİ YAZDIRIYORUZ ÖDEVDE İSTENMİYOR ANCAK MANTIĞINI ANLAMAK İÇİN BAKILABİLİR
        
        System.out.println("\n\nYolcular:");
        System.out.println("İsim\t\tYaş\tKalan Ömür (saat)\tUzay Aracı\tDurum");
        for (Person person : people) {
            System.out.printf("%-15s\t%-5d\t%-20d\t%-15s\t%-10s%n",
                   person.getName(),
                   person.getAge(),
                   person.getRemainingLifespan(),
                   person.getSpacecraftName(),
                   person.isAlive() ? "Yaşıyor" : "Ölü"
                  );
        }
        
        
        */
    }
    
	private static int calculatePlanetPopulation(String planetName, List<Spacecraft> spacecrafts, List<Person> people) {
		int population = 0;
		for (Person person : people) {
            if (!person.isAlive()) {
                continue; // Ölü insanları sayma
            }
            
            String spacecraftName = person.getSpacecraftName();
            Spacecraft spacecraft = findSpacecraft(spacecraftName, spacecrafts);
            
            if (spacecraft != null && !spacecraft.isDestroyed()) {
                String location = spacecraft.getCurrentLocation();
                if (location.equals(planetName)) {
                    population++;
                }
            }
        }
		
		return population;
		
		
	}
	
	private static int countCrewMembers(String spacecraftName, List<Person> people) {
        int count = 0;
        for (Person person : people) {
            if (person.getSpacecraftName().equals(spacecraftName) && person.isAlive()) {
                count++;
            }
        }
        return count;
    }
	
	private static Spacecraft findSpacecraft(String name, List<Spacecraft> spacecrafts) {
        for (Spacecraft spacecraft : spacecrafts) {
            if (spacecraft.getName().equals(name)) {
                return spacecraft;
            }
        }
        return null;
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
            // Ekranın temizlenmesi için kısa bir bekleme
            Thread.sleep(1000);
        } catch (Exception e) {
            // Ekran temizleme başarısız olursa, boş satırlar ile temizle
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
        
        
    }
    
    public static String center(String s, int width) {
        if (s == null || s.length() >= width) {
            return s;
        }
        int leftPadding = (width - s.length()) / 2;
        int rightPadding = width - s.length() - leftPadding;
        return " ".repeat(leftPadding) + s + " ".repeat(rightPadding);
    }
    
    
}