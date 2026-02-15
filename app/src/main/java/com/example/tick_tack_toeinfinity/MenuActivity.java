package com.example.tick_tack_toeinfinity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tick_tack_toeinfinity.R; // Замените на ваш пакет
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private GameModeAdapter adapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        // 1. Создаем список режимов игры
        List<GameModeAdapter.GameMode> modes = new ArrayList<>();
        // TODO: Замените R.drawable.ic_... на ваши реальные иконки
        modes.add(new GameModeAdapter.GameMode("Игрок vs Игрок", R.drawable.ic_player_vs_player));
        modes.add(new GameModeAdapter.GameMode("Игрок vs Компьютер", R.drawable.ic_player_vs_ai));
        modes.add(new GameModeAdapter.GameMode("История игр", R.drawable.ic_history));

        // 2. Создаем и устанавливаем адаптер
        adapter = new GameModeAdapter(modes);
        viewPager.setAdapter(adapter);

        // 3. Настраиваем эффект "карусели"
        setupCarouselEffect();

        // 4. Связываем ViewPager2 с индикатором-точками (TabLayout)
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            // Ничего не делаем, нам нужны только точки
        }).attach();
    }

    private void setupCarouselEffect() {
        // Отступы, чтобы были видны соседние карточки
        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(3); // Сколько страниц держать в памяти

        // Уменьшаем размер соседних страниц
        float MIN_SCALE = 0.85f;
        viewPager.setPageTransformer((page, position) -> {
            int pageWidth = page.getWidth();
            int pageHeight = page.getHeight();

            if (position < -1 || position > 1) { // Страница далеко за экраном
                page.setAlpha(0f);
            } else {
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    page.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    page.setTranslationX(-horzMargin + vertMargin / 2);
                }
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
                page.setAlpha(MIN_SCALE + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_SCALE));
            }
        });
    }
}
