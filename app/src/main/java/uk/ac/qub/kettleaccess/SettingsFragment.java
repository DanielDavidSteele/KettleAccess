package uk.ac.qub.kettleaccess;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;


public class SettingsFragment extends Fragment  {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        Switch tempSwitch = (Switch) rootView.findViewById(R.id.switch1);
        Switch themeSwitch = (Switch) rootView.findViewById(R.id.switch2);

        FloatingActionButton info = (FloatingActionButton) rootView.findViewById(R.id.floatingActionButton);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog builder = new AlertDialog.Builder(getContext()).create();
                builder.setTitle("Alert");
                builder.setMessage("This app was developed by Daniel Steele " +
                        "(Student Number 40123486) for the completion of the " +
                        "MSc Software Development at Queen's University Belfast");
                builder.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
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
}


