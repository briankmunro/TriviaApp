package com.example.triviaapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ClearAllDialogFragment extends DialogFragment {

    private ClearAllConfirmed listener;
    public interface ClearAllConfirmed{
        void ClearStats();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof ClearAllConfirmed){
            listener = (ClearAllConfirmed) context;
        }else{
            throw new ClassCastException(context.toString() + "You must implement ClearAllConfirmed.");
        }
    }

    public ClearAllDialogFragment(){}

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Are you sure you want to delete all stats?")
                .setMessage("This cannot be undone.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.ClearStats();
                        dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
        return builder.create();
    }
}