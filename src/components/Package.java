package components;
import java.util.ArrayList;

public abstract class Package {
    private static int NEXT_ID = 0;
    private int packageID = 1000;
    private Priority priority;
    private Status status;
    private Address senderAddress;
    private Address destinationAddress;
    private ArrayList<Tracking> tracking;

    //ctor
    public Package(Priority priority, Address senderAddress, Address destinationAddress) {
        this.packageID = NEXT_ID++;
        this.priority = priority;
        this.senderAddress = senderAddress;
        this.destinationAddress = destinationAddress;
        this.tracking = new ArrayList<>();
    }

    //methods
    public void addTracking(Node node, Status status) {
        Tracking newTrack = new Tracking(MainOffice.clock, node, status); //CHANGE TIME LATER
        tracking.add(newTrack);
    }

    public void PrintTracking() {
        System.out.println("Tracking history for package number " + packageID);
        for (int i = 0; i < tracking.size(); i++) {
            System.out.println(tracking.get(i));
        }
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj) instanceof Package) {
            Package other = (Package) obj;
            if(this.packageID!= other.packageID || this.priority==other.priority || this.senderAddress==other.senderAddress || this.destinationAddress==other.destinationAddress) return false;
            for(int i=0;i<tracking.size();i++){
                if(this.tracking.get(i)!=other.tracking.get(i))
                    return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Package ID: " + packageID + ", Priority: " + priority + ", Status: " + status + ", From: " + senderAddress + ", To: " + destinationAddress;
    }

    //getters
    public int getPackageID() {
        return packageID;
    }

    public Priority getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

    public Address getSenderAddress() {
        return senderAddress;
    }

    public Address getDestinationAddress() {
        return destinationAddress;
    }
}
