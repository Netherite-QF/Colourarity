package com.netheriteqf.colourarity.mixin.client;

import com.netheriteqf.colourarity.RarityRegister;
import com.netheriteqf.colourarity.rarity.CustomRarity;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Shadow
    private ItemStack currentStack;

    @ModifyVariable(method = "renderHeldItemTooltip", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/text/MutableText;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/MutableText;"), ordinal = 0)
    private MutableText colourarity$renderHeldItemTooltip(MutableText value) {
        if (!RarityRegister.RARITIES.isEmpty()) {
            for (CustomRarity rarity : RarityRegister.RARITIES) {
                if (currentStack.isIn(rarity.tag())) {
                    return Text.empty().append(currentStack.getName()).formatted(rarity.color());
                }

            }
        }
        return value;
    }
}
