//Elizaveta Vainer 332412055
//Shir Cohen 207365024
package components;

import enums.Priority;
import enums.Status;
import java.util.ArrayList;
import java.util.Random;

public class MainOffice {

    private static int clock = 0;
    private static Hub hub;
    private ArrayList<Package> packages;

    //constructor
    //gets number of branches and trucks per branch
    //sets up hub, trucks, branches, and vans
    public MainOffice(int branches, int trucksForBranch) {
        clock = 0;
        packages = new ArrayList<>();
        hub = new Hub();

        for (int i = 0; i < trucksForBranch; i++) {
            hub.addTruck(new StandardTruck());
        }

        hub.addTruck(new NonStandardTruck());

        for (int i = 0; i < branches; i++) {
            Branch branch = new Branch("Branch " + i);
            for (int j = 0; j < trucksForBranch; j++) {
                branch.addTruck(new Van());
            }
            hub.addBranch(branch);
        }
    }

    //gets nothing
    //returns current clock tick
    public static int getClock() {
        return clock;
    }

    //gets number of ticks to simulate
    //runs the simulation and prints results
    public void play(int playTime) {
        System.out.println("=========== START ==============");
        for (int i = 0; i < playTime; i++) {
            tick();
            if (i % 5 == 0) {
                addPackage();
            }
        }
        System.out.println("=========== STOP =================");
        printReport();
    }

    //gets nothing
    // tick branch + hub work
    public void tick() {
        System.out.println(clockString());
        clock++;

        for (int i = 0; i < hub.getBranches().size(); i++) {
            hub.getBranches().get(i).work();
        }

        hub.work();
    }

    //gets nothing
    //creates a random package and sends it to correct place
    public void addPackage() {
        Random rand = new Random();
        int type = rand.nextInt(3); //0 = small, 1 = standard, 2 = nonstandard
        Priority priority = Priority.values()[rand.nextInt(3)];

        Address sender = new Address(rand.nextInt(hub.getBranches().size()), 100000 + rand.nextInt(900000));
        Address dest = new Address(rand.nextInt(hub.getBranches().size()), 100000 + rand.nextInt(900000));
        Package pack;

        if (type == 0) {
            boolean ack = rand.nextBoolean();
            pack = new SmallPackage(priority, sender, dest, ack);
            System.out.println("Creating SmallPackage [" + pack + "]");
        } else if (type == 1) {
            double weight = 1 + rand.nextDouble() * 9;
            pack = new StandardPackage(priority, sender, dest, weight);
            System.out.println("Creating StandardPackage [" + pack + "]");
        } else {
            int width = rand.nextInt(401);
            int length = rand.nextInt(501);
            int height = rand.nextInt(1001);
            pack = new NonStandardPackage(priority, sender, dest, width, length, height);
            System.out.println("Creating NonStandardPackage [" + pack + "]");
        }

        packages.add(pack);

        if (pack instanceof NonStandardPackage) {
            hub.addPackage(pack);
        } else {
            Branch senderBranch = hub.getBranches().get(sender.getZip());
            senderBranch.addPackage(pack);
        }
    }

    //gets nothing
    //prints full tracking report of all packages
    public void printReport() {
        for (int i = 0; i < packages.size(); i++) {
            Package p = packages.get(i);
            System.out.println();
            System.out.println("TRACKING " + p);
            p.printTracking();
        }
    }

    //gets nothing
    //returns time in hh:mm format
    public String clockString() {
        return String.format("%02d:%02d", clock / 60, clock % 60);
    }

    //gets nothing
    //returns the HUB object
    public static Hub getHub() {
        return hub;
    }

    //gets branch zip (0-4)
    //returns the branch if found, null if not
    public static Branch getBranch(int zip) {
        if (zip >= 0 && zip < hub.getBranches().size()) {
            return hub.getBranches().get(zip);
        }
        return null;
    }
}
