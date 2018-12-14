package uk.ac.qub.kettleaccess;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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

    /**
     * Required public constructor
     */

    public BeanFragment(){

    }

    /**
     * OnCreateView method that boots the UI of the fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /*
          Inflates the view "v" with the layout of "fragment_bean.xml"
          to the size of the container
         */
        v = inflater.inflate(R.layout.fragment_bean, container, false);

        //Instantiate recycler view
        myRecyclerView = (RecyclerView) v.findViewById(R.id.bean_recyclerView);

        //Instantiate recycler view adapter
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(), lstBean, this);

        //Setting the layout manager for the view
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Setting the adapter as the recycler view adapter
        myRecyclerView.setAdapter(recyclerAdapter);

        //Returns the view "v"
        return v;
    }

    /**
     * OnCreate method that sets the business logic behind the fragment
     * @param savedInstanceState
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = ("http://10.6.1.111/get.php");

            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        Bean bean = new Bean();


                        System.out.println(response);

                        try {
                            response.getString("name");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });



        queue.add(jsonObjectRequest);

        //Instantiation of the list of bean objects

       lstBean = new ArrayList<>();

        //loop for time-saving while building

        for(int count = 0; count < 100; count ++){
            lstBean.add(new Bean("Bleh", "El Salvador", count, "This is a bean description", R.drawable.el_salvador));
        }

    }
}





        /*

        final Map<String, String> map = new HashMap<String, String>();
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               System.out.println(response);

               String[] responses = response.split("#");
                int i = 0;
                for(String str:responses) {
                    //String cut = str.substring(6, str.length()-2);
                    map.put("Name"+i, str);
                    System.out.println(map.get("Name"));
                }
                System.out.println(response);


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("No luck");
                    }
                });
        queue.add(stringRequest);
*/


