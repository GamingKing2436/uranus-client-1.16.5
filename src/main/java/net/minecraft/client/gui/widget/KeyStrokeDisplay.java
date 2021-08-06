package net.minecraft.client.gui.widget;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.client.util.InputMappings;

public class KeyStrokeDisplay extends Widget {
    public static final ResourceLocation KEY_LOCATION = new ResourceLocation("textures/gui/key.png");
    int color;
    int key;
    public KeyStrokeDisplay(int x, int y, int width, int height, ITextComponent message, int key, int color) {
        super(x, y, width, height, message);
        this.color = color;
        this.key = key;
        }
    
    protected float[] getcolor(boolean keydown) {
        float[] rgb = {0.0F,0.0F,0.0F,0.5F};
        if (keydown) {
           rgb[0] = (float)(color >> 16 & 255) / 255.0F;
           rgb[1] = (float)(color >> 8 & 255) / 255.0F;
           rgb[2] = (float)(color & 255) / 255.0F;
           rgb[3] = 1.0F;
        }
        return rgb;
     }

     public void render(MatrixStack p_230431_1_) {
        Minecraft minecraft = Minecraft.getInstance();
        FontRenderer fontrenderer = minecraft.fontRenderer;
        minecraft.getTextureManager().bindTexture(KEY_LOCATION);
        float[] i;
        if (key < 8) {
           i = this.getcolor(GLFW.glfwGetMouseButton(Minecraft.getInstance().getMainWindow().getHandle(), key) == 1);
        } else {
           i = this.getcolor(InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), key));
        }
        RenderSystem.color4f(i[0],i[1],i[2],i[3]);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        this.blit(p_230431_1_, this.x, this.y, 0, 0, this.width, this.height);
        //this.blit(p_230431_1_, this.x + this.width / 2, this.y, 40, 106, this.width / 2, this.height);
        //this.renderBg(p_230431_1_, minecraft, p_230431_2_, p_230431_3_);
        int j = this.active ? 16777215 : 10526880;
        drawCenteredString(p_230431_1_, fontrenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, color);
     }
}
