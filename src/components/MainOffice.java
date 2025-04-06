
package components;

import java.util.ArrayList;
import java.util.Random;
public class MainOffice {
    public static int clock=0;
    private Hub hub;
    private ArrayList<Package> packages;

    public MainOffice(int branches, int trucksForBranch) {
        clock = 0;
        packages = new ArrayList<>();
        hub = new Hub();
        for (int i = 0; i < trucksForBranch; i++) {//the standard truckforbranch
            hub.addTruck(new StandardTruck());
        }

        hub.addTruck(new NonStandardTruck());//one non standard one

        for (int i = 0; i < branches; i++) {//new branch for adding all the trucks to
            Branch branch = new Branch();

            for (int j = 0; j < trucksForBranch; j++) {
                branch.addTruck(new Van());
            }

            hub.addBranch(branch);
        }
    }


    public void play(int playTime){
        tick();
    }

    public void tick(){
        MainOffice.clock++;
        hub.work();



    }
    public void addPackage(){
        if(MainOffice.clock%5==0) {
            int type = (int) (Math.random() * 3) + 1;
            Package newPackage = null;
            int typePriority = (int)(Math.random()*3)+1;
            Priority priority=null;
            int zipSender = (int)(Math.random() * 10);
            int streetSender = (int)(Math.random() * 900000) + 100000;
            Address sender = new Address(zipSender, streetSender);

            int zipReciever = (int)(Math.random() * 10);
            int streetReciever= (int)(Math.random() * 900000) + 100000;
            Address reciever= new Address(zipReciever, streetReciever);


            switch (typePriority){
                case 1:
                    priority=Priority.LOW;
                    break;
                case 2:
                    priority=Priority.STANDARD;
                    break;
                case 3:
                    priority=Priority.HIGH;

            }


            switch (type) {
                case 1://small package
                    int ack = (int) (Math.random() *2)+1;
                    boolean acknowledge;
                    if(ack==0) { acknowledge=true;} else { acknowledge=false;}
                    newPackage = new SmallPackage(priority,sender,reciever,acknowledge);
                    this.packages.add(newPackage);
                    System.out.println("Created SmallPackage");
                    break;

                case 2://standard package
                    int weight = (int) (Math.random() * 10) + 1;
                    newPackage = new StandardPackage(priority,sender,reciever,weight);
                    System.out.println("Created StandardPackage");
                    break;

                case 3://non standard package
                    int height =(int ) (Math.random() * 400) + 1;
                    int width = (int) (Math.random()* 500)+1;
                    int length = (int)(Math.random()* 1000)+1;
                    newPackage = new NonStandardPackage(priority,sender,reciever,width,length,height);
                    System.out.println("Created NonStandardPackage");
                    break;
            }
        }
    }
}
