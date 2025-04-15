//Elizaveta Vainer 332412055
//Shir Cohen 207365024
package components;

import enums.Status;

public class Tracking {
    private int time;
    private Node node;
    private Status status;

    //constructor
    //gets system time, node (location), and package status
    //saves them as a single tracking record
    public Tracking(int time, Node node, Status status) {
        this.time = time;
        this.node = node;
        this.status = status;
    }

    //gets nothing
    //returns the time of this tracking record
    public int getTime() {
        return time;
    }

    //gets nothing
    //returns the node where the package was at this time
    public Node getNode() {
        return node;
    }

    //gets nothing
    //returns the status of the package at this time
    public Status getStatus() {
        return status;
    }

    //gets nothing
    //builds a string describing the tracking entry
    //returns the string (time + location + status)
    @Override
    public String toString() {
        String name;
        if (node == null) {
            name = "Customer";
        } else {
            name = node.toString();
        }
        return time + ": " + name + ", status=" + status;
    }

    //gets another object
    //checks if it is a Tracking with same time, status and node
    //returns true if equal, false otherwise
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tracking) {
            Tracking other = (Tracking) obj;
            return this.time == other.time &&
                    this.status == other.status &&
                    ((this.node == null && other.node == null) || (this.node != null && this.node.equals(other.node)));
        }
        return false;
    }
}
