# KotelDLC (Fabric 1.21.1, Maven)

KotelDLC is a modular Fabric client scaffold structured around managers, event bus, modules, settings, config serialization, and mixin entry points.

## Build

```bash
mvn clean package
```

## Architecture

- `KotelDLCClient` — client entrypoint
- `module/` — module API + registration
- `event/` — small event bus with priorities and cancellation
- `config/` — JSON persistence (`koteldlc/config.json`)
- `gui/` — ClickGUI/Panel/ModuleButton skeletons + setting widgets
- `hud/` — HUD overlay entry
- `mixin/` — mixin classes and configuration targets
- `utils/` — rendering/entity/rotation utility helpers

## Notes

This repository currently contains a **safe scaffold** rather than bypass logic or exploit code.
