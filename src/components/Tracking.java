package components;

public class Tracking {
    private int time;
    private Node node;
    private Status status;
    public Tracking(int time,Node node,Status status ){
        this.time = time;
        this.node= node;
        this.status=status;

    }

    //Getters
     public int getTime(){
        return this.time;
    }
    public Node getNode(){
        return this.node;
    }
    public Status getStatus(){
        return this.status;
    }


    //Setters
    public void setTime(int value){
        this.time=value;
    }
    public void setNode(Node value){
        this.node=value;
    }
    public void setStatus(Status value){
        this.status=value;
    }

    //extra methods
    @Override
    public boolean equals(Object obj){
        if ((obj) instanceof Tracking){
            Tracking other = (Tracking)obj;
            if(this.time==other.time && this.status==other.status && this.node.getClass()==other.node.getClass()) return true;
        }
        return false;
    }

    public String toString(){
        return "time: "+time + ", Node: "+ node + ", Status: "+status;
    }

}
