package Kotel.gui.clickgui;

import java.util.ArrayList;
import java.util.List;

public class Panel implements Component {
    private final String title;
    private final List<Component> children = new ArrayList<>();
    public Panel(String title) { this.title = title; }
    public void add(Component component) { children.add(component); }
    @Override public void render(StringBuilder out) { out.append("[Panel: ").append(title).append("]\n"); for (Component child : children) child.render(out); }
}
