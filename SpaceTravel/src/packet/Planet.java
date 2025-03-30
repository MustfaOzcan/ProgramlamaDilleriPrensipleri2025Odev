package packet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Planet {
    private String name;
    private int hoursPerDay; // 1 day = X hours
    private LocalDate localDate;
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Planet(String name, int hoursPerDay, String date) {
        this.name = name;
        this.hoursPerDay = hoursPerDay;
        this.localDate = LocalDate.parse(date, dateFormat);
    }

    // Advance time by X hours (simulation)
    public void advanceTime(int hours) {
        int days = hours / hoursPerDay;
        localDate = localDate.plusDays(days);
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