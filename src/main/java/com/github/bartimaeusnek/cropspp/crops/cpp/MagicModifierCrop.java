package com.github.bartimaeusnek.cropspp.crops.cpp;

import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.crops.TC.PrimordialPearlBerryCrop;
import com.github.bartimaeusnek.cropspp.items.CppItems;

import ic2.api.crops.ICropTile;

public class MagicModifierCrop extends PrimordialPearlBerryCrop {

    public MagicModifierCrop() {
        super();
    }

    @Override
    public int tier() {
        return 13;
    }

    @Override
    public String name() {
        return "Magical Nightshade";
    }

    @Override
    public String discoveredBy() {
        return "bartimaeusnek";
    }

    @Override
    public int getEmittedLight(ICropTile crop) {
        if (crop.getSize() == this.maxSize()) return 4;
        else return 0;
    }

    @Override
    public byte getSizeAfterHarvest(ICropTile crop) {
        return 1;
    }

    @Override
    public boolean canCross(ICropTile crop) {
        return crop.getSize() == this.maxSize();
    }

    @Override
    public ItemStack getSeeds(ICropTile crop) {
        return crop.generateSeeds(
                crop.getCrop(),
                crop.getGrowth(),
                crop.getGain(),
                crop.getResistance(),
                crop.getScanLevel());
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        if (!this.canGrowBase(crop)) return false;
        else if (ConfigValues.debug) return true;
        else if (crop.getSize() >= this.maxSize() - 1)
            return crop.isBlockBelow("blockIchorium") || !OreDictionary.doesOreNameExist("blockIchorium");
        else return true;
    }

    @Override
    public int growthDuration(ICropTile crop) {
        return ConfigValues.debug ? 1 : ConfigValues.PrimordialBerryGroth / 16;
    }

    @Override
    public float dropGainChance() {
        return (float) ((float) ((Math.pow(0.95, (float) tier())) * ConfigValues.BerryGain)
                * (ConfigValues.PrimordialBerryGain * 1.5));
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return new ItemStack(CppItems.Modifier, 1, 1);
    }

    @Override
    public List<String> getCropInformation() {
        return Collections.singletonList("Needs a block of Ichorium below to fully mature.");
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(CppItems.Modifier, 1, 1);
    }
}
