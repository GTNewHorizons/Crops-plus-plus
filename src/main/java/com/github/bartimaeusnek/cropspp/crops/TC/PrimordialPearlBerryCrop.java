package com.github.bartimaeusnek.cropspp.crops.TC;

import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicCrop;

import ic2.api.crops.ICropTile;

public class PrimordialPearlBerryCrop extends BasicCrop {

    public PrimordialPearlBerryCrop() {
        super();
    }

    @Override
    public int tier() {
        return 16;
    }

    @Override
    public String name() {
        return "Primordial Berry";
    }

    @Override
    public String discoveredBy() {
        return "bartimaeusnek and ForTheHorde01";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Berry", "Primordial", "Magic", "Unique" };
    }

    @Override
    public boolean canCross(ICropTile crop) {
        return false;
    }

    @Override
    public int stat(int n) {
        return switch (n) {
            case 0 -> 0; // not chemical
            case 1 -> 0; // not edible
            case 2 -> 0; // no defensive properties
            case 3 -> 12; // immensely colorful
            case 4 -> 0; // not weed-like
            default -> 0;
        };
    }

    @Override
    public int weightInfluences(ICropTile crop, float humidity, float nutrients, float air) {
        // Requires Tons of everything
        // used to be able to grow with 2 humidity, 2 nutrients, and 2 air quality pretty well
        // now this crop will be a proper pain in the ass to grow (about 4x slower)
        // it can even die off if you don't handle it carefully
        return (int) ((double) humidity / 2 + (double) nutrients / 2 + (double) air / 2);
    }

    @Override
    public int maxSize() {
        return 4;
    }

    @Override
    public int growthDuration(ICropTile crop) {
        return ConfigValues.debug ? 1 : ConfigValues.PrimordialBerryGroth;
    }

    @Override
    public float dropGainChance() {
        return ConfigValues.PrimordialBerryGain;
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        // this actually has a chance of giving us a pre-existing item stack that may have a non-1 stackCount
        ItemStack is = thaumcraft.api.ItemApi.getItem("itemEldritchObject", 3).copy();
        is.stackSize = 1;
        return is;
    }

    @Override
    public ItemStack getSeeds(ICropTile crop) {
        return crop.generateSeeds(crop.getCrop(), (byte) 1, (byte) 1, (byte) 1, crop.getScanLevel());
    }

    @Override
    public List<String> getCropInformation() {
        return Collections.singletonList("Can not Cross, takes a long time to mature.");
    }

    @Override
    public ItemStack getDisplayItem() {
        return thaumcraft.api.ItemApi.getItem("itemEldritchObject", 3);
    }

}
