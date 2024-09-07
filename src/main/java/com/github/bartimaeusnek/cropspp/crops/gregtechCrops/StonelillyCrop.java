package com.github.bartimaeusnek.cropspp.crops.gregtechCrops;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicDecorationCrop;

import gregtech.api.enums.Materials;
import ic2.api.crops.ICropTile;

public class StonelillyCrop extends BasicDecorationCrop {

    private final String color;

    public StonelillyCrop(String color) {
        super();
        this.color = color;
    }

    @Override
    public ItemStack getDisplayItem() {
        return switch (color) {
            case "Red" -> Materials.GraniteRed.getDust(9);
            case "Black" -> Materials.GraniteBlack.getDust(9);
            case "White" -> Materials.Marble.getDust(9);
            case "Gray" -> Materials.Stone.getDust(9);
            case "Yellow" -> Materials.Endstone.getDust(2);
            case "Nether" -> Materials.Netherrack.getDust(9);
            default -> new ItemStack(Blocks.cobblestone);
        };
    }

    @Override
    public String name() {
        return color + " Stonelilly";
    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        // this used to check if the crop was on final stage with end stone
        // I added a check to only do this check when it's yellow stone lily
        // since it's the only one that can even use it to grow.
        if (this.color.equals("Yellow") && crop.getSize() >= this.maxSize() - 1 && crop.isBlockBelow(Blocks.end_stone))
            return 550;
        return 300;
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        if (!super.canGrow(crop)) return false;
        // debug Override
        if (ConfigValues.debug) return true;
        // if crop is not on last stage, it can grow anyway
        if (crop.getSize() < this.maxSize() - 1) return true;
        // if crop is on last stage, it needs the block
        return switch (color) {
            case "Red" -> crop.isBlockBelow("stoneGraniteRed") || crop.isBlockBelow("blockGranite");
            case "Black" -> crop.isBlockBelow("stoneGraniteBlack") || crop.isBlockBelow("stoneBasalt");
            case "White" -> crop.isBlockBelow("blockMarble") || crop.isBlockBelow("blockDiorite");
            case "Gray" -> crop.isBlockBelow(Blocks.cobblestone) || crop.isBlockBelow(Blocks.stone)
                    || crop.isBlockBelow("blockAndesite");
            case "Yellow" -> crop.isBlockBelow(Blocks.end_stone) || crop.isBlockBelow(Blocks.sand)
                    || crop.isBlockBelow(Blocks.sandstone);
            case "Nether" -> crop.isBlockBelow(Blocks.netherrack) || crop.isBlockBelow(Blocks.nether_brick);
            // if this line executes consider it a UB
            default -> false;
        };
    }

    @Override
    public int weightInfluences(ICropTile crop, float humidity, float nutrients, float air) {
        return (int) ((double) humidity / 0.8D + (double) nutrients / 1.4D + (double) air / 0.8D);
    }

    @Override
    public String[] attributes() {
        return switch (color) {
            case "Red" -> new String[] { color, "Stone", "Fire" };
            case "Black" -> new String[] { color, "Stone", "Dark" };
            case "White" -> new String[] { color, "Stone", "Shiny" };
            case "Gray" -> new String[] { color, "Stone", "Metal" };
            case "Yellow" -> new String[] { color, "Stone", "Alien" };
            case "Nether" -> new String[] { color, "Stone", "Evil" };
            // if this line executes consider it a UB
            default -> new String[] { color, "Stone" };
        };
    }

    @Override
    public List<String> getCropInformation() {
        ArrayList<String> information = new ArrayList<>();
        switch (color) {
            case "Red" -> information.add("Needs a Block of Red Granite or Granite(Non-GT) below to fully Mature");
            case "Black" -> information.add("Needs a Block of Black Granite or Basalt below to fully Mature");
            case "White" -> information.add("Needs a Block of Marble or Diorite below to fully Mature");
            case "Gray" -> information.add("Needs a Block of Cobblestone, Stone or Andesite below to fully Mature");
            case "Yellow" -> information.add("Needs a Block of Endstone, Sand or Sandstone below to fully Mature");
            case "Nether" -> information.add("Needs a Block of Netherrack or Netherbrick below to fully Mature");
        }
        information.add("Has increased Nutrient requirements (x1.4)");
        information.add("Has decreased humidity and air requirements (x0.8)");
        return information;
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        switch (color) {
            case "Red" -> {
                if (crop.isBlockBelow("stoneGraniteRed")) return Materials.GraniteRed.getDust(9);
                if (crop.isBlockBelow("blockGranite")) return CCropUtility.getCopiedOreStack("blockGranite");
            }
            case "Black" -> {
                if (crop.isBlockBelow("stoneGraniteBlack")) return Materials.GraniteBlack.getDust(9);
                if (crop.isBlockBelow("stoneBasalt")) return Materials.Basalt.getDust(9);
            }
            case "White" -> {
                if (crop.isBlockBelow("blockMarble")) return Materials.Marble.getDust(9);
                if (crop.isBlockBelow("blockDiorite")) return CCropUtility.getCopiedOreStack("blockDiorite");
            }
            case "Gray" -> {
                if (crop.isBlockBelow(Blocks.cobblestone) || crop.isBlockBelow(Blocks.stone))
                    return Materials.Stone.getDust(9);
                if (crop.isBlockBelow("blockAndesite")) return CCropUtility.getCopiedOreStack("blockAndesite");
            }
            case "Yellow" -> {
                if (crop.isBlockBelow(Blocks.end_stone)) return Materials.Endstone.getDust(2);
                if ((crop.isBlockBelow(Blocks.sand)) || (crop.isBlockBelow(Blocks.sandstone)))
                    return new ItemStack(Blocks.sand, 4);
            }
            case "Nether" -> {
                if (crop.isBlockBelow(Blocks.netherrack) || crop.isBlockBelow(Blocks.nether_brick))
                    return Materials.Netherrack.getDust(9);
            }
        }
        // this is user error if this executes, not a UB
        return null;
    }
}
