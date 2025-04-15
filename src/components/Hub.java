//Elizaveta Vainer 332412055
//Shir Cohen 207365024
package components;
import enums.Status;
import java.util.ArrayList;

public class Hub extends Branch {
    private final ArrayList<Branch> branches;

    //constructor
    //gets nothing, sets name and ID to HUB
    public Hub() {
        super("HUB");
        this.setBranchId(-1);
        branches = new ArrayList<>();
        System.out.println("Creating Branch -1, branch name:HUB, packages: 0, trucks: 0");
    }

    //gets a branch
    //adds it to the list of branches
    public void addBranch(Branch branch) {
        branches.add(branch);
    }

    //gets a zip
    //returns the matching branch object
    public Branch getBranch(int zip) {
        for (int i = 0; i < branches.size(); i++) {
            Branch b = branches.get(i);
            if (b.getBranchID() - 2000 == zip) return b;
        }
        return null;
    }

    //gets nothing
    //returns the list of all branches
    public ArrayList<Branch> getBranches() {
        return branches;
    }

    //gets a package
    //adds it to the HUB package list
    @Override
    public void collectPackage(Package p) {
        getPackages().add(p);
    }

    //gets nothing
    //works for hub,truck logic, branching, etc
    @Override
    public void work() {
        //let trucks continue their previous jobs
        for (int i = 0; i < getTrucks().size(); i++) {
            getTrucks().get(i).work();
        }

        //assign standard trucks
        for (int i = 0; i < getTrucks().size(); i++) {
            Truck t = getTrucks().get(i);
            if (t instanceof StandardTruck && t.isAvailable()) {
                StandardTruck std = (StandardTruck) t;
                int branchIndex = std.getTruckID() % branches.size();
                Branch dest = branches.get(branchIndex);
                std.setDestination(dest);

                ArrayList<Package> allPackages = getPackages();
                ArrayList<Package> toLoad = new ArrayList<>();
                int currentWeight = 0;

                for (int j = 0; j < allPackages.size(); j++) {
                    Package p = allPackages.get(j);
                    if (p.getDestinationAddress().getZip() == dest.getBranchID() - 2000) {
                        int w = (p instanceof StandardPackage) ? (int) ((StandardPackage) p).getWeight() : 1;
                        if (currentWeight + w <= std.getMaxWeight()) {
                            currentWeight += w;
                            toLoad.add(p);
                            p.setStatus(Status.HUB_TRANSPORT);
                            p.addTracking(this, Status.HUB_TRANSPORT);
                            getPackages().remove(p);
                            j--; //list shifted due to removal
                        }
                    }
                }

                std.getPackages().addAll(toLoad);
                std.setAvailable(false);
                int time = (int) (Math.random() * 10) + 1;
                std.setTimeLeft(time);

                System.out.println("StandardTruck " + std.getTruckID() + " loaded packages at HUB");
                System.out.println("StandardTruck " + std.getTruckID() + " is on it's way to Branch " + (dest.getBranchID() - 2000) + ", time to arrive: " + time);
            }
        }

        //assign non-standard truck
        for (int i = 0; i < getTrucks().size(); i++) {
            Truck t = getTrucks().get(i);
            if (t instanceof NonStandardTruck && t.isAvailable()) {
                NonStandardTruck ns = (NonStandardTruck) t;

                ArrayList<Package> all = new ArrayList<>(getPackages());
                for (int j = 0; j < all.size(); j++) {
                    Package p = all.get(j);
                    if (p instanceof NonStandardPackage && p.getStatus() == Status.CREATION) {
                        NonStandardPackage np = (NonStandardPackage) p;

                        if (np.getWidth() <= ns.getWidth() &&
                                np.getHeight() <= ns.getHeight() &&
                                np.getLength() <= ns.getLength()) {

                            ns.setAvailable(false);
                            ns.collectPackage(np);
                            getPackages().remove(np);

                            np.setStatus(Status.COLLECTION);
                            np.addTracking(ns, Status.COLLECTION);

                            int time = Math.abs(np.getSenderAddress().getStreet() % 1000000 -
                                    np.getDestinationAddress().getStreet() % 1000000) / 10 + 1;
                            ns.setTimeLeft(time);

                            System.out.println("NonStandardTruck " + ns.getTruckID() + " is collecting package " + np.getPackageID() + ", time to arrive: " + time);
                            break;
                        }
                    }
                }
            }
        }

        //let all branches work
        for (int i = 0; i < branches.size(); i++) {
            branches.get(i).work();
        }
    }

    //gets a package
    //adds it to HUB's list
    public void addPackage(Package pack) {
        getPackages().add(pack);
    }
}
