package components;

public class StandardTruck extends Truck{
    private int maxWeight;
    private Branch destination;
    public StandardTruck(){
        super();
    }
    public StandardTruck(String licensePlate,String truckModel,int maxWeight){
        super(licensePlate,truckModel);
        this.maxWeight=maxWeight;
    }
    @Override
    public void work() {

    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StandardTruck) {
            StandardTruck other = (StandardTruck) obj;
            if(this.maxWeight==other.maxWeight &&  this.destination==other.destination) return true;
        }
        return false;
    }
    @Override
    public String toString(){return "Max Weight: "+this.maxWeight+", Destination: "+this.destination;}

}
