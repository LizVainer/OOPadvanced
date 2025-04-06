package components;

import java.util.ArrayList;

public class Branch {
    private static int nextBranchID = 2000;
    private int BranchID;
    private String branchName;
    private ArrayList<Package> listPackages;
    private ArrayList<Truck> listTrucks;

    public Branch() {
        super();
        this.BranchID = nextBranchID++;
        this.branchName = "Default";
        this.listPackages = new ArrayList<>();
        this.listTrucks = new ArrayList<>();
    }

    public Branch(String branchName) {
        super();
        this.BranchID = nextBranchID++;
        this.branchName = branchName;
        this.listPackages = new ArrayList<>();
        this.listTrucks = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Branch) {
            Branch other = (Branch) obj;
            return this.BranchID == other.BranchID &&
                    this.branchName.equals(other.branchName);
        }
        return false;
    }

    @Override
    public String toString() {
        String str = "Branch ID: " + BranchID + ", Name: " + branchName + ", Packages:";
        for (Package p : listPackages) {
            str += "\n" + p.toString();
        }
        str += "\nTrucks:";
        for (Truck t : listTrucks) {
            str += "\n" + t.toString();
        }
        return str;
    }
    public void addTruck(Truck truck) {
        listTrucks.add(truck);
    }


    public void work() {

    }
}
