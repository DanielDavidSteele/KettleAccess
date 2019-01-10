package uk.ac.qub.kettleaccess;

public class MergeThread extends Thread {

    RecyclerViewAdapter.MyViewHolder holder;

    public MergeThread(RecyclerViewAdapter.MyViewHolder holder) {
        super();
        this.holder = holder;
    }

}
