package com.github.bartimaeusnek.cropspp.crops.natura;

import static gregtech.api.enums.Mods.Natura;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicBerryCrop;

import ic2.api.crops.ICropTile;
import mods.natura.common.NContent;

public class BlackberryCrop extends BasicBerryCrop {

    private static final String cropOreName = "cropBlackberry";

    public BlackberryCrop() {
        super();
        OreDict.BSget(cropOreName, this);
    }

    @Override
    public String name() {
        return "Blackberry";
    }

    @Override
    public String discoveredBy() {
        return "Ancient cultures";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Berry", "Food", "Black" }; // purple like CropVenomilia
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        if (Natura.isModLoaded()) return new ItemStack(NContent.berryItem, 3, 2);
        else return CCropUtility.getCopiedOreStack(cropOreName);
    }

    @Override
    public ItemStack getDisplayItem() {
        if (Natura.isModLoaded()) return new ItemStack(NContent.berryItem, 3, 2);
        else return OreDict.ISget(cropOreName);
    }
}
