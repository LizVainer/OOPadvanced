package components;

public class StandardPackage extends Package {
    private double weight;

    public StandardPackage(Priority priority, Address senderAddress, Address destinationAddress, double weight) {
        super(priority, senderAddress, destinationAddress);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
