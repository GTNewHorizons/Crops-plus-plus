package com.github.bartimaeusnek.cropspp.crops.witchery;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicWitcheryCrop;
import ic2.api.crops.ICropTile;
import net.minecraft.item.ItemStack;

public class MandragoraCrop extends BasicWitcheryCrop {

    public MandragoraCrop() {
        super();
        OreDict.BSget("seedMandrake", this);
    }

    @Override
    public String name() {
        return "Mandragora";
    }

    @Override
    public String[] attributes() {
        return new String[]{"Flower", "Magic", "Bad", "Toxic", "Ingredient"};
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return OreDict.ISget("itemMandrake");
    }

    @Override
    public float dropGainChance() {
        return (float) (((Math.pow(0.95, (float) tier())) * ConfigValues.BerryGain) * 0.5);
    }

    @Override
    public ItemStack getDisplayItem() {
        return OreDict.ISget("itemMandrake");
    }
}
