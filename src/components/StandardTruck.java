//Elizaveta Vainer 332412055
//Shir Cohen 207365024
package components;

import enums.Status;
import java.util.ArrayList;

public class StandardTruck extends Truck {
    private int maxWeight;
    private Branch destination;

    //constructor
    //gets nothing
    //creates truck with random max weight and prints creation message
    public StandardTruck() {
        super();
        this.maxWeight = (int) (Math.random() * 201) + 100;
        System.out.println("Creating StandardTruck " + this);
    }

    //constructor
    //gets license plate, model and weight
    //sets the truck fields and prints creation message
    public StandardTruck(String licensePlate, String truckModel, int maxWeight) {
        super(licensePlate, truckModel);
        this.maxWeight = maxWeight;
        System.out.println("Creating StandardTruck " + this);
    }

    //gets nothing
    //returns truck's max weight
    public int getMaxWeight() {
        return maxWeight;
    }

    //gets nothing
    //returns truck's destination branch
    public Branch getDestination() {
        return destination;
    }

    //gets a branch
    //sets it as this truck's destination
    public void setDestination(Branch destination) {
        this.destination = destination;
    }

    //main truck logic
    //checks if truck is available, working, arriving etc.
    //updates status of packages accordingly
    @Override
    public void work() {
        if (isAvailable()) return;

        setTimeLeft(getTimeLeft() - 1);
        if (getTimeLeft() > 0) return;

        //return trip to HUB
        if (destination == null) {
            System.out.println("StandardTruck " + getTruckID() + " arrived to HUB");
            System.out.println("StandardTruck " + getTruckID() + " unloaded packages at HUB");

            for (int i = 0; i < getPackages().size(); i++) {
                Package p = getPackages().get(i);
                p.setStatus(Status.HUB_STORAGE);
                p.addTracking(MainOffice.getHub(), Status.HUB_STORAGE);
                MainOffice.getHub().collectPackage(p);
            }

            getPackages().clear();
            setAvailable(true);
            return;
        }

        //arrived to branch
        int branchNum = destination.getBranchID() - 2000;
        System.out.println("StandardTruck " + getTruckID() + " arrived to Branch " + branchNum);
        System.out.println("StandardTruck " + getTruckID() + " unloaded packages at Branch " + branchNum);

        for (int i = 0; i < getPackages().size(); i++) {
            Package p = getPackages().get(i);
            p.setStatus(Status.BRANCH_STORAGE);
            p.addTracking(destination, Status.BRANCH_STORAGE);
            destination.collectPackage(p);
        }
        getPackages().clear();

        //collect from branch to HUB
        ArrayList<Package> toLoad = new ArrayList<>();
        double totalWeight = 0;

        ArrayList<Package> branchPackages = destination.getPackages();
        for (int i = 0; i < branchPackages.size(); i++) {
            Package p = branchPackages.get(i);
            if (p.getStatus() == Status.BRANCH_STORAGE &&
                    p.getDestinationAddress().getZip() != (destination.getBranchID() - 2000)) {

                double weight = (p instanceof StandardPackage) ? ((StandardPackage) p).getWeight() : 1;
                if (totalWeight + weight <= maxWeight) {
                    totalWeight += weight;
                    p.setStatus(Status.HUB_TRANSPORT);
                    p.addTracking(destination, Status.HUB_TRANSPORT);
                    toLoad.add(p);
                }
            }
        }

        for (int i = 0; i < toLoad.size(); i++) {
            getPackages().add(toLoad.get(i));
        }

        destination.getPackages().removeAll(toLoad);

        if (!toLoad.isEmpty()) {
            System.out.println("StandardTruck " + getTruckID() + " loaded packages at Branch " + branchNum);
        }

        //go back to HUB
        int returnTime = (int) (Math.random() * 6) + 1;
        setTimeLeft(returnTime);
        destination = null;

        System.out.println("StandardTruck " + getTruckID() + " is on it's way to the HUB, time to arrive: " + returnTime);
    }

    //gets an object to compare
    //returns true if both trucks have same max weight and destination
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StandardTruck) {
            StandardTruck other = (StandardTruck) obj;
            return this.maxWeight == other.maxWeight &&
                    this.destination == other.destination;
        }
        return false;
    }

    //gets nothing
    //returns truck details as string
    @Override
    public String toString() {
        return "[truckID=" + getTruckID() + ", licensePlate=" + getLicencePlate() + ", truckModel=" + getTruckModel() +
                ", available= " + isAvailable() + ",maxWeight=" + maxWeight + "]";
    }
}
