package com.ivancarrillo.aolicacionmail.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.ivancarrillo.aolicacionmail.R;
import com.ivancarrillo.aolicacionmail.app.Mail;
import com.ivancarrillo.aolicacionmail.fragments.FragmentCorreo;

public class CorreoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correo);

        // Recibir datos del Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String subject = extras.getString("subject");
            String message = extras.getString("message");
            String sender = extras.getString("sender");

            // Creamos un objeto Mail temporal para pasarlo al fragmento
            Mail mail = new Mail(subject, message, sender);

            // Buscamos el fragmento y le pasamos los datos
            FragmentCorreo fragment = (FragmentCorreo) getSupportFragmentManager().findFragmentById(R.id.fragment_second);
            // Nota: Asegúrate de que el ID en activity_correo.xml coincida.
            // En tu xml subido se llama @+id/fragment_second, asegúrate de usar ese ID.
            if (fragment != null) {
                fragment.renderData(mail);
            }
        }
    }
}