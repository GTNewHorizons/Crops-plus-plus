package com.github.bartimaeusnek.cropspp.crops.cpp;

import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicCrop;
import com.github.bartimaeusnek.cropspp.items.CppItems;

import ic2.api.crops.ICropTile;
import micdoodle8.mods.galacticraft.core.blocks.GCBlocks;

public class SpacePlantCrop extends BasicCrop {

    public SpacePlantCrop() {
        super();
    }

    @Override
    public int tier() {
        return 13;
    }

    @Override
    public String name() {
        return "Space Plant";
    }

    @Override
    public int stat(int n) {
        return switch (n) {
            case 0 -> 8; // chemical
            case 1 -> 0; // edible
            case 2 -> 0; // defensive properties
            case 3 -> 4; // colorful
            case 4 -> 0; // weed-like
            default -> 0;
        };
    }

    @Override
    public String[] attributes() {
        return new String[] { "Alien", "Space", "Radiation", "Transform" };
    }

    @Override
    public int maxSize() {
        return 4;
    }

    @Override
    public int growthDuration(ICropTile crop) {
        return ConfigValues.debug ? 1 : 5000;
    }

    @Override
    public boolean canBeHarvested(ICropTile crop) {
        return crop.getSize() >= this.maxSize();
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        if (crop.getSize() >= this.maxSize()) return false;
        else if (ConfigValues.debug) return true;
        // This also includes the GC ores from the moon
        // in nh we have an ore dict for "rockMoon", we could use that instead but,
        // that ore dict name doesn't exist by default, let's net do that
        else if (crop.getSize() >= this.maxSize() - 1) {
            if (ConfigValues.isGalacticCraftLoaded) {
                return crop.isBlockBelow(GCBlocks.blockMoon);
            } else {
                return true;
            }
        } else return true;
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return new ItemStack(CppItems.Modifier, 1, 0);
    }

    @Override
    public List<String> getCropInformation() {
        return Collections.singletonList("Needs a block from the moon below to fully mature.");
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(CppItems.Modifier, 1, 0);
    }
}
