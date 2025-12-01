
public class AssignmentTwo {
    // Main method: Program entry point (runs all demonstrations automatically)
    public static void main(String[] args) {
        AssignmentTwo demo = new AssignmentTwo();
        
        System.out.println("==================================== Part 3 Demo: Waiting Queue ====================================");
        demo.partThree();
        
        System.out.println("\n==================================== Part 4A Demo: Ride History ====================================");
        demo.partFourA();
        
        System.out.println("\n==================================== Part 4B Demo: History Sorting ====================================");
        demo.partFourB();
        
        System.out.println("\n==================================== Part 5 Demo: Run Ride Cycle ====================================");
        demo.partFive();
        
        System.out.println("\n==================================== Part 6 Demo: Export History ====================================");
        demo.partSix();
        
        System.out.println("\n==================================== Part 7 Demo: Import History ====================================");
        demo.partSeven();
    }

    // ===================== Part 3 Demo: Waiting Queue Operations =====================
    public void partThree() {
        // 1. Create operator and ride object
        Employee operator = new Employee("Zhang San", 30, "13800138000", "EMP-001", "Roller Coaster");
        Ride rollerCoaster = new Ride("Thunder Roller Coaster", "Roller Coaster", operator, 2);

        // 2. Add 5 visitors to queue (Part 3 requirement: at least 5 people)
        rollerCoaster.addVisitorToQueue(new Visitor("Li Si", 25, "13900139000", "VIS-001", false));
        rollerCoaster.addVisitorToQueue(new Visitor("Wang Wu", 18, "13700137000", "VIS-002", true));
        rollerCoaster.addVisitorToQueue(new Visitor("Zhao Liu", 35, "13600136000", "VIS-003", false));
        rollerCoaster.addVisitorToQueue(new Visitor("Sun Qi", 22, "13500135000", "VIS-004", true));
        rollerCoaster.addVisitorToQueue(new Visitor("Zhou Ba", 28, "13400134000", "VIS-005", false));

        // 3. Print queue (verify addition result)
        System.out.println("\nAfter adding 5 visitors:");
        rollerCoaster.printQueue();

        // 4. Remove 1 visitor (Part 3 requirement)
        rollerCoaster.removeVisitorFromQueue();

        // 5. Print queue again (verify removal result)
        System.out.println("\nAfter removing 1 visitor:");
        rollerCoaster.printQueue();
    }

    // ===================== Part 4A Demo: Ride History Operations =====================
    public void partFourA() {
        Ride waterRide = new Ride("Rapid River", "Water Ride", null, 4);

        // 1. Add 5 visitors to history (Part 4A requirement: at least 5 people)
        Visitor v1 = new Visitor("Wu Jiu", 20, "13300133000", "VIS-006", false);
        Visitor v2 = new Visitor("Zheng Shi", 26, "13200132000", "VIS-007", true);
        Visitor v3 = new Visitor("Qian Shi Yi", 32, "13100131000", "VIS-008", false);
        Visitor v4 = new Visitor("Feng Shi Er", 19, "1300130000", "VIS-009", true);
        Visitor v5 = new Visitor("Chen Shi San", 29, "12900129000", "VIS-010", false);

        waterRide.addVisitorToHistory(v1);
        waterRide.addVisitorToHistory(v2);
        waterRide.addVisitorToHistory(v3);
        waterRide.addVisitorToHistory(v4);
        waterRide.addVisitorToHistory(v5);

        // 2. Check if a visitor exists in history (Part 4A requirement)
        Visitor checkV = new Visitor("Zheng Shi", 26, "13200132000", "VIS-007", true);
        System.out.printf("\nCheck if visitor [%s] exists in history: %s\n",
                checkV.getName(), waterRide.checkVisitorFromHistory(checkV) ? "Yes" : "No");

        // 3. Print total number of visitors in history (Part 4A requirement)
        System.out.printf("Total visitors in history: %d\n", waterRide.numberOfVisitors());

        // 4. Print complete history (Part 4A requirement, uses Iterator)
        System.out.println("\nComplete Ride History:");
        waterRide.printRideHistory();
    }

