//Elizaveta Vainer 332412055
//Shir Cohen 207365024
package components;

import enums.Status;
import java.util.ArrayList;

public class Branch implements Node {
    private static int nextBranchID = 2000;
    protected int BranchID;
    private String branchName;
    private ArrayList<Package> listPackages;
    private ArrayList<Truck> listTrucks;

    //default constructor
    //gets nothing, creates a default branch
    public Branch() {
        super();
        this.BranchID = nextBranchID++;
        this.branchName = "Default";
        this.listPackages = new ArrayList<>();
        this.listTrucks = new ArrayList<>();
    }

    //constructor with name
    //gets the name of the branch, creates a branch with it
    public Branch(String branchName) {
        super();
        this.listPackages = new ArrayList<>();
        this.listTrucks = new ArrayList<>();
        this.branchName = branchName;

        if (!branchName.equals("HUB")) {
            this.BranchID = nextBranchID++;
            System.out.println("Creating Branch " + (BranchID - 2000) + ", branch name:" + branchName + ", packages: 0, trucks: 0");
        }
    }

    //gets a package
    //adds it to this branch
    @Override
    public void collectPackage(Package p) {
        listPackages.add(p);
    }

    //gets a package
    //removes it from this branch
    @Override
    public void deliverPackage(Package p) {
        listPackages.remove(p);
    }

    //gets a truck
    //adds it to the truck list
    public void addTruck(Truck t) {
        listTrucks.add(t);
        if (t instanceof Van) {
            ((Van) t).setHomeBranch(this);
        }
    }

    //gets nothing
    //returns the branch ID
    public int getBranchID() {
        return this.BranchID;
    }

    //gets nothing
    //returns the list of packages in this branch
    public ArrayList<Package> getPackages() {
        return listPackages;
    }

    //gets nothing
    //returns the list of trucks in this branch
    public ArrayList<Truck> getTrucks() {
        return listTrucks;
    }

    //gets nothing
    //returns the name of the branch
    public String getBranchName() {
        return this.branchName;
    }

    //gets nothing
    //does the branch work (assigning trucks, ticking, etc.)
    @Override
    public void work() {
        //assign vans to collect
        for (int i = 0; i < listPackages.size(); i++) {
            Package p = listPackages.get(i);
            if (p.getStatus() == Status.CREATION) {
                for (int j = 0; j < listTrucks.size(); j++) {
                    Truck t = listTrucks.get(j);
                    if (t instanceof Van && t.isAvailable()) {
                        t.collectPackage(p);
                        listPackages.remove(p);
                        i--; //fix index after removal
                        break;
                    }
                }
            }
        }

        //mark packages that arrived for delivery
        for (int i = 0; i < listPackages.size(); i++) {
            Package p = listPackages.get(i);
            if (p.getStatus() == Status.BRANCH_STORAGE &&
                    p.getDestinationAddress().getZip() == this.getBranchID()) {
                p.setStatus(Status.DELIVERY);
            }
        }

        //assign vans to deliver
        for (int i = 0; i < listPackages.size(); i++) {
            Package p = listPackages.get(i);
            if (p.getStatus() == Status.DELIVERY) {
                for (int j = 0; j < listTrucks.size(); j++) {
                    Truck t = listTrucks.get(j);
                    if (t instanceof Van && t.isAvailable()) {
                        t.deliverPackage(p);
                        listPackages.remove(p);
                        i--; //fix index
                        break;
                    }
                }
            }
        }

        //tick all trucks
        for (int i = 0; i < listTrucks.size(); i++) {
            listTrucks.get(i).work();
        }
    }

    //gets another object
    //returns true if it's a branch with same ID and name
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Branch) {
            Branch other = (Branch) obj;
            return this.BranchID == other.BranchID &&
                    this.branchName.equals(other.branchName);
        }
        return false;
    }

    //gets nothing
    //returns "Branch X" or "Branch -1" for HUB
    @Override
    public String toString() {
        if (this.BranchID == -1)
            return "Branch -1";
        return "Branch " + (this.BranchID - 2000);
    }

    //gets a package
    //adds it to this branch
    public void addPackage(Package pack) {
        getPackages().add(pack);
    }

    //sets the internal Branch ID manually
    protected void setBranchId(int i) {
        this.BranchID = i;
    }
}
