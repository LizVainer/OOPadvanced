package components;

public class NonStandardPackage extends Package {
    private int width;
    private int length;
    private int height;


    //ctor
    public NonStandardPackage(Priority priority, Address senderAddress, Address destinationAddress,
                              int width, int length, int height) {
        super(priority, senderAddress, destinationAddress);
        this.width = width;
        this.length = length;
        this.height = height;
    }

    //getters
    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    //setters
    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object obj) {

        if ((obj instanceof NonStandardPackage)) {
            NonStandardPackage other = (NonStandardPackage) obj;
            if( this.height==other.height && this.width==other.width && this.length==other.length) return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return super.toString() + String.format(" | Dimensions: %dx%dx%d", width, length, height);
    }
}
