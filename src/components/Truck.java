package components;

import java.util.ArrayList;
import java.util.Random;

public abstract class Truck  implements Node {
    private static int nextTruckID = 2000;
    private int truckID;
    private String licencePlate;
    private String truckModel;
    private boolean available;
    private int timeLeft;
    private ArrayList<Package> packages;

    //ctor
    public Truck() {
        Random random = new Random();
        this.truckID = nextTruckID++;
        this.truckModel = "M" + random.nextInt(5);
        this.licencePlate = String.format("%03d-%02d-%03d", random.nextInt(1000), random.nextInt(100), random.nextInt(1000));
        this.available = true;
        this.timeLeft = 0;
        this.packages = new ArrayList<>();
    }

    public Truck(String licencePlate, String truckModel) {
        this.truckID = nextTruckID++;
        this.licencePlate = licencePlate;
        this.truckModel = truckModel;
        this.available = true;
        this.timeLeft = 0;
        this.packages = new ArrayList<>();
    }

    //getters
    public int getTruckID() {
        return this.truckID;
    }

    public String getLicencePlate() {
        return this.licencePlate;
    }

    public String getTruckModel() {
        return this.truckModel;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public int getTimeLeft() {
        return this.timeLeft;
    }

    public ArrayList<Package> getPackages() {
        return packages;
    }

    //setters
    public void setAvailable(boolean value) {
        this.available = value;
    }

    public void setPackages(ArrayList<Package> value) {
        this.packages = value;
    }

    public void setTimeLeft(int value) {
        this.timeLeft = value;
    }

    //methods from implementation
    @Override
    public void collectPackage(Package p) {
        packages.add(p);
    }

    @Override
    public void deliverPackage(Package p) {
        packages.remove(p);
    }

    @Override
    public abstract void work();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Truck) {
            Truck other = (Truck) obj;
            return this.truckID == other.truckID && this.licencePlate.equals(other.licencePlate) && this.truckModel.equals(other.truckModel);
        }
        return false;
    }

    @Override
    public String toString() {
        String string = "Truck ID: " + truckID + ", License Plate: " + licencePlate + ", Model: " + truckModel + ", Available: " + available + ", Time Left: " + timeLeft + ", Packages:";
        for (int i = 0; i < packages.size(); i++) {
            string += "\n" + packages.get(i).toString();
        }

        return string;
    }
}

