package com.example.startgame;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

/*
Klash pou dhmiourgei ena pop-up mhnyma sthn arxijh o8onh
otan o xrhsths epileksei to Add Field sto menu (nav drawer)
 */
public class AddFieldDialogFragment extends DialogFragment {
    private Button addB, addS;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout


        View v = inflater.inflate(R.layout.add_field_dialog, null);
        addB = (Button)v.findViewById(R.id.buttonDialogBasketball);
        addS = (Button)v.findViewById(R.id.buttonDialogSoccer);

        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AddBasketballField.class);
                startActivity(i);
            }
        });
        addS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AddSoccerField.class);
                startActivity(i);

            }
        });
        builder.setView(v);
        builder.setMessage("What Field you want to Add?")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it

        return builder.create();

    }

}
