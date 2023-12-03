package com.github.bartimaeusnek.cropspp.crops.TC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import com.github.bartimaeusnek.croploadcore.BlockGetterTC;
import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicThaumcraftCrop;

import ic2.api.crops.Crops;
import ic2.api.crops.ICropTile;
import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.ItemManaBean;

public class BasicManaBeanCrop extends BasicThaumcraftCrop {

    private static ItemStack manaBean = null;
    private static ArrayList<Aspect> aspects = null;
    private static Block blockCrystal = null;

    public BasicManaBeanCrop() {
        super();
        // it used to use a method that actually do nothing, at least this works
        Crops.instance.registerBaseSeed(thaumcraft.api.ItemApi.getItem("itemManaBean", 0), this, 1, 1, 1, 1);
    }

    @Override
    public String name() {
        return "Mana Bean";
    }

    @Override
    public String discoveredBy() {
        return "kuba6000";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Berry", "Bean", "Magic", "Colorful" };
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
    public int weightInfluences(ICropTile crop, float humidity, float nutrients, float air) {
        return (int) ((double) humidity / 1.3D + (double) nutrients + (double) air / 0.7);
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        // crystal cluster doesn't exist? no growing
        if (crop.getSize() >= maxSize()) return false;
        else if (blockCrystal == null && (blockCrystal = BlockGetterTC.getBlock_asBlock("blockCrystal", 0)) == null)
            return false;
        else if (crop.getSize() > 1) return crop.isBlockBelow(blockCrystal);
        return true;
    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        return crop.getSize() >= this.maxSize() - 1 ? 1200 : 800;
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        ItemStack bean = this.getDisplayItem().copy();
        if (blockCrystal == null && (blockCrystal = BlockGetterTC.getBlock_asBlock("blockCrystal", 0)) == null)
            return null;
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
    public List<String> getCropInformation() {
        return Arrays.asList(
                "Needs a Crystal Cluster below to fully mature.",
                "Has increased humidity requirements (x1.3)",
                "Has decreased air requirements (x0.7)");
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

}
