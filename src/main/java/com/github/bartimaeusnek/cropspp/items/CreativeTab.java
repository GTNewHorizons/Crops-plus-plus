package com.github.bartimaeusnek.cropspp.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.github.bartimaeusnek.croploadcore.OreDict;

import gregtech.api.enums.Mods;

public final class CreativeTab extends CreativeTabs {

    public static final CreativeTab cpp = new CreativeTab();

    public CreativeTab() {
        super("Cropspp");
    }

    @Override
    public Item getTabIconItem() {
        if (Mods.Natura.isModLoaded()) return OreDict.ISget("cropSaguaroBerry").getItem();
        else return CppItems.Goldfisch;
    }
}
