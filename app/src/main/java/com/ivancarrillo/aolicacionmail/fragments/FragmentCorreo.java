package com.ivancarrillo.aolicacionmail.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ivancarrillo.aolicacionmail.R;
import com.ivancarrillo.aolicacionmail.app.Mail;

public class FragmentCorreo extends Fragment {

    private TextView tvSubject, tvFrom, tvBody;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_correo, container, false);

        tvSubject = view.findViewById(R.id.tvSubjectValue);
        tvFrom = view.findViewById(R.id.tvFromValue);
        tvBody = view.findViewById(R.id.tvBodyMessage);

        return view;
    }

    // Metodo público para actualizar la UI desde la Activity
    public void renderData(Mail mail) {
        if (mail != null) {
            tvSubject.setText(mail.getSubject());
            tvFrom.setText(mail.getSenderName() + " (" + mail.getSenderName() + "@example.com)");
            tvBody.setText(mail.getMessage());
        }
    }
}