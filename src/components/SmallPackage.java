package components;

public class SmallPackage extends Package {
    private boolean acknowledge;

    //ctor
    public SmallPackage(Priority priority, Address senderAddress, Address destinationAddress, boolean acknowledge){
        super(priority, senderAddress, destinationAddress);
        this.acknowledge = acknowledge;


    }
    //get and set
    public boolean getAcknowledge() {
        return acknowledge;
    }

    public void setAcknowledge(boolean acknowledge) {
        this.acknowledge = acknowledge;
    }

    //Extra methods
    @Override
    public boolean equals(Object obj){
        if((obj) instanceof SmallPackage){
            SmallPackage other= (SmallPackage) obj;
            if(this.acknowledge==other.acknowledge) return true;
        }
        return false;
    }
    @Override
    public String toString(){return "Acknowledge: "+this.acknowledge;}
}

