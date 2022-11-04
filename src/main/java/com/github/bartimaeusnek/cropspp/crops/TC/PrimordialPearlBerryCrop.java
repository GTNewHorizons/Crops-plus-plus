package com.github.bartimaeusnek.cropspp.crops.TC;

import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicCrop;
import ic2.api.crops.ICropTile;
import java.util.Collections;
import java.util.List;
import net.minecraft.item.ItemStack;

public class PrimordialPearlBerryCrop extends BasicCrop {

    public PrimordialPearlBerryCrop() {
        super();
    }

    @Override
    public String name() {
        return "Primordial Berry";
    }

    @Override
    public int tier() {
        return 16;
    }

    @Override
    public int stat(int n) {
        switch (n) {
            case 0:
                return 0; // not chemical
            case 1:
                return 0; // not edible
            case 2:
                return 0; // no defensive properties
            case 3:
                return 12; // immensely colorful
            case 4:
                return 0; // not weed-like
            default:
                return 0;
        }
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        return crop.getSize() < 4;
    }

    @Override
    public boolean canBeHarvested(ICropTile crop) {
        return crop.getSize() == 4;
    }

    @Override
    public int weightInfluences(ICropTile crop, float humidity, float nutrients, float air) {
        // Requires Tons of everything
        return (int) ((double) humidity * 2 + (double) nutrients * 2 + (double) air * 2);
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
    public byte getSizeAfterHarvest(ICropTile crop) {
        return 1;
    }

    @Override
    public int maxSize() {
        return 4;
    }

    @Override
    public boolean canCross(ICropTile crop) {
        return false;
    }

    @Override
    public ItemStack getSeeds(ICropTile crop) {
        return crop.generateSeeds(crop.getCrop(), (byte) 1, (byte) 1, (byte) 1, crop.getScanLevel());
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return thaumcraft.api.ItemApi.getItem("itemEldritchObject", 3);
    }

    @Override
    public String[] attributes() {
        return new String[] {"Berry", "Primordial", "Magic", "Unique"};
    }

    @Override
    public String discoveredBy() {
        return "bartimaeusnek and ForTheHorde01";
    }

    @Override
    public ItemStack getDisplayItem() {
        return thaumcraft.api.ItemApi.getItem("itemEldritchObject", 3);
    }

    @Override
    public List<String> getCropInformation() {
        return Collections.singletonList("Can not Cross, takes a long time to mature.");
    }
}
