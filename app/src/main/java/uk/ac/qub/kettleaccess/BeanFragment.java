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

import org.json.JSONArray;
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
    Fragment mFragment;
    private RecyclerView myRecyclerView;
    private List<Bean> lstBean;
    private Bean bean = null;
    public JSONObject jsonObject = null;
    private RecyclerViewAdapter adapter;

    /**
     * Required public constructor
     */

    public BeanFragment() {

    }

    /**
     * OnCreateView method that boots the UI of the fragment
     *
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




        myRecyclerView = (RecyclerView) v.findViewById(R.id.bean_recyclerView);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        lstBean = new ArrayList<>();

        adapter = new RecyclerViewAdapter(getContext(), lstBean, this);


        myRecyclerView.setAdapter(adapter);

        return v;
    }

    /**
     * OnCreate method that sets the business logic behind the fragment
     *
     * @param savedInstanceState
     */


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = ("http://10.6.1.89/get.php");

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {

                                    jsonObject = new JSONObject(String.valueOf(response));
                                    JSONArray array = jsonObject.getJSONArray("beans");
                                    for (int i = 0; i < array.length(); i++){
                                        JSONObject beanItem = array.getJSONObject(i);
                                        bean = new Bean(
                                                beanItem.getString("name"),
                                                beanItem.getString("origin"),
                                                beanItem.getInt("temp"),
                                                beanItem.getString("desc"),
                                                beanItem.getInt("flag"));
                                        lstBean.add(bean);
                                    }



                                    adapter.notifyDataSetChanged();

                                    System.out.println(bean.getName());

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        queue.add(jsonObjectRequest);




    }


}





