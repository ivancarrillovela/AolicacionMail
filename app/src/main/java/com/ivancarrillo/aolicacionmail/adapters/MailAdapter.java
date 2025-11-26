package com.ivancarrillo.aolicacionmail.adapters;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.ivancarrillo.aolicacionmail.R;
import com.ivancarrillo.aolicacionmail.app.Mail;
import java.util.List;

public class MailAdapter extends RecyclerView.Adapter<MailAdapter.MailHolder> {

    private List<Mail> listaMails;
    private OnItemClickListener listener;

    public MailAdapter(List<Mail> listaMails, OnItemClickListener listener) {
        this.listaMails = listaMails;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CORRECCIÓN: Usamos R.layout.item_menu
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new MailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MailHolder holder, int position) {
        holder.assignData(listaMails.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return listaMails.size();
    }

    public static class MailHolder extends RecyclerView.ViewHolder {
        CardView cardAvatar;
        TextView txtAvatar;
        TextView tvTitle;
        TextView tvSnippet;

        public MailHolder(@NonNull View itemView) {
            super(itemView);
            // Enlazamos con los IDs de item_menu.xml
            cardAvatar = itemView.findViewById(R.id.cardAvatar);
            txtAvatar = itemView.findViewById(R.id.txtAvatar);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSnippet = itemView.findViewById(R.id.tvSnippet);
        }

        public void assignData(Mail mail, OnItemClickListener listener) {
            tvTitle.setText(mail.getSubject());
            tvSnippet.setText(mail.getMessage());

            // Lógica del Avatar: Inicial y Color
            if (mail.getSenderName() != null && !mail.getSenderName().isEmpty()) {
                txtAvatar.setText(mail.getSenderName().substring(0, 1).toUpperCase());
            }
            // Parseamos el color (viene sin # en tu Util)
            String colorHex = "#" + mail.getColor();
            cardAvatar.setCardBackgroundColor(Color.parseColor(colorHex));

            itemView.setOnClickListener(v -> listener.onItemClick(mail));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Mail mail);
    }
}