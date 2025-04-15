//Elizaveta Vainer 332412055
//Shir Cohen 207365024
package components;

import java.util.ArrayList;
import java.util.Random;

public abstract class Truck implements Node {
    private static int nextTruckID = 2000;
    private int truckID;
    private String licencePlate;
    private String truckModel;
    private boolean available;
    private int timeLeft;
    private ArrayList<Package> packages;

    //constructor
    //gets nothing
    //sets random license, model, and initializes values
    public Truck() {
        Random random = new Random();
        this.truckID = nextTruckID++;
        this.truckModel = "M" + random.nextInt(5);
        this.licencePlate = String.format("%03d-%02d-%03d", random.nextInt(1000), random.nextInt(100), random.nextInt(1000));
        this.available = true;
        this.timeLeft = 0;
        this.packages = new ArrayList<>();
    }

    //constructor
    //gets license plate and model
    //sets truck with those values
    public Truck(String licencePlate, String truckModel) {
        this.truckID = nextTruckID++;
        this.licencePlate = licencePlate;
        this.truckModel = truckModel;
        this.available = true;
        this.timeLeft = 0;
        this.packages = new ArrayList<>();
    }

    //gets nothing
    //returns truck ID
    public int getTruckID() {
        return truckID;
    }

    //gets nothing
    //returns license plate string
    public String getLicencePlate() {
        return licencePlate;
    }

    //gets nothing
    //returns model string
    public String getTruckModel() {
        return truckModel;
    }

    //gets nothing
    //returns if the truck is available
    public boolean isAvailable() {
        return available;
    }

    //gets nothing
    //returns the time left
    public int getTimeLeft() {
        return timeLeft;
    }

    //gets nothing
    //returns the list of packages
    public ArrayList<Package> getPackages() {
        return packages;
    }

    //gets a boolean
    //sets truck availability
    public void setAvailable(boolean value) {
        this.available = value;
    }

    //gets a list of packages
    //replaces the truck's package list
    public void setPackages(ArrayList<Package> value) {
        this.packages = value;
    }

    //gets an int
    //sets the time left
    public void setTimeLeft(int value) {
        this.timeLeft = value;
    }

    //gets a package
    //adds it to the truck
    @Override
    public void collectPackage(Package p) {
        packages.add(p);
    }

    //gets a package
    //removes it from the truck
    @Override
    public void deliverPackage(Package p) {
        packages.remove(p);
    }

    //abstract method
    //gets nothing
    //subclasses must define what the truck does during work
    @Override
    public abstract void work();

    //gets another object
    //compares by ID, license, and model
    //returns true if all equal
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Truck other) {
            return truckID == other.truckID &&
                    licencePlate.equals(other.licencePlate) &&
                    truckModel.equals(other.truckModel);
        }
        return false;
    }

    //gets nothing
    //builds a string of truck info
    //returns it
    @Override
    public String toString() {
        return "[truckID=" + truckID + ", licensePlate=" + licencePlate + ", truckModel=" + truckModel + ", available= " + available + "]";
    }
}
