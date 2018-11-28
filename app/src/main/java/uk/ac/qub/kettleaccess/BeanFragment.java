package uk.ac.qub.kettleaccess;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Public class BeanFragment extends the functionality of the
 * Fragment class in the Android library
 */

public class BeanFragment extends Fragment {

    /**
     * Declaration of variables
     */

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


        /*final RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = ("http://10.6.1.143:8081");
        final Map<String, String> map = new HashMap<String, String>();
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //System.out.println(response);
                String[] responses = response.split("#");
                int i = 0;
                for(String str:responses) {
                    String cut = str.substring(6, str.length()-2);
                    map.put("Name"+i, cut);
                    //System.out.println(map.get("Name"));
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("No luck");
                    }
                });
        queue.add(stringRequest);*/



       lstBean = new ArrayList<>();

        //loop for time-saving while building

        for(int count = 0; count < 100; count ++){
            lstBean.add(new Bean("Frank and Honest", "El Salvador", count, "This is a bean description", R.drawable.el_salvador));
        }

    }
}







