//Elizaveta Vainer 332412055
//Shir Cohen 207365024
package components;

import enums.Status;

public class NonStandardTruck extends Truck {
    private int width;
    private int length;
    private int height;

    //constructor
    //gets nothing
    //creates truck with random dimensions
    public NonStandardTruck() {
        super();
        this.width = (int)(Math.random() * 500) + 1;
        this.length = (int)(Math.random() * 1000) + 1;
        this.height = (int)(Math.random() * 400) + 1;
        System.out.println("Creating NonStandardTruck [truckID=" + getTruckID() + ", licensePlate=" + getLicencePlate() +
                ", truckModel=" + getTruckModel() + ", available= " + isAvailable() + ", length=" + length + ", width=" + width + ", height=" + height + "]");
    }

    //constructor
    //gets license plate, model, and size
    //sets truck with those exact values
    public NonStandardTruck(String licensePlate, String truckModel, int length, int width, int height) {
        super(licensePlate, truckModel);
        this.width = width;
        this.length = length;
        this.height = height;
    }

    //gets nothing
    //returns truck width
    public int getWidth() { return width; }

    //gets nothing
    //returns truck length
    public int getLength() { return length; }

    //gets nothing
    //returns truck height
    public int getHeight() { return height; }

    //gets nothing
    //runs one tick of the truck behavior
    //checks if collecting or delivering
    @Override
    public void work() {
        if (isAvailable()) return;

        setTimeLeft(getTimeLeft() - 1);
        if (getTimeLeft() > 0) return;
        if (getPackages().isEmpty()) return;

        Package p = getPackages().get(0);
        Status status = p.getStatus();

        //truck finished collecting package
        if (status == Status.COLLECTION) {
            p.setStatus(Status.DISTRIBUTION);
            p.addTracking(this, Status.DISTRIBUTION);

            int street1 = p.getSenderAddress().getStreet();
            int street2 = p.getDestinationAddress().getStreet();
            int time = Math.abs(street1 - street2) / 10 + 1;
            setTimeLeft(time);

            System.out.println("NonStandardTruck " + getTruckID() + " has collected package " + p.getPackageID());
            System.out.println("NonStandardTruck " + getTruckID() + " is delivering package " + p.getPackageID() +
                    ", time left: " + getTimeLeft());
        }

        //truck finished delivery
        else if (status == Status.DISTRIBUTION) {
            p.setStatus(Status.DELIVERED);
            p.addTracking(this, Status.DELIVERED);
            deliverPackage(p);

            System.out.println("NonStandardTruck " + getTruckID() + " has delivered package " + p.getPackageID() + " to the destination");

            setAvailable(true);
        }
    }

    //gets another truck object
    //compares size values
    //returns true if all sizes match
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NonStandardTruck) {
            NonStandardTruck other = (NonStandardTruck) obj;
            return this.width == other.width &&
                    this.length == other.length &&
                    this.height == other.height;
        }
        return false;
    }

    //gets a package
    //adds it to this truck's list
    @Override
    public void collectPackage(Package p) {
        getPackages().add(p);
    }

    //gets nothing
    //returns full info about the truck as a string
    @Override
    public String toString() {
        return "NonStandardTruck [truckID=" + getTruckID() + ", licensePlate=" + getLicencePlate() +
                ", truckModel=" + getTruckModel() + ", available= " + isAvailable() +
                ", length=" + length + ", width=" + width +
                ", height=" + height + "]";
    }
}
