package components;

public class Van extends Truck{
    public Van(){
        super();
        System.out.println("Creating Van " + this);
    }

    public Van(String licensePlate,String truckModel ){
        super(licensePlate,truckModel);
        System.out.println("Creating van"+this);
    }
    @Override
    public void work(){
        if(!isAvailable()){
            setTimeLeft(getTimeLeft()-1);
            if(getTimeLeft()==0){
                Package p=getPackages().get(0);//get the first package in the array of packages
                Status current =p.getStatus();
                if(current==Status.COLLECTION){
                    p.setStatus(Status.BRANCH_STORAGE);
                   
                }
            }
        }
    }
}
