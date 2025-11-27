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
import com.ivancarrillo.aolicacionmail.models.Mail;
import com.ivancarrillo.aolicacionmail.app.Util;

import io.realm.Realm;
import io.realm.RealmResults;

public class FragmentMenu extends Fragment {
    Realm realm;
    private RealmResults<Mail> listaMails;
    private DataListener callback;

    public FragmentMenu() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        //Obtener datos de la BBDD
        realm = Realm.getDefaultInstance();
        listaMails = realm.where(Mail.class).findAll();

        // Configuración del RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewMenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Creamos el adaptador pasándole la lista de mails y el listener del click
        MailAdapter adapter = new MailAdapter(listaMails, new MailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Mail mail) {
                // Cuando se hace click avisamos a la Activity
                callback.sendData(mail);
            }
        });

        recyclerView.setAdapter(adapter);

        return view;
    }

    // Definimos la interfaz para comunicar con la Activity
    public interface DataListener {
        void sendData(Mail mail);
    }

    // Nos aseguramos de que la Activity implementa la interfaz
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            callback = (DataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " tiene que implementar DataListener");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close(); // Cerramos la conexión a la BBDD cuando se destruye el fragment
    }
}