package com.example.samsungfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView theoremListView;
    private TheoremAdapter adapter;
    private List<Theorem> theoremList;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theoremListView = findViewById(R.id.theorem_list_view);
        dbHelper = new DatabaseHelper(this);

        FloatingActionButton addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // При нажатии на кнопку "+" открываем активити для добавления новой теоремы
                startActivity(new Intent(MainActivity.this, AddTheoremActivity.class));
            }
        });

        // Получаем список всех теорем из базы данных
        theoremList = dbHelper.getAllTheorems();

        // Создаем адаптер для отображения заголовков теорем в ListView
        adapter = new TheoremAdapter(this, theoremList);
        theoremListView.setAdapter(adapter);

        // Обработчик нажатия на заголовок теоремы
        theoremListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранную теорему по позиции в списке
                Theorem selectedTheorem = theoremList.get(position);
                // Открываем экран с текстом выбранной теоремы
                Intent intent = new Intent(MainActivity.this, TheoremDetailsActivity.class);
                intent.putExtra("theorem_title", selectedTheorem.getTitle());
                intent.putExtra("theorem_content", selectedTheorem.getContent());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Обновляем список теорем при возвращении на главный экран
        theoremList.clear();
        theoremList.addAll(dbHelper.getAllTheorems());
        adapter.notifyDataSetChanged();
    }
}
