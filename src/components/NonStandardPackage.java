//Elizaveta Vainer 332412055
//Shir Cohen 207365024
package components;

import enums.Priority;

public class NonStandardPackage extends Package {
    private int width;
    private int length;
    private int height;

    //constructor
    //gets priority, sender & destination address, width, length, height
    //creates a non-standard package with those values
    public NonStandardPackage(Priority priority, Address senderAddress, Address destinationAddress, int width, int length, int height) {
        super(priority, senderAddress, destinationAddress);
        this.width = width;
        this.length = length;
        this.height = height;
    }

    //gets nothing
    //returns the width
    public int getWidth() {
        return width;
    }

    //gets nothing
    //returns the length
    public int getLength() {
        return length;
    }

    //gets nothing
    //returns the height
    public int getHeight() {
        return height;
    }

    //gets nothing
    //builds and returns a string describing the package
    @Override
    public String toString() {
        return "Creating NonStandardPackage [packageID=" + getPackageID() + ", priority=" + getPriority() + ", status=" + getStatus() +
                ", startTime=, senderAddress=" + getSenderAddress() + ", destinationAddress=" + getDestinationAddress() +
                ", width=" + width + ", length=" + length + ", height=" + height + "]";
    }

    //gets another object
    //checks if it's a NonStandardPackage and has same values
    //returns true if equal, false otherwise
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NonStandardPackage) {
            NonStandardPackage other = (NonStandardPackage) obj;
            return super.equals(other) &&
                    this.width == other.width &&
                    this.length == other.length &&
                    this.height == other.height;
        }
        return false;
    }
}
