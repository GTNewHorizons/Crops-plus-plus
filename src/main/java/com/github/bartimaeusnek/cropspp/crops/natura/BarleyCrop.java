package com.github.bartimaeusnek.cropspp.crops.natura;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicFoodCrop;

import ic2.api.crops.ICropTile;

public class BarleyCrop extends BasicFoodCrop {

    public BarleyCrop() {
        super();
        OreDict.BSget("crop" + name().replaceAll(" ", ""), this);
    }

    @Override
    public String name() {
        return "Barley";
    }

    @Override
    public int tier() {
        return 2;
    }

    @Override
    public String[] attributes() {
        return new String[] { "Green", "Food", "Wheat" };
    }

    @Override
    public int maxSize() {
        return 4;
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        return crop.getSize() < 4 && crop.getLightLevel() >= 9;
    }

    @Override
    public boolean canBeHarvested(ICropTile crop) {
        return crop.getSize() == 4;
    }

    @Override
    public ItemStack getDisplayItem() {
        return OreDict.ISget("crop" + this.name());
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return CCropUtility.getCopiedOreStack("crop" + this.name());
    }
}
