package com.ivancarrillo.aolicacionmail.adapters;

import android.graphics.Color;
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

        public void assignData(Mail mail) {
            tvTitle.setText(mail.getSubject());
            tvSnippet.setText(mail.getMessage());

            // Logica del Avatar: Inicial y Color
            if (mail.getSenderName() != null && !mail.getSenderName().isEmpty()) {
                txtAvatar.setText(mail.getSenderName().substring(0, 1).toUpperCase());
            }
            // Parseamos el color
            String colorHex = "#" + mail.getColor();
            cardAvatar.setCardBackgroundColor(Color.parseColor(colorHex));

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