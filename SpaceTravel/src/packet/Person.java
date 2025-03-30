package packet;

public class Person {
	
    private String name;
    private int age;
    private double remainingLifeHours; // Saat cinsinden
    private String spacecraft; // Bulunduğu uzay aracı

    // Constructor
    public Person(String name, int age, double remainingLifeHours, String spacecraft) {
        this.name = name;
        this.age = age;
        this.remainingLifeHours = remainingLifeHours;
        this.spacecraft = spacecraft;
    }

    // Getter and Setter 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getRemainingLifeHours() {
        return remainingLifeHours;
    }

    public void setRemainingLifeHours(double remainingLifeHours) {
        this.remainingLifeHours = remainingLifeHours;
    }

    public String getSpacecraft() {
        return spacecraft;
    }

    public void setSpacecraft(String spacecraft) {
        this.spacecraft = spacecraft;
    }

    @Override
    public String toString() {
        return String.format(
            name, age, remainingLifeHours, spacecraft
        );
    }
}