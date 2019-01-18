package uk.ac.qub.kettleaccess;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
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

    String name;
    String origin;
    public boolean tempFlag;
    public boolean tempValue = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        final CircularSeekBar seekBar = (CircularSeekBar) rootView.findViewById(R.id.seekBar);

        //tempValue = ((MainActivity)getActivity()).changeTemp;

        /*
        FAHRENHEIT SEEKBAR

        if(tempValue){
            seekBar.setProgressTextFormat(new DecimalFormat("0°F"));

            seekBar.setMin(32);
            seekBar.setMax(212);
            seekBar.setProgress(32);
            seekBar.setRingColor(Color.GREEN);


            seekBar.setOnCenterClickedListener(new CircularSeekBar.OnCenterClickedListener() {
                @Override
                public void onCenterClicked(CircularSeekBar seekBar, float progress) {
                    Snackbar.make(seekBar, "Reset", Snackbar.LENGTH_SHORT)
                            .show();
                    seekBar.setProgress(32);
                }
            });

            if (((MainActivity)getActivity()).useTemp){
                int fTemp = (((MainActivity)getActivity()).test);
                seekBar.setProgress(
                        ((fTemp)*9/5)+32
                );
                setColour(seekBar, (int)((seekBar.getProgress()-32)*5/9));
            }

            seekBar.setOnCircularSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
                @Override
                public void onProgressChanged(CircularSeekBar seekBar, float progress, boolean fromUser) {

                    ((MainActivity)getActivity()).useTemp = false;
                    setColour(seekBar, (int)((progress-32)*5/9));

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




                    AlertDialog builder = new AlertDialog.Builder(getContext()).create();
                    builder.setTitle("Alert");
                    builder.setMessage("The kettle will boil at "+String.format("%.0f",seekBar.getProgress()) +"°C");
                    builder.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    int intProgress = Math.round(seekBar.getProgress());
                                    Log.d("DEBUG","seekBar float: " + ((seekBar.getProgress()-32)*5/9) + ", seekBar int: " + intProgress);

                                    String url = ("http://10.6.1.83:8080?d="+((intProgress-32)*5/9));


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

                                    dialog.dismiss();
                                }
                            });
                    builder.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    builder.show();


                }
            });




        CELSIUS SEEKBAR

        } else {   }  */

            seekBar.setProgressTextFormat(new DecimalFormat("0°C"));

            seekBar.setRingColor(Color.GREEN);


            seekBar.setOnCenterClickedListener(new CircularSeekBar.OnCenterClickedListener() {
                @Override
                public void onCenterClicked(CircularSeekBar seekBar, float progress) {
                    Snackbar.make(seekBar, "Reset", Snackbar.LENGTH_SHORT)
                            .show();
                    seekBar.setProgress(0);
                }
            });

            if (((MainActivity)getActivity()).useTemp){
                seekBar.setProgress(((MainActivity)getActivity()).test);
                setColour(seekBar, (int)seekBar.getProgress());
            }

            seekBar.setOnCircularSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
                @Override
                public void onProgressChanged(CircularSeekBar seekBar, float progress, boolean fromUser) {

                    ((MainActivity)getActivity()).useTemp = false;
                    setColour(seekBar, (int)progress);

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




                    AlertDialog builder = new AlertDialog.Builder(getContext()).create();
                    builder.setTitle("Alert");
                    builder.setMessage("The kettle will boil at "+String.format("%.0f",seekBar.getProgress()) +"°C");
                    builder.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    int intProgress = Math.round(seekBar.getProgress());
                                    Log.d("DEBUG","seekBar float: " + seekBar.getProgress() + ", seekBar int: " + intProgress);

                                    String url = ("http://10.6.1.83:8080?d="+intProgress);


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

                                    dialog.dismiss();
                                }
                            });
                    builder.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    builder.show();


                }
            });







        return rootView;
    }

    private void setColour(CircularSeekBar seekBar, int progress) {
        int red, green, blue = 0;

        red = (progress < 25) ? (int) ((2.55 * (4 * progress))) : (255);
        green = (progress < 25) ? (255) : 255 - (int) (255f * ((progress - 25f) / 75f));

        Paint colour = new Paint();
        colour.setARGB(255, red, green, blue);
        seekBar.setRingColor(colour.getColor());
    }
}

