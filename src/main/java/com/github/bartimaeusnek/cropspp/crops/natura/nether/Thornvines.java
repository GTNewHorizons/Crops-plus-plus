package com.github.bartimaeusnek.cropspp.crops.natura.nether;

import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.crops.cpp.VineCrop;
import ic2.api.crops.ICropTile;
import mods.natura.common.NContent;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class Thornvines extends VineCrop {

    public Thornvines() {
        super();
    }

    @Override
    public int tier() {
        return 3;
    }

    @Override
    public String name() {
        return "Thornvines";
    }

    @Override
    public String discoveredBy() {
        return "bartimaeusnek";
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return new ItemStack(NContent.thornVines, 2);
    }

    @Override
    public String[] attributes() {
        return new String[] {"Nether", "Climbable", "Bad"};
    }

    @Override
    public boolean onEntityCollision(ICropTile crop, Entity entity) {
        CCropUtility.damageEntity(entity, 1);
        return super.onEntityCollision(crop, entity);
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(NContent.thornVines, 2);
    }
}
