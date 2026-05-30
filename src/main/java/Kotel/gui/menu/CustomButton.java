package Kotel.gui.menu;

public record CustomButton(String label, int x, int y, int width, int height) {
    public boolean contains(int mouseX, int mouseY) { return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height; }
}
