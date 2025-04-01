package packet;

public class Person {
	
    private String name;
    private int age;
    private int remainingLifespan; // in hours
    private String spacecraft; //
    private boolean isAlive;

    // Constructor
    public Person(String name, int age, int remainingLifespan, String spacecraft) {
        this.name = name;
        this.age = age;
        this.remainingLifespan = remainingLifespan;
        this.spacecraft = spacecraft;
        this.isAlive=true;
    }
    
    public void passTime(int hours) {
        if (isAlive) {
            remainingLifespan -= hours;
            if (remainingLifespan <= 0) {
                isAlive = false;
                remainingLifespan = 0;
            }
        }
    }

    // Getter  
    public String getName() { return name;}
    public int getAge() {return age;}
    public int getRemainingLifespan() {return remainingLifespan;}
    public String getSpacecraftName() {return spacecraft;}
    public boolean isAlive() {return isAlive;}
  
    
  
}