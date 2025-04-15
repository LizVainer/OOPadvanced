//Elizaveta Vainer 332412055
//Shir Cohen 207365024
package components;

import enums.*;

public class SmallPackage extends Package {
    private boolean acknowledge;

    //constructor
    //gets priority, sender, destination, and if acknowledgment is required
    //sets up small package with base values and flag
    public SmallPackage(Priority priority, Address senderAddress, Address destinationAddress, boolean acknowledge) {
        super(priority, senderAddress, destinationAddress);
        this.acknowledge = acknowledge;
    }

    //gets nothing
    //returns whether acknowledgment is required
    public boolean isAcknowledge() {
        return acknowledge;
    }

    //gets nothing
    //builds string for small package with all details
    //returns the formatted string
    @Override
    public String toString() {
        return super.toString().replace("]", ", acknowledge=" + acknowledge + "]");
    }
}
