package com.github.bartimaeusnek.cropspp.crops.cpp;

import static gregtech.api.enums.Mods.BiomesOPlenty;

import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicDecorationCrop;
import com.github.bartimaeusnek.cropspp.croploader.CropLoader;

import biomesoplenty.api.content.BOPCBlocks;
import ic2.api.crops.Crops;
import ic2.api.crops.ICropTile;

public class CactiCrop extends BasicDecorationCrop {

    public CactiCrop() {
        if (BiomesOPlenty.isModLoaded()) Crops.instance.registerBaseSeed(
                new ItemStack(BOPCBlocks.plants, 1, 12),
                com.github.bartimaeusnek.cropspp.croploader.CropLoader.CropunpackerCC(new CropLoader(this)),
                1,
                1,
                1,
                1);
    }

    @Override
    public int tier() {
        return 3;
    }

    @Override
    public String name() {
        return "Cactus";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Green", "Cactus" };
    }

    @Override
    public int weightInfluences(ICropTile crop, float humidity, float nutrients, float air) {
        // Requires more humidity than nutrients or air, but not much more
        return (int) ((double) humidity / 0.5 + (double) nutrients / 1.25D + (double) air / 1.25D);
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        if (BiomesOPlenty.isModLoaded() && crop.getSize() >= this.maxSize() - 1)
            return new ItemStack(BOPCBlocks.plants, 1, 12);
        else return new ItemStack(Item.getItemById(81), 1, 0);
    }

    @Override
    public boolean canBeHarvested(ICropTile crop) {
        return crop.getSize() >= this.maxSize() - 1;
    }

    @Override
    public boolean onEntityCollision(ICropTile crop, Entity entity) {
        if (!entity.isSneaking()) CCropUtility.damageEntity(entity, 1);
        return super.onEntityCollision(crop, entity);
    }

    @Override
    public List<String> getCropInformation() {
        return Arrays.asList(
                "Has increased air and nutrients requirements (x1.25)",
                "Has decreased humidity requirements (x0.5)",
                "Hurt Player on collision");
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(Item.getItemById(81), 1, 0);
    }
}
