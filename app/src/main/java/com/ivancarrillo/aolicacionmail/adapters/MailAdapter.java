package com.ivancarrillo.aolicacionmail.adapters;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.ivancarrillo.aolicacionmail.R;
import com.ivancarrillo.aolicacionmail.models.Mail;
import java.util.List;

public class MailAdapter extends RecyclerView.Adapter<MailAdapter.MailHolder> {

    private List<Mail> listaMails;
    private OnItemClickListener onItemClickListener;

    public MailAdapter(List<Mail> listaMails, OnItemClickListener onItemClickListener) {
        this.listaMails = listaMails;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new MailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MailHolder holder, int position) {
        holder.assignData(listaMails.get(position));
    }

    @Override
    public int getItemCount() {
        return listaMails.size();
    }

    public class MailHolder extends RecyclerView.ViewHolder {
        TextView txtAvatar;
        TextView tvTitle;
        TextView tvSnippet;

        public MailHolder(@NonNull View itemView) {
            super(itemView);
            // Enlazamos con los IDs de item_menu.xml
            txtAvatar = itemView.findViewById(R.id.txtAvatar);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSnippet = itemView.findViewById(R.id.tvSnippet);
        }

        public void assignData(Mail mail) {
            tvTitle.setText(mail.getSubject());
            tvSnippet.setText(mail.getMessage());

            // Logica para poner la inicial del avatar segun el correo del remitente
            if (mail.getSenderName() != null && !mail.getSenderName().isEmpty()) {
                txtAvatar.setText(mail.getSenderName().substring(0, 1).toUpperCase());
            } else {
                // Si no hay nombre de remitente se pone una A de anónimo
                txtAvatar.setText("A");
            }

            // Lógica para pintar el fondo del círculo con el color específico del mail
            Drawable circleDrawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.circulo);
            if (circleDrawable != null) {
                // Mutate es necesario para no cambiar el color de todos los círculos, solo este
                Drawable mutableDrawable = circleDrawable.mutate();
                try {
                    int color = Color.parseColor("#" + mail.getColor());
                    mutableDrawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                    txtAvatar.setBackground(mutableDrawable);
                } catch (Exception e) {
                    // Si falla se pone el gris por defecto
                    mutableDrawable.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                    txtAvatar.setBackground(mutableDrawable);
                }
            }

            // Listener del click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(mail);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Mail mail);
    }
}