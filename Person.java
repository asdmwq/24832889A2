
public abstract class Person {
    // 3 instance variables (name, age, phone number)
    private String name;
    private int age;
    private String phoneNumber;

    // Default constructor
    public Person() {
        this.name = "Unknown";
        this.age = 0;
        this.phoneNumber = "Unknown";
    }

    // Parameterized constructor
    public Person(String name, int age, String phoneNumber) {
        // Data validation: Ensure valid age range (1-120 years old) for high-distinction standard
        if (age < 1 || age > 120) {
            throw new IllegalArgumentException("Age must be between 1 and 120 years old");
        }
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    // Getter and Setter methods
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
        if (age < 1 || age > 120) {
            throw new IllegalArgumentException("Age must be between 1 and 120 years old");
        }
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Override toString: Facilitates printing personnel information (supports queue/record printing)
    @Override
    public String toString() {
        return String.format("Name: %s, Age: %d, Phone Number: %s",
                name, age, phoneNumber);
    }
}
