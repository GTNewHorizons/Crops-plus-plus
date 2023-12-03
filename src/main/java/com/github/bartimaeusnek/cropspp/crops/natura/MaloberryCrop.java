package com.github.bartimaeusnek.cropspp.crops.natura;

import static gregtech.api.enums.Mods.Natura;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicBerryCrop;

import ic2.api.crops.ICropTile;
import mods.natura.common.NContent;

public class MaloberryCrop extends BasicBerryCrop {

    public MaloberryCrop() {
        super();
        OreDict.BSget("crop" + this.name(), this);
        OreDict.BSget("cropGooseberry", this);
    }

    public String name() {
        return "Maloberry";
    }

    public String[] attributes() {
        return new String[] { "Berry", "Food", "Yellow" }; // orange like CropCarrots
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        if (Natura.isModLoaded()) {
            return new ItemStack(NContent.berryItem, 3, 3);
        }
        return CCropUtility.getCopiedOreStack("crop" + this.name());
    }

    @Override
    public ItemStack getDisplayItem() {
        if (Natura.isModLoaded()) {
            return new ItemStack(NContent.berryItem, 3, 3);
        }
        return OreDict.ISget("crop" + this.name());
    }
}
