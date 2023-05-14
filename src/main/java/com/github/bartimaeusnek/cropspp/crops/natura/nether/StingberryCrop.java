package com.github.bartimaeusnek.cropspp.crops.natura.nether;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.abstracts.BasicNetherBerryCrop;

import ic2.api.crops.ICropTile;
import mods.natura.common.NContent;

public class StingberryCrop extends BasicNetherBerryCrop {

    public StingberryCrop() {
        super();
    }

    public String name() {
        return "Stingberry";
    }

    public String[] attributes() {
        return new String[] { "Berry", "Toxic", "Bad", "Green", "Nether", "Addictive" };
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return new ItemStack(NContent.netherBerryItem, 2, 3);
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(NContent.netherBerryItem, 2, 3);
    }
}
