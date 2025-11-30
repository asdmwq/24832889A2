
public class Employee extends Person {
    // 2 exclusive instance variables for employees
    private String employeeId;
    private String responsibleRideType;

    // Default constructor
    public Employee() {
        super();
        this.employeeId = "EMP-0000";
        this.responsibleRideType = "Unknown";
    }

    // Parameterized constructor (initializes parent + child class attributes)
    public Employee(String name, int age, String phoneNumber,
                   String employeeId, String responsibleRideType) {
        super(name, age, phoneNumber); // Call parent class parameterized constructor
        this.employeeId = employeeId;
        this.responsibleRideType = responsibleRideType;
    }

    // Getter and Setter methods
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getResponsibleRideType() {
        return responsibleRideType;
    }

    public void setResponsibleRideType(String responsibleRideType) {
        this.responsibleRideType = responsibleRideType;
    }

    // Override toString: Integrates employee-specific information
    @Override
    public String toString() {
        return String.format("%s, Employee ID: %s, Responsible Ride Type: %s",
                super.toString(), employeeId, responsibleRideType);
    }
}
