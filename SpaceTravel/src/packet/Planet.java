package packet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Planet {
    private String name;
    private int hoursPerDay; // 1 day = X hours
    private LocalDate localDate;
    private int accumulatedHours = 0;
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Planet(String name, int hoursPerDay, String date) {
        this.name = name;
        this.hoursPerDay = hoursPerDay;
        this.localDate = LocalDate.parse(date, dateFormat);
    }

    // Saat ilerletme (1 iterasyon = 1 saat)
    public void advanceTime(int hours) {
        accumulatedHours += hours;
        int daysToAdd = accumulatedHours / hoursPerDay; // Tam gün sayısı
        accumulatedHours %= hoursPerDay; // Kalan saatler
        
        localDate = localDate.plusDays(daysToAdd);
    }

    // Getters
    public String getName() { return name; }
    public LocalDate getLocalDate() { return localDate; }
    public String getFormattedDate() { return localDate.format(dateFormat); }
    public int getHoursPerDay() { return hoursPerDay; }
    
    @Override
    public String toString() {
        return String.format("%-15s | Tarih: %s", name, getFormattedDate());
    }
}