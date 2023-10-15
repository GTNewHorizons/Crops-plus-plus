package com.github.bartimaeusnek.cropspp.abstracts;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.ConfigValues;

import ic2.api.crops.ICropTile;

public abstract class BasicTinkerBerryCrop extends BasicCrop {

    public BasicTinkerBerryCrop() {
        super();
    }

    public static String OBname() {
        return "Oreberry";
    }

    @Override
    public int tier() {
        return 5;
    }

    @Override
    public float dropGainChance() {
        return (float) ((Math.pow(0.95, (float) tier())) * ConfigValues.TConstructBerryGain);
    }

    protected abstract String hasBlock();

    @Override
    public int stat(int n) {
        switch (n) {
            case 0:
                return 3; // Industrial Crop
            case 1:
                return 0; // NOT Edible
            case 2:
                return 4; // strong defensive properties
            case 3:
                return 1; // a bit colorful
            case 4:
                return 0; // not particularly weed-like
            default:
                return 0;
        }
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        if (crop.getSize() >= this.maxSize()) return false;
        else if (ConfigValues.debug || crop.getSize() < 1) return true;
        else if (crop.getSize() < this.maxSize() - 1) return crop.getLightLevel() <= 10;
        else if (crop.getSize() == this.maxSize() - 1) return crop.isBlockBelow(hasBlock());
        return false;
    }

    @Override
    public boolean canBeHarvested(ICropTile crop) {
        return crop.getSize() >= 3;
    }

    @Override
    public int weightInfluences(ICropTile crop, float humidity, float nutrients, float air) {
        // Requires no humidity but nutrients.
        return (int) ((double) humidity / 0.5 + (double) nutrients / 1.5 + (double) air);
    }

    @Override
    public String discoveredBy() {
        return "bartimaeusnek";
    }

    @Override
    public int maxSize() {
        return 4;
    }

    @Override
    public byte getSizeAfterHarvest(ICropTile crop) {
        // return to partially grown state when harvested
        return 2;
    }

    @Override
    public List<String> getCropInformation() {
        List<String> ret = new ArrayList<>();
        List<ItemStack> blocks = OreDictionary.getOres(hasBlock());
        if (blocks.size() > 0) {
            ret.add("Needs a block of " + blocks.get(0).getDisplayName() + " Below to fully mature.");
        }
        ret.add("Needs a light level below or equal to 10 to fully mature.");
        ret.add("Has increased Nutrient requirements (x1.5) and decreased humidity requirements (x0.5)");
        ret.add("Hurt Player on collision");
        return ret;
    }

    @Override
    public boolean onEntityCollision(ICropTile crop, Entity entity) {
        CCropUtility.damageEntity(entity, 1);
        return super.onEntityCollision(crop, entity);
    }
}
