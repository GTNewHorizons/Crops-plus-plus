package com.github.bartimaeusnek.cropspp.crops.TC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.ItemManaBean;

import com.github.bartimaeusnek.croploadcore.BlockGetterTC;
import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicThaumcraftCrop;
import ic2.api.crops.ICropTile;

public class BasicManaBeanCrop extends BasicThaumcraftCrop {

    private static ItemStack manaBean = null;
    private static ArrayList<Aspect> aspects = null;
    private static Block blockCrystal = null;

    public BasicManaBeanCrop() {
        super();
        OreDict.BSget("crop" + this.name(), this);
    }

    @Override
    public int stat(int n) {
        switch (n) {
            case 0:
                return 0; // a bit chemical
            case 1:
                return 4; // edible
            case 2:
                return 0; // strong defensive properties
            case 3:
                return 4; // quite colorful
            case 4:
                return 0; // not particularly weed-like
            default:
                return 0;
        }
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        if (blockCrystal == null) blockCrystal = BlockGetterTC.getBlock_asBlock("blockCrystal", 0);
        if (crop.getSize() == maxSize()) return false;
        if (crop.getSize() > 1) {
            return crop.isBlockBelow(blockCrystal);
        } else return true;
    }

    @Override
    public int weightInfluences(ICropTile crop, float humidity, float nutrients, float air) {
        // Requires no humidity but nutrients.
        return (int) ((double) humidity * 1.3 + (double) nutrients * 1 + (double) air * 0.7);
    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        return crop.getSize() == 2 ? 1200 : 800;
    }

    @Override
    public String[] attributes() {
        return new String[] { "Berry", "Bean", "Magic", "Colorful" };
    }

    @Override
    public String name() {
        return "Mana Bean";
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        ItemStack bean = this.getDisplayItem().copy();
        if (blockCrystal == null) blockCrystal = BlockGetterTC.getBlock_asBlock("blockCrystal", 0);
        World w = crop.getWorld();
        ChunkCoordinates location = crop.getLocation();
        int option = 0;
        for (int i = 1; i < getrootslength(crop); i++) {
            if (w.isAirBlock(location.posX, location.posY - i, location.posZ)) break;
            Block b = w.getBlock(location.posX, location.posY - i, location.posZ);
            if (b == blockCrystal) {
                option = w.getBlockMetadata(location.posX, location.posY - i, location.posZ);
                break;
            }
        }
        ((ItemManaBean) bean.getItem()).setAspects(
                bean,
                (new AspectList()).add(aspects.get(option > 5 ? w.rand.nextInt(aspects.size()) : option), 1));
        return bean;
    }

    @Override
    public ItemStack getDisplayItem() {
        if (manaBean == null) {
            aspects = new ArrayList<>(5);
            aspects.add(Aspect.AIR);
            aspects.add(Aspect.FIRE);
            aspects.add(Aspect.WATER);
            aspects.add(Aspect.EARTH);
            aspects.add(Aspect.ORDER);
            aspects.add(Aspect.ENTROPY);
            manaBean = ItemApi.getItem("itemManaBean", 0);
        }
        return manaBean;
    }

    @Override
    public String discoveredBy() {
        return "kuba6000";
    }

    @Override
    public List<String> getCropInformation() {
        return Collections.singletonList("Needs a Crystal Cluster below to fully mature.");
    }
}
