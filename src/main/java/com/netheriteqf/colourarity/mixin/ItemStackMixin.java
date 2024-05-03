package com.netheriteqf.colourarity.mixin;

import com.netheriteqf.colourarity.RarityRegister;
import com.netheriteqf.colourarity.rarity.CustomRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract boolean isIn(TagKey<Item> tag);

    @Shadow
    public abstract Text getName();
    
    @ModifyVariable(method = "getTooltip", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/text/MutableText;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/MutableText;", ordinal = 0), ordinal = 0)
    private MutableText colour$getTooltip(MutableText value) {
        if (!RarityRegister.RARITIES.isEmpty()) {
            for (CustomRarity rarity : RarityRegister.RARITIES) {
                if (isIn(rarity.tag())) {
                    return Text.empty().append(getName()).formatted(rarity.color());
                }
            }
        }
        return value;
    }
}
