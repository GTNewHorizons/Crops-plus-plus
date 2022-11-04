package com.github.bartimaeusnek.cropspp.items;

import com.github.bartimaeusnek.croploadcore.ModsLoaded;
import com.github.bartimaeusnek.croploadcore.OreDict;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public final class CreativeTab extends CreativeTabs {

    public static final CreativeTab cpp = new CreativeTab();

    public CreativeTab() {
        super("Cropspp");
    }

    @Override
    public Item getTabIconItem() {
        if (ModsLoaded.Natura) return OreDict.ISget("cropSaguaroBerry").getItem();
        else return CppItems.Goldfisch;
    }
}
