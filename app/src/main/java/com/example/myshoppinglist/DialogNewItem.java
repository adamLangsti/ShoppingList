package com.example.myshoppinglist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DialogNewItem extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_new_item, null);
        Button btnCancel = (Button) dialogView.findViewById(R.id.btnCancel);
        Button btnAdd = (Button) dialogView.findViewById(R.id.btnAdd);

        final EditText editTitle = (EditText) dialogView.findViewById(R.id.editTitle);

        builder.setView(dialogView).setMessage("Add new item");

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item newItem = new Item();
                newItem.setTitle(editTitle.getText().toString());


                MainActivity callingActivity = (MainActivity) getActivity();
                callingActivity.createNewItem(newItem);

                dismiss();

            }

        });
            return builder.create();
    }

    }

