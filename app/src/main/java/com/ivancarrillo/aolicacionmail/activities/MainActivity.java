package com.ivancarrillo.aolicacionmail.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.ivancarrillo.aolicacionmail.R;
import com.ivancarrillo.aolicacionmail.app.Mail;
import com.ivancarrillo.aolicacionmail.fragments.FragmentCorreo;
import com.ivancarrillo.aolicacionmail.fragments.FragmentMenu;

public class MainActivity extends AppCompatActivity implements FragmentMenu.DataListener {

    private boolean isMultiPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Determinamos si es multipanel buscando el segundo fragmento
        isMultiPanel = (getSupportFragmentManager().findFragmentById(R.id.fragmentCorreo) != null);
    }

    @Override
    public void sendData(Mail mail) {
        if (isMultiPanel) {
            // MODO TABLET: Llamamos al método renderData del fragmento que ya está en pantalla
            FragmentCorreo fragmentCorreo = (FragmentCorreo) getSupportFragmentManager().findFragmentById(R.id.fragmentCorreo);
            if (fragmentCorreo != null) {
                fragmentCorreo.renderData(mail);
            }
        } else {
            // MODO MÓVIL: Lanzamos la segunda Activity pasando los datos
            Intent intent = new Intent(this, CorreoActivity.class);
            intent.putExtra("subject", mail.getSubject());
            intent.putExtra("message", mail.getMessage());
            intent.putExtra("sender", mail.getSenderName());
            startActivity(intent);
        }
    }
}