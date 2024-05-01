package com.example.samsungfinalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.samsungfinalproject.Theorem;

import java.util.List;

public class TheoremAdapter extends ArrayAdapter<Theorem> {

    private Context context;
    private List<Theorem> theorems;

    public TheoremAdapter(Context context, List<Theorem> theorems) {
        super(context, 0, theorems);
        this.context = context;
        this.theorems = theorems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Получаем текущий объект Theorem
        Theorem theorem = theorems.get(position);

        // Проверяем, если convertView равен null, то заполняем его с помощью LayoutInflater
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        // Находим TextView в макете и устанавливаем заголовок теоремы
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(theorem.getTitle());

        return convertView;
    }
}
