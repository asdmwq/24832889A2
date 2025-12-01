import java.io.*;
import java.util.*;


public class Ride implements RideInterface {
    //  3 instance variables (including 1 Employee type)
    private String rideName;       // Ride name
    private String rideType;       // Ride type (e.g., roller coaster, water ride)
    private Employee operator;     // Ride operator (Employee type)

    //  Queue to store waiting visitors (FIFO, implemented with LinkedList)
    private Queue<Visitor> waitingLine;

    //  LinkedList to store ride history (supports sorting/iteration)
    private LinkedList<Visitor> rideHistory;

    // Ride cycle-related attributes
    private int maxRider;      // Maximum riders per cycle (≥1, variable per ride)
    private int numOfCycles;   // Number of completed cycles (default 0, increment by 1 per cycle)

    // Default constructor
    public Ride() {
        this.rideName = "Unknown Ride";
        this.rideType = "Unknown Type";
        this.operator = null;
        this.waitingLine = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.maxRider = 2;    // Default 2 riders per cycle
        this.numOfCycles = 0;
    }

    //  Parameterized constructor (initializes all attributes)
    public Ride(String rideName, String rideType, Employee operator,
                int maxRider) {
        this.rideName = rideName;
        this.rideType = rideType;
        this.operator = operator;
        this.waitingLine = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        // Validate maxRider (≥1)
        this.maxRider = maxRider >= 1 ? maxRider : 2;
        this.numOfCycles = 0;
    }

