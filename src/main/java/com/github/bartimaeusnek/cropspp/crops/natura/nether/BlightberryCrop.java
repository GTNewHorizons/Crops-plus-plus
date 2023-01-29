package com.github.bartimaeusnek.cropspp.crops.natura.nether;

import mods.natura.common.NContent;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.abstracts.BasicNetherBerryCrop;
import ic2.api.crops.ICropTile;

public class BlightberryCrop extends BasicNetherBerryCrop {

    public BlightberryCrop() {
        super();
    }

    public String name() {
        return "Blightberry";
    }

    public String[] attributes() {
        return new String[] { "Berry", "Toxic", "Bad", "Green", "Nether", "Addictive" };
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return new ItemStack(NContent.netherBerryItem, 2, 0);
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(NContent.netherBerryItem, 2, 0);
    }
}
