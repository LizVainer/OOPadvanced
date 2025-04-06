package components;

import java.util.ArrayList;
import java.util.Random;

public abstract class Truck {
    private static int nextTruckID=2000;
    private int truckID;
    private String licencePlate;
    private String truckModel;
    private boolean available;
    private int timeLeft;
    private ArrayList<Package> packages;

    //ctor
    public Truck(){
        Random random = new Random();
        this.truckID = nextTruckID++;
        this.truckModel = "M" + random.nextInt(5);
        this.licencePlate = String.format("%03d-%02d-%03d", random.nextInt(1000), random.nextInt(100), random.nextInt(1000));
        this.available = true;
        this.timeLeft = 0;
        this.packages = new ArrayList<>();
    }
    public Truck(String licencePlate,String truckModel){
        this.truckID = nextTruckID++;
        this.licencePlate = licencePlate;
        this.truckModel = truckModel;
        this.available = true;
        this.timeLeft = 0;
        this.packages = new ArrayList<>();
    }

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
        String string = "Truck ID: " + truckID + ", License Plate: " + licencePlate + ", Model: " + truckModel + ", Available: " + available + ", Time Left: " + timeLeft +", Packages:";
        for (int i = 0; i < packages.size(); i++) {
            string += "\n" + packages.get(i).toString();
        }

        return string;
    }
    public abstract void work();
}
