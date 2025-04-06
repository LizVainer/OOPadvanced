package components;

import java.util.ArrayList;

public class Hub extends Branch{
    private ArrayList<Branch> branches;
    public Hub(){
        super("HUB");
    }

    public void addBranch(Branch branch){
        branches.add(branch);
    }
    @Override
    public void work(){

    }
}
