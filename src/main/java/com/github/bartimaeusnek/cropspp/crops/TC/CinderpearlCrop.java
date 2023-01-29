package com.github.bartimaeusnek.cropspp.crops.TC;

import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicThaumcraftCrop;
import ic2.api.crops.ICropTile;

public class CinderpearlCrop extends BasicThaumcraftCrop {

    public CinderpearlCrop() {
        super();
        OreDict.BSget("crop" + this.name(), this);
    }

    @Override
    public String name() {
        return "Cinderpearl";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Magic", "Blaze", "Nether" };
    }

    @Override
    public String discoveredBy() {
        return "bartimaeusnek and mitchej123";
    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        return crop.getSize() == 1 ? 2250 : 1750;
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        if (ConfigValues.debug) return crop.getSize() < 3;
        if (crop.getSize() <= 1) return crop.getSize() <= 1;
        if (crop.getSize() == 2) return (crop.getSize() == 2
                && (crop.isBlockBelow("blockBlaze") || !(OreDictionary.doesOreNameExist("blockBlaze"))));
        return false;
    }

    @Override
    public ItemStack getDisplayItem() {
        return OreDict.ISget("crop" + this.name());
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return CCropUtility.getCopiedOreStack("crop" + this.name());
    }

    @Override
    public List<String> getCropInformation() {
        return Collections.singletonList("Needs a block of Blaze below to fully mature.");
    }
}
