package com.github.bartimaeusnek.cropspp.crops.natura;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicBerryCrop;

import ic2.api.crops.ICropTile;
import mods.natura.common.NContent;

public class BlackberryCrop extends BasicBerryCrop {

    public BlackberryCrop() {
        super();
        OreDict.BSget("crop" + this.name(), this);
    }

    public String name() {
        return "Blackberry";
    }

    public String[] attributes() {
        return new String[] { "Berry", "Food", "Black" }; // purple like CropVenomilia
    }

    public String discoveredBy() {
        return "Ancient cultures";
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        if (Mods.Natura.isModLoaded()) return new ItemStack(NContent.berryItem, 3, 2);
        else return CCropUtility.getCopiedOreStack("crop" + this.name());
    }

    @Override
    public ItemStack getDisplayItem() {
        if (Mods.Natura.isModLoaded()) return new ItemStack(NContent.berryItem, 3, 2);
        else return OreDict.ISget("crop" + this.name());
    }
}
