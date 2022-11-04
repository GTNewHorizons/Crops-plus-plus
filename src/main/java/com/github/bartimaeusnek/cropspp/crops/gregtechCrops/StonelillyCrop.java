package com.github.bartimaeusnek.cropspp.crops.gregtechCrops;

import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicDecorationCrop;
import gregtech.api.enums.Materials;
import ic2.api.crops.ICropTile;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class StonelillyCrop extends BasicDecorationCrop {
    private String color;

    public StonelillyCrop(String color) {
        super();
        this.color = color;
    }

    @Override
    public ItemStack getDisplayItem() {
        switch (color) {
            case "Red": {
                return Materials.GraniteRed.getDust(9);
            }
            case "Black": {
                return Materials.GraniteBlack.getDust(9);
            }
            case "White": {
                return Materials.Marble.getDust(9);
            }
            case "Gray": {
                return Materials.Stone.getDust(9);
            }
            case "Yellow": {
                return Materials.Endstone.getDust(2);
            }
            case "Nether": {
                return Materials.Netherrack.getDust(9);
            }
        }
        return new ItemStack(Blocks.cobblestone);
    }

    @Override
    public String name() {
        return color + " Stonelilly";
    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        return crop.getSize() == (this.maxSize() - 1) && crop.isBlockBelow(Blocks.end_stone) ? 550 : 300;
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        // debug Override
        if (ConfigValues.debug) return crop.getSize() < this.maxSize();

        boolean ret = false;
        switch (crop.getSize()) {
            case 0: {
                ret = true;
                break;
            }
            case 1: {
                ret = true;
                break;
            }
            case 2: {
                switch (color) {
                    case "Red": {
                        if (crop.isBlockBelow("stoneGraniteRed") || crop.isBlockBelow("blockGranite")) ret = true;
                        break;
                    }
                    case "Black": {
                        if (crop.isBlockBelow("stoneGraniteBlack") || crop.isBlockBelow("stoneBasalt")) ret = true;
                        break;
                    }
                    case "White": {
                        if (crop.isBlockBelow("blockMarble") || crop.isBlockBelow("blockDiorite")) ret = true;
                        break;
                    }
                    case "Gray": {
                        if (crop.isBlockBelow(Blocks.cobblestone)
                                || crop.isBlockBelow(Blocks.stone)
                                || crop.isBlockBelow("blockAndesite")) ret = true;
                        break;
                    }
                    case "Yellow": {
                        if (crop.isBlockBelow(Blocks.end_stone)
                                || crop.isBlockBelow(Blocks.sand)
                                || crop.isBlockBelow(Blocks.sandstone)) ret = true;
                        break;
                    }
                    case "Nether": {
                        if (crop.isBlockBelow(Blocks.netherrack) || crop.isBlockBelow(Blocks.nether_brick)) ret = true;
                        break;
                    }
                }
            }
        }
        return ret;
    }

    @Override
    public int weightInfluences(ICropTile crop, float humidity, float nutrients, float air) {
        return (int) ((double) humidity * 0.8 + (double) nutrients * 1.4 + (double) air * 0.8);
    }

    @Override
    public String[] attributes() {
        String[] ret = null;
        switch (color) {
            case "Red": {
                ret = new String[] {color, "Stone", "Fire"};
                break;
            }
            case "Black": {
                ret = new String[] {color, "Stone", "Dark"};
                break;
            }
            case "White": {
                ret = new String[] {color, "Stone", "Shiny"};
                break;
            }
            case "Gray": {
                ret = new String[] {color, "Stone", "Metal"};
                break;
            }
            case "Yellow": {
                ret = new String[] {color, "Stone", "Alien"};
                break;
            }
            case "Nether": {
                ret = new String[] {color, "Stone", "Evil"};
                break;
            }
        }
        return ret;
    }

    @Override
    public List<String> getCropInformation() {
        ArrayList<String> information = new ArrayList<>();
        switch (color) {
            case "Red": {
                information.add("Needs a Block of Red Granite or Granite(Non-GT) below to fully Mature");
                break;
            }
            case "Black": {
                information.add("Needs a Block of Black Granite or Basalt below to fully Mature");
                break;
            }
            case "White": {
                information.add("Needs a Block of Marble or Diorite below to fully Mature");
                break;
            }
            case "Gray": {
                information.add("Needs a Block of Cobblestone, Stone or Andesite below to fully Mature");
                break;
            }
            case "Yellow": {
                information.add("Needs a Block of Endstone, Sand or Sandstone below to fully Mature");
                break;
            }
            case "Nether": {
                information.add("Needs a Block of Netherrack or Netherbrick below to fully Mature");
                break;
            }
        }
        information.add("Has increased Nutrient requirements (x1.4)");
        information.add("Has decreased humidity and air requirements (x0.8)");
        return information;
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        ItemStack ret = null;
        switch (color) {
            case "Red": {
                if (crop.isBlockBelow("stoneGraniteRed")) ret = Materials.GraniteRed.getDust(9);
                if (crop.isBlockBelow("blockGranite")) ret = CCropUtility.getCopiedOreStack("blockGranite");
                break;
            }
            case "Black": {
                if (crop.isBlockBelow("stoneGraniteBlack")) ret = Materials.GraniteBlack.getDust(9);
                if (crop.isBlockBelow("stoneBasalt")) ret = Materials.Basalt.getDust(9);
                break;
            }
            case "White": {
                if (crop.isBlockBelow("blockMarble")) ret = Materials.Marble.getDust(9);
                if (crop.isBlockBelow("blockDiorite")) ret = CCropUtility.getCopiedOreStack("blockDiorite");
                break;
            }
            case "Gray": {
                if (crop.isBlockBelow(Blocks.cobblestone) || crop.isBlockBelow(Blocks.stone))
                    ret = Materials.Stone.getDust(9);
                if (crop.isBlockBelow("blockAndesite")) ret = CCropUtility.getCopiedOreStack("blockAndesite");
                break;
            }
            case "Yellow": {
                if (crop.isBlockBelow(Blocks.end_stone)) ret = Materials.Endstone.getDust(2);
                if ((crop.isBlockBelow(Blocks.sand)) || (crop.isBlockBelow(Blocks.sandstone)))
                    ret = new ItemStack(Blocks.sand, 4);
                break;
            }
            case "Nether": {
                if (crop.isBlockBelow(Blocks.netherrack)) ret = Materials.Netherrack.getDust(9);
                if (crop.isBlockBelow(Blocks.nether_brick)) ret = Materials.Netherrack.getDust(9);
                break;
            }
        }
        return ret;
    }
}
