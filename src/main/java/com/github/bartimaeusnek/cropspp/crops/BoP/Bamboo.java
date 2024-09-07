package com.github.bartimaeusnek.cropspp.crops.BoP;

import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicCrop;

import biomesoplenty.api.content.BOPCBlocks;
import ic2.api.crops.ICropTile;

public class Bamboo extends BasicCrop {

    @Override
    public int tier() {
        return 2;
    }

    @Override
    public String name() {
        return "Bamboo";
    }

    @Override
    public String discoveredBy() {
        return "Minepolz320";
    }

    @Override
    public int weightInfluences(ICropTile crop, float humidity, float nutrients, float air) {
        return (int) ((double) humidity / 1.3D + (double) nutrients / 1.2D + (double) air / 0.8D);
    }

    @Override
    public int stat(int n) {
        return switch (n) {
            case 0 -> 0; // not chemical
            case 1 -> 0; // not edible
            case 2 -> 3; // has defensive properties
            case 3 -> 0; // not colorful
            case 4 -> 3; // weed-like
            default -> 0;
        };
    }

    @Override
    public String[] attributes() {
        return new String[] { "Green", "Pointed", "Edgy" };
    }

    @Override
    public int maxSize() {
        return 3;
    }

    @Override
    public boolean canBeHarvested(ICropTile crop) {
        return crop.getSize() > 1;
    }

    @Override
    public int getOptimalHavestSize(ICropTile crop) {
        return this.maxSize();
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return crop.getSize() > 1 ? new ItemStack(BOPCBlocks.bamboo, crop.getSize() - 1) : null;
    }

    @Override
    public int growthDuration(ICropTile crop) {

        // If raining = fast grow
        if (CCropUtility.isRainingOn(crop)) {
            return 100;
        }
        return 150;
    }

    @Override
    public boolean onEntityCollision(ICropTile crop, Entity entity) {
        if (!entity.isSneaking()) {
            CCropUtility.damageEntity(entity, 1);
        }
        return false;
    }

    public List<String> getCropInformation() {
        return Arrays.asList(
                "Has increased humidity requirements (x1.3)",
                "Has increased nutrient requirements (x1.2)",
                "Has decreased humidity and air requirements (x0.8)");
    }
}