    // ===================== Part 4B Demo: History Sorting =====================
    public void partFourB() {
        Ride ferrisWheel = new Ride("Ferris Wheel", "Sightseeing Ride", null, 6);

        // 1. Add 5 visitors to history (Part 4B requirement: at least 5 people)
        ferrisWheel.addVisitorToHistory(new Visitor("Chu Shi Si", 38, "12800128000", "VIS-011", false));
        ferrisWheel.addVisitorToHistory(new Visitor("Wei Shi Wu", 21, "12700127000", "VIS-012", true));
        ferrisWheel.addVisitorToHistory(new Visitor("Jiang Shi Liu", 24, "12600126000", "VIS-013", false));
        ferrisWheel.addVisitorToHistory(new Visitor("Shen Shi Qi", 31, "12500125000", "VIS-014", true));
        ferrisWheel.addVisitorToHistory(new Visitor("Han Shi Ba", 17, "12400124000", "VIS-015", false));

        // 2. Print history before sorting
        System.out.println("Ride History Before Sorting:");
        ferrisWheel.printRideHistory();

        // 3. Sort using custom Comparator (Part 4B requirement)
        ferrisWheel.sortRideHistory(new VisitorComparator());

        // 4. Print history after sorting (verify sorting result)
        System.out.println("\nRide History After Sorting (VIP Priority, Age Ascending for Same VIP):");
        ferrisWheel.printRideHistory();
    }

    // ===================== Part 5 Demo: Run Ride Cycle =====================
    public void partFive() {
        // 1. Create operator and ride (max 4 riders per cycle)
        Employee operator = new Employee("Yang Shi Jiu", 28, "12300123000", "EMP-002", "Carousel");
        Ride carousel = new Ride("Dream Carousel", "Children's Ride", operator, 4);

        // 2. Add 10 visitors to queue (Part 5 requirement: at least 10 people)
        for (int i = 16; i <= 25; i++) {
            carousel.addVisitorToQueue(new Visitor(
                    "Visitor" + i,
                    10 + (i % 10),  // Age range: 10-19 years old
                    "12200122" + (i % 1000),
                    "VIS-0" + i,
                    i % 3 == 0 // 1 VIP every 3 visitors
            ));
        }

        // 3. Print queue before operation
        System.out.println("Waiting Queue Before Operation:");
        carousel.printQueue();

        // 4. Run 1 cycle (Part 5 requirement)
        carousel.runOneCycle();

        // 5. Print queue after operation (verify reduction)
        System.out.println("Waiting Queue After Operation:");
        carousel.printQueue();

        // 6. Print history after operation (verify addition)
        System.out.println("Ride History After Operation:");
        carousel.printRideHistory();
    }

    // ===================== Part 6 Demo: Export History =====================
    public void partSix() {
        Ride exportRide = new Ride("Pirate Ship", "Thrilling Ride", null, 3);

        // 1. Add 5 visitors to history (Part 6 requirement: at least 5 people)
        exportRide.addVisitorToHistory(new Visitor("Zhu Er Shi", 23, "12100121000", "VIS-026", true));
        exportRide.addVisitorToHistory(new Visitor("Qin Er Shi Yi", 27, "12000120000", "VIS-027", false));
        exportRide.addVisitorToHistory(new Visitor("You Er Shi Er", 33, "11900119000", "VIS-028", true));
        exportRide.addVisitorToHistory(new Visitor("Xu Er Shi San", 25, "11800118000", "VIS-029", false));
        exportRide.addVisitorToHistory(new Visitor("He Er Shi Si", 30, "11700117000", "VIS-030", true));

        // 2. Export to CSV file (path can be adjusted by system, relative path used here)
        String exportPath = "ride_history_pirate_ship.csv";
        exportRide.exportRideHistory(exportPath);
    }

    // ===================== Part 7 Demo: Import History =====================
    public void partSeven() {
        Ride importRide = new Ride("Import Test Ride", "Test Ride", null, 5);

        // 1. Import file generated by Part 6 (path must match Part 6)
        String importPath = "ride_history_pirate_ship.csv";
        importRide.importRideHistory(importPath);

        // 2. Print total number of visitors after import (verify import count, Part 7 requirement)
        System.out.printf("\nTotal visitors after import: %d\n", importRide.numberOfVisitors());

        // 3. Print detailed history after import (verify data accuracy, Part 7 requirement)
        System.out.println("Ride History After Import:");
        importRide.printRideHistory();
    }
}
