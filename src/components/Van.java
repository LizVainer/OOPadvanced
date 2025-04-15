package components;

import enums.Status;

public class Van extends Truck {

    private Branch homeBranch;

    // constructor
    // gets nothing
    // prints creation message
    public Van() {
        super();
        System.out.println("Creating Van " + this);
    }

    // constructor
    // gets license plate and model
    // prints creation message
    public Van(String licensePlate, String truckModel) {
        super(licensePlate, truckModel);
        System.out.println("Creating Van " + this);
    }

    // gets a branch
    // sets it as the van's home branch
    public void setHomeBranch(Branch b) {
        this.homeBranch = b;
    }

    // gets a package
    // clears previous, adds this package and sets collection state
    @Override
    public void collectPackage(Package p) {
        getPackages().clear(); // Only one package at a time
        getPackages().add(p);
        setAvailable(false);

        int time = (p.getSenderAddress().getStreet() % 10) + 1;
        setTimeLeft(time);

        p.addTracking(this, Status.COLLECTION);
        p.setStatus(Status.COLLECTION);

        System.out.println("Van " + getTruckID() + " is collecting package " + p.getPackageID() +
                ", time left: " + getTimeLeft());
    }

    // gets a package
    // clears previous, adds this package and sets distribution state
    @Override
    public void deliverPackage(Package p) {
        getPackages().clear();
        getPackages().add(p);
        setAvailable(false);

        int time = (p.getDestinationAddress().getStreet() % 10) + 1;
        setTimeLeft(time);

        p.addTracking(this, Status.DISTRIBUTION);
        p.setStatus(Status.DISTRIBUTION);

        System.out.println("Van " + getTruckID() + " is delivering package " + p.getPackageID() +
                ", time left: " + getTimeLeft());
    }

    //gets nothing
    //simulates one time unit of van work
    //handles collecting or delivering a package
    @Override
    public void work() {
        if (isAvailable()) return;
        //reduce remaining time by 1 (simulates travel time)
        setTimeLeft(getTimeLeft() - 1);
        //if time is up (van arrived) if there is no package on the van, exit
        if (getTimeLeft() == 0) {
            if (getPackages().isEmpty()) return;
            Package p = getPackages().get(0); // the package being handled
            //if the van was collecting a package from sender update status to storage at branch
            if (p.getStatus() == Status.COLLECTION) {
                p.setStatus(Status.BRANCH_STORAGE);
                p.addTracking(homeBranch, Status.BRANCH_STORAGE);

                //deliver package to the home branch
                homeBranch.collectPackage(p);
                getPackages().clear(); // remove from van
                System.out.println("Van " + getTruckID() + " has collected package " + p.getPackageID()
                        + " and arrived back to branch " + homeBranch.getBranchID());

                setAvailable(true);
            }

            //if the van was delivering the package to the customer update status to delivered and remove from van
            else if (p.getStatus() == Status.DISTRIBUTION) {

                p.setStatus(Status.DELIVERED);
                p.addTracking(null, Status.DELIVERED);
                getPackages().clear();
                System.out.println("Van " + getTruckID() + " has delivered package " + p.getPackageID() + " to the destination");
                //if it's a small package with acknowledge flag on, send confirmation
                if (p instanceof SmallPackage sp && sp.isAcknowledge()) {
                    System.out.println("Delivery confirmation sent for package " + p.getPackageID());
                }

                setAvailable(true);
            }
        }

        //if van is still in transit (timeLeft > 0), print progress
        else {
            if (!getPackages().isEmpty()) {
                Package p = getPackages().get(0);
                if (p.getStatus() == Status.COLLECTION) {
                    System.out.println("Van " + getTruckID() + " is collecting package " + p.getPackageID() +
                            ", time left: " + getTimeLeft());
                } else if (p.getStatus() == Status.DISTRIBUTION) {
                    System.out.println("Van " + getTruckID() + " is delivering package " + p.getPackageID() +
                            ", time left: " + getTimeLeft());
                }
            }
        }
    }

    //gets nothing
    //returns a string describing the van
    @Override
    public String toString() {
        return "[truckID=" + getTruckID() +
                ", licensePlate=" + getLicencePlate() +
                ", truckModel=" + getTruckModel() +
                ", available= " + isAvailable() + "]";
    }
}
