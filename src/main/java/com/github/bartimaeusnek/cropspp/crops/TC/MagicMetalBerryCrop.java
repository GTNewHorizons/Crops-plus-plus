package com.github.bartimaeusnek.cropspp.crops.TC;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicTinkerBerryCrop;

import ic2.api.crops.ICropTile;

public class MagicMetalBerryCrop extends BasicTinkerBerryCrop {

    public MagicMetalBerryCrop() {
        super();
    }

    @Override
    public int tier() {
        return 7;
    }

    @Override
    public String name() {
        return "Magic Metal Berry";
    }

    @Override
    public String[] attributes() {
        return new String[] { "OreBerry", "Magic", "Metal", "Thaumium", "Void" };
    }

    @Override
    protected String hasBlock() {
        return "";
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        boolean r;
        if (crop.getSize() >= this.maxSize()) return false;
        if (ConfigValues.debug) return true;
        if (crop.getSize() >= this.maxSize() - 1)
            return crop.isBlockBelow("blockThaumium") || crop.isBlockBelow("blockThauminite")
                    || crop.isBlockBelow("blockIron")
                    || crop.isBlockBelow("blockVoid");
        // final growth stage doesn't care about light level, it was like that before, I'm just keeping it as is
        else return crop.getLightLevel() <= 10;

    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;

        // This used to check for all types of block but i removed that in favor of only thaumium check since thaumium
        // is the only one that is supposed to make it grow faster, and it requires a block to grow to maturity.

        // Doing this also technically introduces an exploit where you can make a crop grow faster if you swap the block
        // at the end. The effort to do this at a large scale is very stupid, it's at least note-worthy.

        // A solution to this would be to introduce a tick function like how netherwart and terrawart do it
        else if (crop.getSize() >= this.maxSize() - 1) return crop.isBlockBelow("blockThaumium") ? 1800 : 3300;
        else if (crop.getSize() >= this.maxSize() - 2) return 1200;
        return 500;
    }

    @Override
    public boolean canBeHarvested(ICropTile crop) {
        return crop.getSize() >= this.maxSize();
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        // note: you can change the block after it's fully grown to set the harvest type, it can be abused but the
        // effort required at a large scale isn't very worth it in terms of lag.
        if (crop.isBlockBelow("blockThaumium") || crop.isBlockBelow("blockIron"))
            return CCropUtility.getCopiedOreStack("nuggetThaumium");
        else if (crop.isBlockBelow("blockVoid")) return CCropUtility.getCopiedOreStack("nuggetVoid");
        else if (crop.isBlockBelow("blockThauminite")) return CCropUtility.getCopiedOreStack("nuggetThauminite");
        else return null;
    }

    @Override
    public List<String> getCropInformation() {
        return (List<String>) Arrays.asList(
                new String[] { "Needs a block of Thaumium, Iron, Thauminite or Void Below to fully mature.",
                        "Drops the Magic-Metal that is underneath (Iron will drop Thaumium)",
                        "Matures fastest with Thaumium under it.",
                        "Needs a light level below or equal to 10 to fully mature." });
    }

    @Override
    public ItemStack getDisplayItem() {
        return thaumcraft.api.ItemApi.getItem("itemResource", 17);
    }
}
