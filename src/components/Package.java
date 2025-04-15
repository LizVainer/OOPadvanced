//Elizaveta Vainer 332412055
//Shir Cohen 207365024
package components;

import enums.Priority;
import enums.Status;

import java.util.ArrayList;

public abstract class Package {

    private static int NEXT_ID = 1000;
    private int packageID;
    private Priority priority;
    private Status status;
    private Address senderAddress;
    private Address destinationAddress;
    private ArrayList<Tracking> tracking;
    private boolean assigned;

    //constructor
    //gets priority, sender address and destination
    //sets up the package and adds first tracking
    public Package(Priority priority, Address senderAddress, Address destinationAddress) {
        this.packageID = NEXT_ID++;
        this.priority = priority;
        this.status = Status.CREATION;
        this.senderAddress = senderAddress;
        this.destinationAddress = destinationAddress;
        this.tracking = new ArrayList<>();
        addTracking(null, Status.CREATION); //package starts at customer
    }

    //gets nothing
    //returns package ID
    public int getPackageID() {
        return packageID;
    }

    //gets nothing
    //returns priority
    public Priority getPriority() {
        return priority;
    }

    //gets new priority
    //updates the priority
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    //gets nothing
    //returns current status
    public Status getStatus() {
        return status;
    }

    //gets new status
    //updates package status
    public void setStatus(Status status) {
        this.status = status;
    }

    //gets nothing
    //returns sender address
    public Address getSenderAddress() {
        return senderAddress;
    }

    //gets new address
    //updates sender address
    public void setSenderAddress(Address senderAddress) {
        this.senderAddress = senderAddress;
    }

    //gets nothing
    //returns destination address
    public Address getDestinationAddress() {
        return destinationAddress;
    }

    //gets new address
    //updates destination address
    public void setDestinationAddress(Address destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    //gets nothing
    //returns list of tracking records
    public ArrayList<Tracking> getTracking() {
        return tracking;
    }

    //gets a node and status
    //adds tracking with current clock and updates status
    public void addTracking(Node node, Status status) {
        tracking.add(new Tracking(MainOffice.getClock(), node, status));
        this.status = status;
    }

    //gets nothing
    //prints full tracking list for this package
    public void printTracking() {
        System.out.println("Tracking history for package number " + packageID);
        for (int i = 0; i < tracking.size(); i++) {
            System.out.println(tracking.get(i));
        }
    }

    //gets another object
    //checks if it's same package ID
    //returns true if match, false if not
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Package) {
            Package other = (Package) obj;
            return this.packageID == other.packageID;
        }
        return false;
    }

    //gets nothing
    //returns formatted string of package info
    @Override
    public String toString() {
        return "packageID=" + packageID +
                ", priority=" + priority +
                ", status=" + status +
                ", startTime=," +
                " senderAddress=" + senderAddress +
                ", destinationAddress=" + destinationAddress;
    }

    //gets boolean
    //sets assigned flag
    public void setAssigned(boolean b) {
        this.assigned = b;
    }

    //gets nothing
    //returns if package is assigned
    public boolean isAssigned() {
        return this.assigned;
    }
}
