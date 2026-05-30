# KotelDLC (Minecraft 1.21.4)

KotelDLC is a Java 21 premium client-style scaffold for Minecraft/Fabric-style projects.  The codebase now keeps the requested `Kotel/` package only, with module registration, two ClickGUI views, theme/resource assets, utility classes, and a vector-only Python loader.

> Safety note: modules are implemented as configurable client/UI features and development placeholders.  The project does not include anti-cheat bypass logic, packet abuse, credential theft, or hidden binary assets.

## Build

```bash
gradle build --no-daemon
```

## Launcher preview

```bash
python3 launcher/main.py
```

For the custom Tkinter loader window, call `Launcher().run_gui()` from `launcher/launcher.py`.  The loader draws its Lucky-style background and settings icon with vectors/canvas commands, so no binary images are required.

## Architecture

- `Kotel.client.KotelMod` — central entry point and explicit registration for all Combat, Movement, Visual, Misc, and Player modules.
- `Kotel.core.module` — `Module`, `PremiumModule`, `ModuleManager`, and categories.
- `Kotel.core.setting` — boolean, number, mode, and color settings used by every premium module.
- `Kotel.gui.clickgui` — two-view ClickGUI model: `LUCKY_CARDS` and `COMPACT_LIST`.
- `Kotel.gui.menu` — custom Lucky-style main menu model.
- `Kotel.modules` — requested module list with richer descriptions/settings/status lines.
- `Kotel.utils` — math, render, entity, rotation, movement, network, timer, interpolation, projectile, crystal, jello, and event helpers.
- `src/main/resources/assets/koteldlc` — text-only resource folders for backgrounds, icons, GUI layout, themes, particles, settings, shaders, textures, fonts, sounds, and config.
