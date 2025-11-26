package com.ivancarrillo.aolicacionmail.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ivancarrillo.aolicacionmail.app.Mail;

import java.util.ArrayList;

public class MailAdapter extends RecyclerView.Adapter<MailAdapter.MailHolder> {

    ArrayList<Mail> listaMails;
    OnItemClickListener onItemClickListener;

    public MailAdapter(ArrayList<Mail> listaMails, OnItemClickListener onItemClickListener) {
        this.listaMails = listaMails;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    public MailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(escogerItem, parent,false);
        return new MailHolder(view);
    }

    public void onBindViewHolder(@NonNull MailHolder holder, int position) {
        holder.assignData(listaMails.get(position));
    }

    @Override
    public int getItemCount() {
        return listaMails.size();
    }

    public class MailHolder extends RecyclerView.ViewHolder {
        private String subject;
        private String message;
        private String senderName;
        private String color;
        public MailHolder(@NonNull View itemView) {
            super(itemView);
            subject = itemView.findViewById();
            message = itemView.findViewById();
            senderName = itemView.findViewById();
            color = itemView.findViewById();
        }

        public void assignData(Mail mail) {
            subject.setText(mail.getSubject());
            message.setText(mail.getMessage());
            senderName.setText(mail.getSenderName());
            color.setBackgroundColor(Color.parseColor("#" + mail.getColor()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClick(mail);
                }
            });
        }
    }
    public interface OnItemClickListener {
        void OnItemClick(Mail mail);
    }
}
