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

    @Override
    public String toString() {
        return super.toString() + ", Weight: " + this.weight;
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj) instanceof StandardPackage) {
            StandardPackage other = (StandardPackage) obj;
            return super.equals(other) && this.weight == other.weight;
        }
        return false;
    }
}

