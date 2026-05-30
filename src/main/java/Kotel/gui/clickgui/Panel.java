package Kotel.gui.clickgui;

import java.util.ArrayList;
import java.util.List;

public class Panel implements Component {
    private final String title;
    private final int x;
    private final int y;
    private final int width;
    private final List<Component> children = new ArrayList<>();

    public Panel(String title) {
        this(title, 0, 0, 420);
    }

    public Panel(String title, int x, int y, int width) {
        this.title = title;
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public void add(Component component) { children.add(component); }

    @Override
    public void render(StringBuilder out) {
        out.append("[Panel: ").append(title).append(" @ ").append(x).append(',').append(y).append(" w=").append(width).append("]\n");
        for (Component child : children) child.render(out);
    }
}
