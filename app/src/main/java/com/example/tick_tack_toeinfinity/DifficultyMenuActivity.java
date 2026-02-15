// Файл: DifficultyMenuActivity.java
package com.example.tick_tack_toeinfinity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tick_tack_toeinfinity.game.GameEngine;
import com.google.android.material.button.MaterialButton;

public class DifficultyMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_menu);

        MaterialButton btnEasy = findViewById(R.id.btnEasy);
        MaterialButton btnMedium = findViewById(R.id.btnMedium);
        MaterialButton btnHard = findViewById(R.id.btnHard);

        btnEasy.setOnClickListener(v -> startGame(GameEngine.Difficulty.EASY));
        btnMedium.setOnClickListener(v -> startGame(GameEngine.Difficulty.MEDIUM));
        btnHard.setOnClickListener(v -> startGame(GameEngine.Difficulty.HARD));
    }

    private void startGame(GameEngine.Difficulty difficulty) {
        // Создаем Intent для запуска GameActivity
        Intent intent = new Intent(this, GameActivity.class);
        // Кладем в него выбранный уровень сложности
        intent.putExtra("DIFFICULTY_LEVEL", difficulty);
        // Запускаем игровой экран
        startActivity(intent);
    }
}
