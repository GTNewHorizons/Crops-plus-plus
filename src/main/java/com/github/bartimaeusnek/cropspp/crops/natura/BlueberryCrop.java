package com.github.bartimaeusnek.cropspp.crops.natura;

import static gregtech.api.enums.Mods.Natura;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicBerryCrop;

import ic2.api.crops.ICropTile;
import mods.natura.common.NContent;

public class BlueberryCrop extends BasicBerryCrop {

    public BlueberryCrop() {
        super();
        OreDict.BSget("crop" + this.name(), this);
    }

    public String name() {
        return "Blueberry";
    }

    public String[] attributes() {
        return new String[] { "Berry", "Food", "Blue" }; // Blue like CropTerraWart
    }

    public String discoveredBy() {
        return "Ancient cultures";
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        if (Natura.isModLoaded()) {
            return new ItemStack(NContent.berryItem, 3, 1);
        }
        return CCropUtility.getCopiedOreStack("crop" + this.name());
    }

    @Override
    public ItemStack getDisplayItem() {
        if (Natura.isModLoaded()) {
            return new ItemStack(NContent.berryItem, 3, 1);
        }
        return OreDict.ISget("crop" + this.name());
    }
}
