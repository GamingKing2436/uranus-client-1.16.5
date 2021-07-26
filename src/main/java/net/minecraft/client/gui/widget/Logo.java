package net.minecraft.client.gui.widget;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.util.math.MathHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.AbstractButton;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Logo extends AbstractButton {
   public static final Logo.ITooltip NO_TOOLTIP = (p_238488_0_, p_238488_1_, p_238488_2_, p_238488_3_) -> {
   };
   protected final Logo.IPressable onPress;
   protected final Logo.ITooltip onTooltip;

   public Logo(int p_i232255_1_, int p_i232255_2_, int p_i232255_3_, int p_i232255_4_, ITextComponent p_i232255_5_, Logo.IPressable p_i232255_6_) {
      this(p_i232255_1_, p_i232255_2_, p_i232255_3_, p_i232255_4_, p_i232255_5_, p_i232255_6_, NO_TOOLTIP);
   }

   public Logo(int p_i232256_1_, int p_i232256_2_, int p_i232256_3_, int p_i232256_4_, ITextComponent p_i232256_5_, Logo.IPressable p_i232256_6_, Logo.ITooltip p_i232256_7_) {
      super(p_i232256_1_, p_i232256_2_, p_i232256_3_, p_i232256_4_, p_i232256_5_);
      this.onPress = p_i232256_6_;
      this.onTooltip = p_i232256_7_;
   }

   public void onPress() {
      this.onPress.onPress(this);
   }

   public void renderButton(MatrixStack p_230431_1_, int p_230431_2_, int p_230431_3_, float p_230431_4_) {
      renderButtonsuper(p_230431_1_, p_230431_2_, p_230431_3_, p_230431_4_);
      if (this.isHovered()) {
         this.renderToolTip(p_230431_1_, p_230431_2_, p_230431_3_);
      }

   }
   public void renderButtonsuper(MatrixStack p_230431_1_, int p_230431_2_, int p_230431_3_, float p_230431_4_) {
      Minecraft minecraft = Minecraft.getInstance();
      FontRenderer fontrenderer = minecraft.font;
      minecraft.getTextureManager().bind(WIDGETS_LOCATION);
      RenderSystem.color4f(0.96F, 0.58F, 0.13F, this.alpha);
      //int i = this.getYImage(this.isHovered());
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.enableDepthTest();
      this.blit(p_230431_1_, this.x, this.y, 40, 106, this.width, this.height);
      //this.blit(p_230431_1_, this.x + this.width / 2, this.y, 40, 106, this.width / 2, this.height);
      this.renderBg(p_230431_1_, minecraft, p_230431_2_, p_230431_3_);
      int j = this.active ? 16777215 : 10526880;
      drawCenteredString(p_230431_1_, fontrenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
   }

   public void renderToolTip(MatrixStack p_230443_1_, int p_230443_2_, int p_230443_3_) {
      this.onTooltip.onTooltip(this, p_230443_1_, p_230443_2_, p_230443_3_);
   }

   @OnlyIn(Dist.CLIENT)
   public interface IPressable {
      void onPress(Logo p_onPress_1_);
   }

   @OnlyIn(Dist.CLIENT)
   public interface ITooltip {
      void onTooltip(Logo p_onTooltip_1_, MatrixStack p_onTooltip_2_, int p_onTooltip_3_, int p_onTooltip_4_);
   }
}