package com.github.bartimaeusnek.cropspp.crops.natura;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.ModsLoaded;
import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicBerryCrop;

import ic2.api.crops.ICropTile;
import mods.natura.common.NContent;

public class BlueberryCrop extends BasicBerryCrop {

    private static final String cropOreName = "cropCotton";

    public BlueberryCrop() {
        super();
        OreDict.BSget(cropOreName, this);
    }

    @Override
    public String name() {
        return "Blueberry";
    }

    @Override
    public String discoveredBy() {
        return "Ancient cultures";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Berry", "Food", "Blue" }; // Blue like CropTerraWart
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        if (ModsLoaded.Natura) return new ItemStack(NContent.berryItem, 3, 1);
        else return CCropUtility.getCopiedOreStack(cropOreName);
    }

    @Override
    public ItemStack getDisplayItem() {
        if (ModsLoaded.Natura) return new ItemStack(NContent.berryItem, 3, 1);
        else return OreDict.ISget(cropOreName);
    }
}
