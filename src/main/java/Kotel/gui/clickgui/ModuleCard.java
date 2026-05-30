package Kotel.gui.clickgui;

import Kotel.core.module.Module;

public record ModuleCard(Module module, int x, int y, int width, int height) implements Component {
    @Override
    public void render(StringBuilder out) {
        out.append("  ✦ ").append(module.name())
           .append(" — ").append(module.description())
           .append(" [settings=").append(module.settings().size()).append("]\n");
    }
}
