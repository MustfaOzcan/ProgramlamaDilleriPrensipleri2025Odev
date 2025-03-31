
/** 
* 
* @author Mustafa Özcan ~ mustafa.ozcan8@ogr.sakarya.edu.tr 
* @since 30.03.2025
* <p> 
*  Main Class
* </p> 
*/
package packet;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Simulation {

	public static void main(String[] args) throws IOException, InterruptedException {
			
		 	FileReader reader = new FileReader();
	        List<Planet> planets = reader.readPlanets("Gezegenler.txt");
	        Scanner scanner = new Scanner(System.in);
	        int x=1;
	        while (true) {
	        		
	        	
	            clearConsole(); // Ekranı temizle
	            
	            // Gezegenleri 4'lü gruplar halinde göster
	            for (int i = 0; i < planets.size(); i += 4) {
	                printPlanetGroup(planets, i);
	            }
	            
	            // Her iterasyon = 1 saat ilerle
	            planets.forEach(p -> p.advanceTime(1));
	            
	            // 1 saniye bekle (simülasyon hızı)
	            Thread.sleep(1000);
	            
	            
	            System.out.println("x : "+x);
	            x++;
	        }
	
		
	}
	
	private static void printPlanetGroup(List<Planet> planets, int startIndex) {
		
	    StringBuilder header = new StringBuilder("Gezegenler : ");
	    StringBuilder dates = new StringBuilder("Tarih:       ");
	    
	    for (int i = startIndex; i < startIndex + 4 && i < planets.size(); i++) {
	        Planet p = planets.get(i);
	        header.append(String.format("%-25s","---" + p.getName() + "---"));
	        dates.append(String.format("%-25s", p.getFormattedDate()));
	    }
	    
	    System.out.println(header);
	    System.out.println(dates);
	    System.out.println("\n".repeat(2)); // Gruplar arası boşluk
	}
	


		private static void clearConsole() {
		    try {
		        if (System.getProperty("os.name").contains("Windows")) {
		            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		        } else {
		            System.out.print("\033[H\033[2J"); // ANSI escape codes (Linux/Mac)
		        }
		    } catch (Exception e) {
		        System.out.println("\n".repeat(50)); // Fallback temizleme
		    }
		}

}

