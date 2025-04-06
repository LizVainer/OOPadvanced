package components;

public class Address {
    private int zip;
    private int street;


    //ctor
    public Address(int zip,int street) {

        this.street = street;
        this.zip = zip;
    }
    //getters
    public int getZip(){
        return this.zip;
    }
    public int getStreet(){
        return this.street;
    }
    //Extra methods
    @Override
    public boolean equals(Object obj){
        if((obj) instanceof Address) {
            Address other = (Address) obj;
            if(this.zip==other.zip && this.street == other.street) return true;
        }
        return false;
    }
    @Override
    public String toString(){
        return "Zip: "+this.zip + ", Street: "+this.zip;
    }

}
