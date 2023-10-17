package com.github.bartimaeusnek.cropspp.crops.natura;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.ModsLoaded;
import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicBerryCrop;

import ic2.api.crops.ICropTile;
import mods.natura.common.NContent;

public class RaspberryCrop extends BasicBerryCrop {

    private static final String cropOreName = "cropRaspberry";

    public RaspberryCrop() {
        super();
        OreDict.BSget(cropOreName, this);
    }

    public String name() {
        return "Raspberry";
    }

    public String discoveredBy() {
        return "Ancient cultures";
    }

    public String[] attributes() {
        return new String[] { "Berry", "Food", "Red" }; // Red like CropRedWheat, CropNetherWart
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        if (ModsLoaded.Natura) return new ItemStack(NContent.berryItem, 3, 0);
        else return CCropUtility.getCopiedOreStack(cropOreName);
    }

    @Override
    public ItemStack getDisplayItem() {
        if (ModsLoaded.Natura) return new ItemStack(NContent.berryItem, 3, 0);
        else return OreDict.ISget(cropOreName);
    }
}
