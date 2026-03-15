package com.koteldlc.client.module.modules.visual;

import com.koteldlc.client.gui.settings.ColorSetting;
import com.koteldlc.client.module.Category;
import com.koteldlc.client.module.Module;

public class ThemeEditor extends Module {
    public ThemeEditor() {
        super("Theme Editor", "Цветовая палитра интерфейса", -1, Category.RENDER);

        register(new ColorSetting("Основной", 0xFF1A1D2B));
        register(new ColorSetting("Визуальные модули", 0xFFA8C5FF));
        register(new ColorSetting("Текст", 0xFFFFFFFF));
        register(new ColorSetting("Неактивный текст", 0xFFB0B7C3));
        register(new ColorSetting("Текст заголовков", 0xFFE9ECF2));
        register(new ColorSetting("Премиум текст", 0xFFFFD87A));
        register(new ColorSetting("Слайдер", 0xFF9FC0FF));
        register(new ColorSetting("Круг слайдера", 0xFFB9D2FF));
        register(new ColorSetting("Окно слайдера", 0xFF2D3550));
        register(new ColorSetting("Переключатель", 0xFF8FB8FF));
        register(new ColorSetting("Неактивный переключатель", 0xFF56617F));
        register(new ColorSetting("Кнопка", 0xFF9CC2FF));
        register(new ColorSetting("Неактивная кнопка", 0xFF4C5A79));
        register(new ColorSetting("Обводка", 0xFF6D8CCF));
        register(new ColorSetting("Разделитель", 0xFF7284AD));
        register(new ColorSetting("Скролл бар", 0xFFA9C6FF));
        register(new ColorSetting("Поле", 0xFF1D2437));
        register(new ColorSetting("Неактивное поле", 0xFF161C2A));
        register(new ColorSetting("Лого", 0xFFA7C4FF));
        register(new ColorSetting("Текст в лого", 0xFFFFFFFF));
        register(new ColorSetting("Фон в подсказках", 0xFF151B29));
        register(new ColorSetting("Текст в подсказках", 0xFFF3F6FF));
        register(new ColorSetting("Фон окон", 0xCC101521));
    }
}
