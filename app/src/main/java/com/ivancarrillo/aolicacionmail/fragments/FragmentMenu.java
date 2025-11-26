package com.ivancarrillo.aolicacionmail.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ivancarrillo.aolicacionmail.R;
import com.ivancarrillo.aolicacionmail.adapters.MailAdapter;
import com.ivancarrillo.aolicacionmail.app.Mail;
import com.ivancarrillo.aolicacionmail.app.Util;

public class FragmentMenu extends Fragment {

    private DataListener callback;

    // 1. Definimos la Interfaz para comunicar con la Activity
    public interface DataListener {
        void sendData(Mail mail);
    }

    // 2. onAttach: Nos aseguramos de que la Activity implementa la interfaz
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            callback = (DataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " debe implementar DataListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        // 3. Configuración del RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewMenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Creamos el adaptador pasándole los datos dummy y el listener del click
        MailAdapter adapter = new MailAdapter(Util.getDummyData(), new MailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Mail mail) {
                // Cuando se hace click, avisamos a la Activity
                callback.sendData(mail);
            }
        });

        recyclerView.setAdapter(adapter);
        return view;
    }
}