package com.netheriteqf.colourarity.mixin;

import com.netheriteqf.colourarity.RarityRegister;
import com.netheriteqf.colourarity.rarity.CustomRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @ModifyVariable(method = "getTooltip", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/text/MutableText;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/MutableText;", ordinal = 0), ordinal = 0)
    private MutableText colour$getTooltip(MutableText value) {
        ItemStack item = (ItemStack) (Object) this;
        if (!RarityRegister.RARITIES.isEmpty()) {
            for (CustomRarity rarity : RarityRegister.RARITIES) {
                if (item.isIn(rarity.tag())) {
                    return Text.empty().append(item.getName()).formatted(rarity.color());
                }
            }
        }
        return value;
    }
}
