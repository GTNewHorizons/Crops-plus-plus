package com.github.bartimaeusnek.cropspp.crops.BoP;

import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.content.BOPCItems;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicCrop;
import ic2.api.crops.ICropTile;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;

import java.util.Arrays;
import java.util.List;

public class Bamboo extends BasicCrop {



    public String name() {
        return "Bamboo";
    }


    public String discoveredBy() {
        return "Minepolz320";
    }

    public int tier() {
        return 2;
    }



    @Override
    public int weightInfluences(ICropTile crop, float humidity, float nutrients, float air) {
        return (int) ((double) humidity * 1.3D + (double) nutrients * 1.2D + (double) air * 0.8D);
    }




    public int stat(int n) {
        switch(n) {
            case 0:
                return 0;// not chemical
            case 1:
                return 0; // not edible
            case 2:
                return 3; // has defensive properties
            case 3:
                return 0; // not colorful
            case 4:
                return 3; //  weed-like
            default:
                return 0;
        }
    }



    public String[] attributes() {
        return new String[]{"Green" ,"Pointed" , "Edgy" };
    }

    public int maxSize() {
        return 3;
    }

    public boolean canGrow(ICropTile crop) {
        return crop.getSize() < 3;
    }



    public boolean canBeHarvested(ICropTile crop) {
        return crop.getSize() > 1;
    }

    public int getOptimalHavestSize(ICropTile crop) {
        return 3;
    }

    public ItemStack getGain(ICropTile crop) {
        return new ItemStack(BOPCBlocks.bamboo, crop.getSize() - 1);
    }


    public int growthDuration(ICropTile crop) {

        //If raining = fast grow
        if(CCropUtility.isRainingOn(crop)){
            return  100;
        }
        return 150;
    }

    public boolean onEntityCollision(ICropTile crop, Entity entity) {
        if(!entity.isSneaking()){
            CCropUtility.damageEntity(entity ,1);
        }
        return false;
    }

}
