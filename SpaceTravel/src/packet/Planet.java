/** 
* 
* @author mustafa ozcan ve mustafa.ozcan8@ogr.sakarya.edu.tr 
* @since 01.04.2025
* <p> 
*  Gezegen
* </p> 
*/ 
package packet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Planet {
    private String name;
    private int hoursInDay;
    private Date currentDate;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private int accumulatedHours = 0; // Biriken saatler
    
    public Planet(String name, int hoursInDay, String startDate) throws ParseException {
        this.name = name;
        this.hoursInDay = hoursInDay;
        this.currentDate = dateFormat.parse(startDate);
    }
    
    public void advanceTime(int hours) {
        accumulatedHours += hours;
        int daysToAdd = accumulatedHours / hoursInDay; // Tam gün sayısı
        accumulatedHours %= hoursInDay; // Kalan saatler
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        
        // Günleri ekle
        if (daysToAdd > 0) {
            calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
        }
        
        currentDate = calendar.getTime();
    }
    
    public String getName() {
        return name;
    }
    
    public int getHoursInDay() {
        return hoursInDay;
    }
    
    public Date getCurrentDate() {
        return currentDate;
    }
    
    public String getCurrentDateAsString() {
        return dateFormat.format(currentDate);
    }
    
    @Override
    public String toString() {
        return "---" + name + "---";
    }
    
    // İki tarihin aynı olup olmadığını kontrol eder
    public boolean isSameDate(String otherDate) throws ParseException {
        Date date = dateFormat.parse(otherDate);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(currentDate);
        cal2.setTime(date);
        
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
               cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
               cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }
    
  /*  // Belirli saat sonraki tarihi hesaplar
    public String getDateAfterHours(int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        
        int totalHours = hours;
        int daysToAdd = totalHours / hoursInDay;
        
        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
        
        return dateFormat.format(calendar.getTime());
    }
*/
    public String getDateAfterDays(int daysToAdd) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        
        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
        
        return dateFormat.format(calendar.getTime());
    }
    
    public String getDateAfterHours(int hours) {
        int daysToAdd = hours / hoursInDay;
        return getDateAfterDays(daysToAdd);
    }
}