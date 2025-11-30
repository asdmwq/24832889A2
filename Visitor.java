
public class Visitor extends Person {
    // 2 exclusive instance variables for visitors
    private String visitorCardId;
    private boolean isVip;

    // Default constructor
    public Visitor() {
        super();
        this.visitorCardId = "VIS-0000";
        this.isVip = false;
    }

    // Parameterized constructor (initializes parent + child class attributes)
    public Visitor(String name, int age, String phoneNumber,
                  String visitorCardId, boolean isVip) {
        super(name, age, phoneNumber);
        this.visitorCardId = visitorCardId;
        this.isVip = isVip;
    }

    // Getter and Setter methods
    public String getVisitorCardId() {
        return visitorCardId;
    }

    public void setVisitorCardId(String visitorCardId) {
        this.visitorCardId = visitorCardId;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean isVip) {
        this.isVip = isVip;
    }

    // Override toString: Integrates visitor-specific information (supports queue/record printing)
    @Override
    public String toString() {
        return String.format("%s, Visitor Card ID: %s, VIP Status: %s",
                super.toString(), visitorCardId, isVip ? "Yes" : "No");
    }
}