    //  Getter and Setter for all attributes
    public String getRideName() { return rideName; }
    public void setRideName(String rideName) { this.rideName = rideName; }
    public String getRideType() { return rideType; }
    public void setRideType(String rideType) { this.rideType = rideType; }
    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) { this.operator = operator; }
    public int getMaxRider() { return maxRider; }
    public void setMaxRider(int maxRider) { this.maxRider = maxRider >= 1 ? maxRider : 2; }
    public int getNumOfCycles() { return numOfCycles; }

    // ===================== Part 3: Waiting Queue Implementation =====================
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor == null) {
            System.out.println("❌ Error: Visitor object cannot be null, failed to add to queue");
            return;
        }
        waitingLine.offer(visitor); // Queue add method (FIFO)
        System.out.printf("✅ Visitor [%s] has been added to the waiting queue of [%s]\n",
                visitor.getName(), rideName);
    }

    @Override
    public void removeVisitorFromQueue() {
        if (waitingLine.isEmpty()) {
            System.out.printf("❌ The waiting queue of [%s] is empty, cannot remove visitor\n", rideName);
            return;
        }
        Visitor removed = waitingLine.poll(); // Queue remove method (removes head element)
        System.out.printf("✅ Visitor [%s] has been removed from the waiting queue of [%s]\n",
                removed.getName(), rideName);
    }

    @Override
    public void printQueue() {
        if (waitingLine.isEmpty()) {
            System.out.printf("📋 The waiting queue of [%s] is empty\n", rideName);
            return;
        }
        System.out.printf("📋 Waiting Queue of [%s] (Total %d people, FIFO order):\n",
                rideName, waitingLine.size());
        int index = 1;
        for (Visitor visitor : waitingLine) { // Traverse and print queue
            System.out.printf("  %d. %s\n", index++, visitor);
        }
    }

    // ===================== Part 4: Ride History Implementation =====================
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("❌ Error: Visitor object cannot be null, failed to add to history");
            return;
        }
        rideHistory.add(visitor);
        System.out.printf("✅ Visitor [%s] has been added to the ride history of [%s]\n",
                visitor.getName(), rideName);
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null || rideHistory.isEmpty()) {
            return false; // Return false for null object or empty history
        }
        // Traverse history: Uniquely identify by visitor card ID (avoids object reference issues)
        for (Visitor v : rideHistory) {
            if (v.getVisitorCardId().equals(visitor.getVisitorCardId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int numberOfVisitors() {
        return rideHistory.size(); // Return total number of visitors in history
    }

    @Override
    public void printRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.printf("📜 The ride history of [%s] is empty\n", rideName);
            return;
        }
        System.out.printf("📜 Ride History of [%s] (Total %d people):\n",
                rideName, rideHistory.size());
        
        // Part 4 requirement: Must use Iterator for printing
        Iterator<Visitor> iterator = rideHistory.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            System.out.printf("  %d. %s\n", index++, iterator.next());
        }
    }

    // Part 4B: Sorting method (uses custom Comparator, Comparable interface prohibited)
    public void sortRideHistory(Comparator<Visitor> comparator) {
        if (comparator == null) {
            System.out.println("❌ Error: Sort comparator cannot be null");
            return;
        }
        if (rideHistory.size() < 2) {
            System.out.printf("ℹ️  Not enough visitors (%d) in [%s] history to sort\n", rideHistory.size(), rideName);
            return;
        }
        Collections.sort(rideHistory, comparator); // Use Collections.sort
        System.out.printf("✅ Ride history of [%s] has been sorted successfully\n", rideName);
    }

    // ===================== Part 5: Run Ride Cycle =====================
    @Override
    public void runOneCycle() {
        // Validation 1: Check if operator exists
        if (operator == null) {
            System.out.printf("❌ [%s] has no operator, cannot run\n", rideName);
            return;
        }
        // Validation 2: Check if waiting queue has visitors
        if (waitingLine.isEmpty()) {
            System.out.printf("❌ The waiting queue of [%s] is empty, cannot run\n", rideName);
            return;
        }

        // Operation logic: Take visitors from queue according to maxRider, add to history
        int ridersThisCycle = Math.min(maxRider, waitingLine.size());
        System.out.printf("\n🚀 [%s] starts Cycle %d (Planned riders: %d)\n",
                rideName, numOfCycles + 1, ridersThisCycle);
        
        for (int i = 0; i < ridersThisCycle; i++) {
            Visitor rider = waitingLine.poll(); // Remove from queue
            addVisitorToHistory(rider); // Add to history
        }

        numOfCycles++; // Increment cycle count
        System.out.printf("✅ [%s] Cycle %d completed (Actual riders: %d)\n\n",
                rideName, numOfCycles, ridersThisCycle);
    }

    // ===================== Part 6: Export to File (CSV Format) =====================
    public void exportRideHistory(String filePath) {
        // Validate parameters and history
        if (filePath == null || filePath.trim().isEmpty()) {
            System.out.println("❌ Error: File path cannot be empty");
            return;
        }
        if (rideHistory.isEmpty()) {
            System.out.printf("❌ The ride history of [%s] is empty, no data to export\n", rideName);
            return;
        }

        // try-with-resources: Auto-close stream (high-distinction resource management)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write header (facilitates subsequent reading and parsing)
            writer.write("Name,Age,PhoneNumber,VisitorCardId,IsVIP");
            writer.newLine();

            // Write each visitor record (CSV format)
            for (Visitor visitor : rideHistory) {
                String line = String.format("%s,%d,%s,%s,%s",
                        visitor.getName(),
                        visitor.getAge(),
                        visitor.getPhoneNumber(),
                        visitor.getVisitorCardId(),
                        visitor.isVip() ? "Yes" : "No");
                writer.write(line);
                writer.newLine();
            }

            System.out.printf("✅ Ride history of [%s] exported successfully to: %s\n",
                    rideName, new File(filePath).getAbsolutePath());

        } catch (IOException e) {
            // Complete exception handling
            System.out.printf("❌ Export failed: %s (Please check if the path is valid)\n", e.getMessage());
        }
    }

    // ===================== Part 7: Import from File =====================
    public void importRideHistory(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            System.out.println("❌ Error: File path cannot be empty");
            return;
        }

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.printf("❌ Error: File [%s] does not exist\n", filePath);
            return;
        }

        int importedCount = 0; // Count imported records
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            reader.readLine(); // Skip header (matches export format)

            // Parse CSV data line by line
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                // Split CSV fields (handle empty values)
                String[] fields = line.split(",", -1);
                if (fields.length != 5) {
                    System.out.printf("⚠️  Skipping invalid line: %s (Incorrect number of fields)\n", line);
                    continue;
                }

                // Parse fields and create Visitor object (data validation)
                try {
                    String name = fields[0].isEmpty() ? "Unknown" : fields[0];
                    int age = fields[1].isEmpty() ? 0 : Integer.parseInt(fields[1]);
                    String phone = fields[2].isEmpty() ? "Unknown" : fields[2];
                    String cardId = fields[3].isEmpty() ? "VIS-IMPORT" : fields[3];
                    boolean isVip = "Yes".equals(fields[4]);

                    // Validate age (avoid invalid data)
                    if (age < 1 || age > 120) {
                        System.out.printf("⚠️  Skipping invalid line: %s (Invalid age)\n", line);
                        continue;
                    }

                    Visitor importedVisitor = new Visitor(name, age, phone, cardId, isVip);
                    rideHistory.add(importedVisitor);
                    importedCount++;

                } catch (NumberFormatException e) {
                    System.out.printf("⚠️  Skipping invalid line: %s (Age format error)\n", line);
                }
            }

            System.out.printf("✅ Import completed from [%s], total imported visitors: %d\n",
                    filePath, importedCount);

        } catch (IOException e) {
            // Complete exception handling (Part 7 requirement)
            System.out.printf("❌ Import failed: %s\n", e.getMessage());
        }
    }
}
