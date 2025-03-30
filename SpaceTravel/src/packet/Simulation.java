
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

public class Simulation {

	public static void main(String[] args) {
			
		try {
            FileReader reader = new FileReader();//create Filereader Object
            List<String> kisilerLines = reader.readLinesFromResource("Kisiler.txt");
            
            System.out.println("----- Kisiler.txt -----");
            for (String line : kisilerLines) {
                System.out.println(line); //  parsing işlemleri
            }
            
        }catch (IOException e) {
            System.err.println("Hata: " + e.getMessage());
        	}
		
	}

}
