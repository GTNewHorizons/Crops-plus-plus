package com.github.bartimaeusnek.cropspp.crops.natura;

import static gregtech.api.enums.Mods.Natura;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicBerryCrop;

import ic2.api.crops.ICropTile;
import mods.natura.common.NContent;

public class MaloberryCrop extends BasicBerryCrop {

    private static final String cropOreName = "cropMaloberry";

    public MaloberryCrop() {
        super();
        OreDict.BSget(cropOreName, this);
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
        if (Natura.isModLoaded()) return new ItemStack(NContent.berryItem, 3, 3);
        else return CCropUtility.getCopiedOreStack(cropOreName);
    }

    @Override
    public ItemStack getDisplayItem() {
        if (Natura.isModLoaded()) return new ItemStack(NContent.berryItem, 3, 3);
        else return OreDict.ISget(cropOreName);
    }
}
