package com.drew_benham.listapp.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.drew_benham.listapp.R;

import java.util.Objects;

public class AddMediaTypeDialog extends DialogFragment {
    private EditText addMediaTypeEdit;

    public AddMediaTypeDialog() {}

    public interface EditTypeDialogListener {
        void onFinishedEdit(String inputText);
    }

    public static AddMediaTypeDialog newInstance(String title) {
        AddMediaTypeDialog frag = new AddMediaTypeDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        addMediaTypeEdit = view.findViewById(R.id.mediaTypeEdit);
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        addMediaTypeEdit.requestFocus();
        Objects.requireNonNull(getDialog().getWindow()).setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //inflate view and initialize edit text.
        View view = inflater.inflate(R.layout.add_media_type, null);
        addMediaTypeEdit = view.findViewById(R.id.mediaTypeEdit);

        //build dialog and set click listeners.
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setTitle(title);
        final EditTypeDialogListener listener = (EditTypeDialogListener) getActivity();
        alertDialogBuilder.setPositiveButton("Add",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    //call the listener interface so that all implementations get updated.
                    listener.onFinishedEdit(addMediaTypeEdit.getText().toString());
                }
            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        return alertDialogBuilder.create();
    }
}
