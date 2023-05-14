package com.github.bartimaeusnek.cropspp.crops.natura.nether;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.abstracts.BasicNetherBerryCrop;

import ic2.api.crops.ICropTile;
import mods.natura.common.NContent;

public class DuskberryCrop extends BasicNetherBerryCrop {

    public DuskberryCrop() {
        super();
    }

    public String name() {
        return "Duskberry";
    }

    public String[] attributes() {
        return new String[] { "Berry", "Toxic", "Bad", "Gray", "Nether", "Addictive" };
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return new ItemStack(NContent.netherBerryItem, 2, 1);
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(NContent.netherBerryItem, 2, 1);
    }
}
