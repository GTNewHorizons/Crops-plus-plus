package com.github.bartimaeusnek.cropspp.crops.natura;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.ModsLoaded;
import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicDecorationCrop;

import ic2.api.crops.ICropTile;
import mods.natura.common.NContent;

public class CottonCrop extends BasicDecorationCrop {

    private static final String cropOreName = "cropCotton";

    public CottonCrop() {
        super();
        OreDict.BSget(cropOreName, this);
    }

    @Override
    public int tier() {
        return 3;
    }

    @Override
    public String name() {
        return "Cotton";
    }

    @Override
    public String[] attributes() {
        return new String[] { "White", "Cotton" };
    }

    @Override
    public int stat(int n) {
        switch (n) {
            case 0: {
                return 4;
            }
            case 1: {
                return 4;
            }
            case 2: {
                return 0;
            }
            case 3: {
                return 0;
            }
            case 4: {
                return 2;
            }
        }
        return 0;
    }

    @Override
    public int maxSize() {
        return 5;
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        return super.canGrow(crop) && crop.getLightLevel() >= 9;
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        if (!ModsLoaded.Natura) return CCropUtility.getCopiedOreStack(cropOreName);
        else return new ItemStack(NContent.plantItem, 1, 3);
    }

    @Override
    public ItemStack getDisplayItem() {
        return OreDict.ISget(cropOreName);
    }
}
