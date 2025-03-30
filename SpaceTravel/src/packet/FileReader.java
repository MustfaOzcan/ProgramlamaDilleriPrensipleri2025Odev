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
}