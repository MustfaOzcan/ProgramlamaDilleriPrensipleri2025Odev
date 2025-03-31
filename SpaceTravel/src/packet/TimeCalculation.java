package packet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeCalculation {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	
	 /**
     * İki tarih arasındaki farkı saat cinsinden hesaplar
     * @param startDate Başlangıç tarihi (dd.MM.yyyy formatında)
     * @param endDate Bitiş tarihi (dd.MM.yyyy formatında)
     * @param hoursInDay İlgili gezegendeki bir günün kaç saat olduğu
     * @return İki tarih arasındaki farkı saat cinsinden döndürür
     */
    public static int calculateHoursBetweenDates(String startDate, String endDate, int hoursInDay) throws ParseException {
        Date date1 = dateFormat.parse(startDate);
        Date date2 = dateFormat.parse(endDate);
        
        // Milisaniye cinsinden farkı hesapla
        long diffInMillies = Math.abs(date2.getTime() - date1.getTime());
        
        // Gün farkını hesapla
        long diffInDays = diffInMillies / (24 * 60 * 60 * 1000);
        
        // Gezegendeki saat cinsinden çevir
        int diffInHours = (int) (diffInDays * hoursInDay);
        
        return diffInHours;
    }
    
    
    /**
     * Kalkış gezegenindeki tarihten, varış gezegenindeki tarihe dönüşüm yapar
     * @param departurePlanetDate Kalkış gezegenindeki tarih
     * @param departureHoursInDay Kalkış gezegenindeki bir günün kaç saat olduğu
     * @param distanceInHours İki gezegen arasındaki mesafe (saat cinsinden)
     * @param destinationHoursInDay Varış gezegenindeki bir günün kaç saat olduğu
     * @param destinationCurrentDate Varış gezegenindeki mevcut tarih
     * @return Varış gezegenindeki tahmini varış tarihi
     */
    public static String calculateArrivalDate(String departurePlanetDate, int departureHoursInDay, 
                                            double distanceInHours, int destinationHoursInDay,
                                            String destinationCurrentDate) throws ParseException {
        
        // Önce varış gezegenindeki mevcut tarihe dönüştürelim
        Date destDate = dateFormat.parse(destinationCurrentDate);
        Calendar destCalendar = Calendar.getInstance();
        destCalendar.setTime(destDate);
        
        // Toplam seyahat süresi
        int totalHours = (int) Math.floor(distanceInHours);
        
        // Varış gezegenindeki gün sayısı
        int daysToAdd = totalHours / destinationHoursInDay;
        
        // Günleri ekle
        destCalendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
        
        return dateFormat.format(destCalendar.getTime());
    }
    
    /**
     * İki gezegenin mevcut tarihleri, aradaki mesafe ve kalkış tarihi verildiğinde,
     * uzay aracının varış gezegeninde ne zaman varacağını hesaplar
     * @param departurePlanetDate Kalkış gezegenindeki mevcut tarih
     * @param spacecraftDepartureDate Uzay aracının kalkış tarihi
     * @param departureHoursInDay Kalkış gezegenindeki bir günün kaç saat olduğu
     * @param distanceInHours İki gezegen arasındaki mesafe (saat cinsinden)
     * @param destinationCurrentDate Varış gezegenindeki mevcut tarih
     * @param destinationHoursInDay Varış gezegenindeki bir günün kaç saat olduğu
     * @return Varış gezegenindeki tahmini varış tarihi
     */
    public static String calculateCompleteJourneyDate(String departurePlanetDate, String spacecraftDepartureDate,
                                                     int departureHoursInDay, double distanceInHours,
                                                     String destinationCurrentDate, int destinationHoursInDay) throws ParseException {
        
        // Uzay aracının kalkış tarihine kadar olan süre (saat cinsinden)
        int waitingHours = calculateHoursBetweenDates(departurePlanetDate, spacecraftDepartureDate, departureHoursInDay);
        
        // Toplam seyahat süresi (kalkışa kadar bekleme + yolculuk)
        int totalJourneyHours = waitingHours + (int) Math.floor(distanceInHours);
        
        // Varış gezegenindeki gün sayısı
        int daysToAdd = (int) (totalJourneyHours / destinationHoursInDay);
        
        // Varış gezegenindeki mevcut tarihe günleri ekle
        Date destDate = dateFormat.parse(destinationCurrentDate);
        Calendar destCalendar = Calendar.getInstance();
        destCalendar.setTime(destDate);
        destCalendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
        
        return dateFormat.format(destCalendar.getTime());
    }
}
