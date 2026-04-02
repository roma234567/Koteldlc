package com.koteldlc.client.module.impl.visual;

import com.koteldlc.client.config.modules.BypassProfileRepository;
import com.koteldlc.client.gui.settings.BooleanSetting;
import com.koteldlc.client.gui.settings.ColorSetting;
import com.koteldlc.client.gui.settings.ModeSetting;
import com.koteldlc.client.gui.settings.SliderSetting;
import com.koteldlc.client.module.Category;
import com.koteldlc.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class ESPModule extends Module {
    private final ModeSetting mode;
    private final ModeSetting colorMode;
    private final ColorSetting customColor;
    private final SliderSetting outlineWidth;
    private final BooleanSetting chams;
    private final SliderSetting chamsAlpha;
    private final BooleanSetting arrows;
    private final ModeSetting arrowColorMode;
    private final SliderSetting arrowSize;

    public ESPModule(BypassProfileRepository repository) {
        super("ESP", "ESP с box/outline/chams/arrows", -1, Category.RENDER);
        mode = register(new ModeSetting("mode", "Box", List.of("Box", "Outline", "Glow", "Shader", "2D", "Wireframe", "Skeleton")));
        colorMode = register(new ModeSetting("colorMode", "Distance", List.of("Distance", "Health", "Team", "Custom", "Rainbow")));
        customColor = register(new ColorSetting("customColor", 0xFFFF0000));
        outlineWidth = register(new SliderSetting("outlineWidth", 2.0, 1.0, 5.0, 0.1));
        chams = register(new BooleanSetting("chams", false));
        chamsAlpha = register(new SliderSetting("chamsAlpha", 0.5, 0.1, 1.0, 0.05));
        arrows = register(new BooleanSetting("arrows", true));
        arrowColorMode = register(new ModeSetting("arrowColorMode", "Distance", List.of("Distance", "Health", "Team", "Custom")));
        arrowSize = register(new SliderSetting("arrowSize", 10.0, 5.0, 20.0, 0.5));
        register(new BooleanSetting("nametags", true));
        register(new BooleanSetting("healthBar", true));
        register(new BooleanSetting("armorBar", true));
        register(new BooleanSetting("distance", true));
        register(new BooleanSetting("boxFilled", false));
        register(new BooleanSetting("throughWalls", true));
        repository.registerDynamicSettings(this, "visual", "esp");
        repository.applyValueOverrides(this, "visual", "esp");
    }

    public void renderWorld(MatrixStack matrices) {
        if (!isToggled()) return;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.world == null || mc.player == null) return;

        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buffer = tess.begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glLineWidth(outlineWidth.getValue().floatValue());
        GL11.glEnable(GL11.GL_POLYGON_OFFSET_LINE);
        GL11.glPolygonOffset(-1.0f, -1.0f);

        for (PlayerEntity target : mc.world.getPlayers()) {
            if (target == mc.player || !target.isAlive()) continue;
            int color = getColor(target);
            float r = ((color >> 16) & 0xFF) / 255f;
            float g = ((color >> 8) & 0xFF) / 255f;
            float b = (color & 0xFF) / 255f;
            float a = chams.getValue() ? chamsAlpha.getValue().floatValue() : 1.0f;

            Matrix4f pos = matrices.peek().getPositionMatrix();
            Camera camera = mc.gameRenderer.getCamera();
            double x = target.getX() - camera.getPos().x;
            double y = target.getY() - camera.getPos().y;
            double z = target.getZ() - camera.getPos().z;
            addBasicBox(buffer, pos, x, y, z, r, g, b, a);
        }

        BufferRenderer.drawWithGlobalProgram(buffer.end());
        GL11.glDisable(GL11.GL_POLYGON_OFFSET_LINE);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    public void render2D(DrawContext context) {
        if (!isToggled() || !arrows.getValue()) return;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.world == null) return;

        int cx = mc.getWindow().getScaledWidth() / 2;
        int cy = mc.getWindow().getScaledHeight() / 2;
        float size = arrowSize.getValue().floatValue();

        for (PlayerEntity target : mc.world.getPlayers()) {
            if (target == mc.player || !target.isAlive()) continue;
            double dx = target.getX() - mc.player.getX();
            double dz = target.getZ() - mc.player.getZ();
            float yaw = (float) Math.toRadians(Math.toDegrees(Math.atan2(dz, dx)) - 90f - mc.player.getYaw());
            int px = (int) (cx + Math.cos(yaw) * 50);
            int py = (int) (cy + Math.sin(yaw) * 50);
            int color = switch (arrowColorMode.getValue()) {
                case "Health" -> target.getHealth() < 10 ? 0xFFFF4444 : 0xFF44FF44;
                case "Custom" -> customColor.getValue();
                default -> getColor(target);
            };
            drawArrow(context, px, py, size, color);
        }
    }

    private int getColor(PlayerEntity target) {
        return switch (colorMode.getValue()) {
            case "Health" -> target.getHealth() < 10 ? 0xFFFF4444 : 0xFF44FF44;
            case "Custom" -> customColor.getValue();
            case "Rainbow" -> 0xFF000000 | java.awt.Color.HSBtoRGB((System.currentTimeMillis() % 2500L) / 2500f, 1f, 1f);
            default -> {
                double d = MinecraftClient.getInstance().player.distanceTo(target);
                yield d < 6 ? 0xFFFF5555 : 0xFF55FF55;
            }
        };
    }

    private void drawArrow(DrawContext context, int x, int y, float size, int color) {
        int s = Math.round(size);
        context.fill(x, y - s, x + 2, y + s, color);
        context.fill(x - s, y, x + s, y + 2, color);
    }

    private void addBasicBox(BufferBuilder b, Matrix4f m, double x, double y, double z, float r, float g, float bl, float a) {
        double w = 0.35;
        double h = 1.9;
        b.vertex(m, (float) (x - w), (float) y, (float) (z - w)).color(r, g, bl, a);
        b.vertex(m, (float) (x + w), (float) y, (float) (z - w)).color(r, g, bl, a);
        b.vertex(m, (float) (x + w), (float) y, (float) (z - w)).color(r, g, bl, a);
        b.vertex(m, (float) (x + w), (float) (y + h), (float) (z - w)).color(r, g, bl, a);
        b.vertex(m, (float) (x + w), (float) (y + h), (float) (z - w)).color(r, g, bl, a);
        b.vertex(m, (float) (x - w), (float) (y + h), (float) (z - w)).color(r, g, bl, a);
        b.vertex(m, (float) (x - w), (float) (y + h), (float) (z - w)).color(r, g, bl, a);
        b.vertex(m, (float) (x - w), (float) y, (float) (z - w)).color(r, g, bl, a);
    }
}
