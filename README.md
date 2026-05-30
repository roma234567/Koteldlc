# KotelDLC (Minecraft 1.21.4)

KotelDLC is a Java 21 client-side utility scaffold for Minecraft/Fabric-style projects.  It contains the requested package layout under `src/main/java/Kotel`, a themed menu/ClickGUI preview layer, module/settings/event managers, resource folders, and a small Python launcher stub.

> Safety note: this repository intentionally keeps modules as UI/configuration placeholders.  It does not implement automated combat, anti-cheat bypasses, packet manipulation, server-rule evasion, or other unfair multiplayer behavior.

## Build

```bash
gradle build --no-daemon
```

The Gradle build compiles the safe `Kotel/**` scaffold.  The legacy `com/koteldlc/**` sources remain in the repository, but are excluded from the Gradle source set because they require Minecraft/Fabric dependencies that are not part of this lightweight scaffold.

## Launcher preview

```bash
python3 launcher/main.py
```

## Architecture

- `Kotel.client.KotelMod` — central entry point and manager wiring.
- `Kotel.core.event` — base event and event manager.
- `Kotel.core.module` — `Module`, categories, and default module registration.
- `Kotel.core.setting` — boolean, number, mode, and color settings.
- `Kotel.core.theme` / `Kotel.core.resources` — theme and resource helpers.
- `Kotel.gui.menu` — custom main menu model and button layout.
- `Kotel.gui.clickgui` — panel/component-based ClickGUI preview.
- `Kotel.modules` — requested module structure with safe placeholders.
- `Kotel.utils` — math, render, entity, rotation, movement, network, timer, interpolation, projectile, crystal, jello, and event helpers.
- `Kotel.antileak` — local development-only placeholders; no invasive telemetry or DRM.
