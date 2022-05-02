package com.github.bartimaeusnek.cropspp.abstracts;

import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.ConfigValues;
import ic2.api.crops.ICropTile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.DamageSource;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.List;

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
                return 3;   // Industrial Crop
            case 1:
                return 0;   // NOT Edible
            case 2:
                return 4;   // strong defensive properties
            case 3:
                return 1;   // a bit colorful
            case 4:
                return 0;   // not particularly weed-like
            default:
                return 0;
        }
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        boolean r;
        if (ConfigValues.debug)
            r = crop.getSize() < 4;
        else
            r = crop.getSize() < 1 || crop.getSize() == 3 && crop.isBlockBelow(hasBlock()) || (crop.getLightLevel() <= 10 && crop.getSize() < 3); //Codepiece by DaeFennek <3
        return r;
    }

    @Override
    public boolean canBeHarvested(ICropTile crop) {
        return crop.getSize() >= 3;
    }

    @Override
    public int weightInfluences(ICropTile crop, float humidity, float nutrients, float air) {
        // Requires no humidity but nutrients.
        return (int) ((double) humidity * 0.5 + (double) nutrients * 1.5 + (double) air * 1);
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
        return (List<String>) Arrays.asList(new String[]{"Needs a block of " + OreDictionary.getOres(hasBlock()).get(0).getDisplayName() + " Below to fully mature.", "Needs a light level below or equal to 10 to fully mature.", "Has increased Nutrient requirements (x1.5) and decreased humidity requirements (x0.5)", "Hurt Player on collision"});
    }

    @Override
    public boolean onEntityCollision(ICropTile crop, Entity entity) {
        CCropUtility.damageEntity(entity,1);
        return super.onEntityCollision(crop, entity);
    }

}
