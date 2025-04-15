//Elizaveta Vainer 332412055
//Shir Cohen 207365024
package components;

public class Address {
    private int zip;
    private int street;

    //constructor: gets zip and street, sets the address
    public Address(int zip, int street) {
        this.street = street;
        this.zip = zip;
    }

    //gets nothing
    //returns the zip code

    public int getZip() {
        return this.zip;
    }

    //gets nothing
    //returns the street number
    public int getStreet() {
        return this.street;
    }

    //gets an Object to compare
    //checks if both zip and street are equal
    //returns true if equal, false if not
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Address) {
            Address other = (Address) obj;
            return this.zip == other.zip && this.street == other.street;
        }
        return false;
    }

    //gets nothing
    //builds a string of the address
    //returns "zip-street" as a String
    @Override
    public String toString() {
        return this.zip + "-" + this.street;
    }
}
