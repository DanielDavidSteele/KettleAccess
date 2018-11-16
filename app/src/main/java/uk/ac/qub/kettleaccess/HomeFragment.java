package uk.ac.qub.kettleaccess;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.akaita.android.circularseekbar.CircularSeekBar;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DecimalFormat;


public class HomeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    //just to see this work
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        final CircularSeekBar seekBar = (CircularSeekBar) rootView.findViewById(R.id.seekBar);
        seekBar.setProgressTextFormat(new DecimalFormat("###,###,##0°C"));
        seekBar.setRingColor(Color.GREEN);

        seekBar.setOnCenterClickedListener(new CircularSeekBar.OnCenterClickedListener() {
            @Override
            public void onCenterClicked(CircularSeekBar seekBar, float progress) {
                Snackbar.make(seekBar, "Reset", Snackbar.LENGTH_SHORT)
                        .show();
                seekBar.setProgress(0);
            }
        });

        seekBar.setOnCircularSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar seekBar, float progress, boolean fromUser) {

                int red = 0, green = 0, blue = 0;

                red = (progress < 25) ? ((int) (2.55f * (4f * progress))) : (255);
                green = (progress < 25) ? (255) : 255 - (int) (255f * ((progress - 25f) / 75f));

                Paint colour = new Paint();
                colour.setARGB(255, red, green, blue);
                seekBar.setRingColor(colour.getColor());
            }
            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

            }
        });


        final RequestQueue queue = Volley.newRequestQueue(getContext());
        Button btn = (Button) rootView.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int intProgress = Math.round(seekBar.getProgress());
                Log.d("DEBUG","seekBar float: " + seekBar.getProgress() + ", seekBar int: " + intProgress);

                String url = ("http://10.6.1.143:8080?t="+intProgress);


                StringRequest stringRequest;
                stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                    }},
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error){
                                System.out.println("It didn't work");
                            }

                        });

                queue.add(stringRequest);


            }
        });

        return rootView;
    }
}

