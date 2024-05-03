package com.netheriteqf.colourarity;

import com.netheriteqf.colourarity.rarity.CustomRarity;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Formatting;

import java.util.*;

public class RarityRegister {
    public static ArrayList<CustomRarity> RARITIES = new ArrayList<>();

    public static CustomRarity registry(Formatting color, TagKey<Item> tag) {
        CustomRarity rarity = new CustomRarity(color, tag);
        RARITIES.add(rarity);
        return rarity;
    }
}
