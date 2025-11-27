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

    private TextView tvSubject;
    private TextView tvFrom;
    private TextView tvBody;

    public FragmentCorreo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_correo, container, false);

        tvSubject = (TextView) view.findViewById(R.id.tvSubjectValue);
        tvFrom = (TextView) view.findViewById(R.id.tvFromValue);
        tvBody = (TextView) view.findViewById(R.id.tvBodyMessage);

        return view;
    }

    public void renderData(Mail mail) {
        if (mail != null) {
            tvSubject.setText(mail.getSubject());
            tvFrom.setText(mail.getSenderName());
            tvBody.setText(mail.getMessage());
        }
    }
}