package components;

public class NonStandardTruck extends Truck{
    private int width;
    private int length;
    private int height;

    public NonStandardTruck(){
        super();
    }
    public NonStandardTruck(String licensePlate,String truckModel,int length,int width,int height){
        super(licensePlate,truckModel);
        this.width=width;
        this.length=length;
        this.height=height;
    }
    @Override
    public void work() {

    }
    @Override
    public boolean equals(Object obj){
        if(obj instanceof NonStandardTruck){
            NonStandardTruck other= (NonStandardTruck) obj;
            if(this.width==other.width && this.height==other.height && this.length==other.length)return true;

        }
        return false;
    }
    @Override
    public String toString(){return "Width: "+this.width+",Height: "+this.height+", Length: "+this.length;}
}
