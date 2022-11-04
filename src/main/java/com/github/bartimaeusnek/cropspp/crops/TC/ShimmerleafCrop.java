package com.github.bartimaeusnek.cropspp.crops.TC;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicThaumcraftCrop;
import ic2.api.crops.ICropTile;
import java.util.Collections;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ShimmerleafCrop extends BasicThaumcraftCrop {

    public ShimmerleafCrop() {
        super();
        OreDict.BSget("crop" + this.name(), this);
    }

    @Override
    public String name() {
        return "Shimmerleaf";
    }

    @Override
    public String[] attributes() {
        return new String[] {"Magic", "Silver", "Toxic"};
    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        return crop.getSize() == 1 ? 2250 : 1750;
    }

    @Override
    public String discoveredBy() {
        return "bartimaeusnek and DreamMasterXXL";
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        boolean r = false;
        if (ConfigValues.debug) r = crop.getSize() < 3;
        else if (crop.getSize() <= 1) r = crop.getSize() <= 1;
        else if (crop.getSize() == 2)
            r = (crop.getSize() == 2
                    && (crop.isBlockBelow("blockQuicksilver")
                            || !(OreDictionary.doesOreNameExist("blockQuicksilver"))));
        return r;
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
        return Collections.singletonList("Needs a block of Quicksilver below to fully mature.");
    }
}
