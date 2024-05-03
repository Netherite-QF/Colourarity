package com.netheriteqf.colourarity.rarity;

import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Formatting;

public record CustomRarity(Formatting color, TagKey<Item> tag) {

}
