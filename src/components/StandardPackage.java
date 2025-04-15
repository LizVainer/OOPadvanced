//Elizaveta Vainer 332412055
//Shir Cohen 207365024
package components;

import enums.Priority;

public class StandardPackage extends Package {
    private double weight;

    //constructor
    //gets priority, sender, destination, and weight
    //sets up the standard package with these values
    public StandardPackage(Priority priority, Address senderAddress, Address destinationAddress, double weight) {
        super(priority, senderAddress, destinationAddress);
        this.weight = weight;
    }

    //gets nothing
    //returns the weight of the package
    public double getWeight() {
        return weight;
    }

    //gets new weight
    //updates the weight value
    public void setWeight(double weight) {
        this.weight = weight;
    }

    //gets nothing
    //builds a string with all package info
    //returns it as a string
    @Override
    public String toString() {
        return "Creating StandardPackage [packageID=" + getPackageID() + ", priority=" + getPriority() + ", status=" + getStatus() +
                ", startTime=, senderAddress=" + getSenderAddress() + ", destinationAddress=" + getDestinationAddress() + ", weight=" + weight + "]";
    }

    //gets an object
    //checks if it's a StandardPackage and has same weight + ID
    //returns true if equal, false otherwise
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StandardPackage) {
            StandardPackage other = (StandardPackage) obj;
            return super.equals(other) && this.weight == other.weight;
        }
        return false;
    }
}
