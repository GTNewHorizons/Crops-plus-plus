package com.github.bartimaeusnek.cropspp.crops.cpp;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicTinkerBerryCrop;
import ic2.api.crops.ICropTile;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class CobaltBerryCrop extends BasicTinkerBerryCrop {

    public CobaltBerryCrop() {
        super();
    }

    @Override
    public String name() {
        return "Cobalt Berry";
    }

    @Override
    public int tier() {
        return 7;
    }

    @Override
    protected String hasBlock() {
        return "blockCobalt";
    }


    @Override
    public ItemStack getGain(ICropTile crop) {
        if ((crop.isBlockBelow("blockCobalt") || ConfigValues.debug) && OreDictionary.getOres("nuggetCobalt").size() != 0) {
            return OreDictionary.getOres("nuggetCobalt").get(OreDictionary.getOres("nuggetCobalt").size() - 1);
        } else
            return null;
    }


    @Override
    public int growthDuration(ICropTile crop) {
        int r;
        if (ConfigValues.debug)
            r = 1;
        else if (crop.getSize() == 2)
            r = 3000;
        else if ((crop.getSize() == 3 && crop.isBlockBelow("blockCobalt")))
            r = 3000;
        else
            r = 500;
        return r;
    }

    @Override
    public String[] attributes() {
        return new String[]{"OreBerry", "Cobalt", "Metal", "Blue"};
    }

    @Override
    public ItemStack getDisplayItem() {
        return OreDict.ISget("nuggetCobalt");
    }
}
