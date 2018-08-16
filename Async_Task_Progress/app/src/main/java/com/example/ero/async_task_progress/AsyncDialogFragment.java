package com.example.ero.async_task_progress;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AsyncDialogFragment extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.async_dialog_fragment, container, false);
        final TextView textView = view.findViewById(R.id.set_text);
        if (getArguments() != null) {
            String str = getArguments().getString(MainActivity.KAY_FR);
            textView.setText(str);
        }
        return view;
    }
}
