package uk.ac.qub.kettleaccess;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

public class BeanFragment extends Fragment {

    View v;
    private RecyclerView myRecyclerView;
    private List<Bean> lstBean;

    public BeanFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_bean, container, false);

        myRecyclerView = (RecyclerView) v.findViewById(R.id.bean_recyclerView);

        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(), lstBean, this);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerAdapter);

        return v;
    }



     @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       lstBean = new ArrayList<>();

        //loop for time-saving while building

        for(int count = 0; count < 100; count ++){
            lstBean.add(new Bean("True and Honest", "El Salvador", count, "This is a bean description", R.drawable.el_salvador));
        }
    }
}







