package com.github.bartimaeusnek.cropspp.crops.gregtechCrops;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicCrop;

import gregtech.api.GregTechAPI;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.objects.ItemData;
import gregtech.api.objects.XSTR;
import gregtech.api.util.GTOreDictUnificator;
import gregtech.common.blocks.BlockOresAbstract;
import gregtech.common.blocks.TileEntityOres;
import ic2.api.crops.ICropTile;

public class GarnydniaCrop extends BasicCrop {

    public GarnydniaCrop() {
        super();
    }

    @Override
    public int tier() {
        return 7;
    }

    @Override
    public String name() {
        return "Garnydinia";
    }

    @Override
    public String discoveredBy() {
        return "moronwmachinegun";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Shiny", "Crystal", "Red", "Yellow", "Metal" };
    }

    @Override
    public int stat(int n) {
        return switch (n) {
            case 0 -> 4; // chemical
            case 1 -> 0; // not edible
            case 2 -> 2; // a bit defense
            case 3 -> 4; // quite colorful
            case 4 -> 0; // not particularly weed-like
            default -> 0;
        };
    }

    @Override
    public int weightInfluences(ICropTile crop, float humidity, float nutrients, float air) {
        // no documentation so i'm leaving it alone instead of inverting the effect
        return (int) Math.floor((double) humidity / 0.5D + (double) nutrients / 2.0D + (double) air / 0.5D);
    }

    @Override
    public int maxSize() {
        return 3;
    }

    @Override
    public byte getSizeAfterHarvest(ICropTile crop) {
        return 1;
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        if (!super.canGrow(crop)) return false;
        if (crop.getSize() >= this.maxSize() - 1) return isBlockBelow(crop);
        return true;
    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        // this used to have a stage 0 of 3300 ticks, but stage 0 is never a thing sadly.
        return crop.getSize() >= this.maxSize() - 1 ? 550 : 300;
    }

    /**
     * Taken from GT Directly, all credits for this method go to GregoriousT
     */
    public boolean isBlockBelow(ICropTile aCrop) {
        if (aCrop == null) {
            return false;
        }

        for (int i = 1; i < this.getrootslength(aCrop); ++i) {
            Block tBlock = aCrop.getWorld()
                    .getBlock(aCrop.getLocation().posX, aCrop.getLocation().posY - i, aCrop.getLocation().posZ);

            if (tBlock instanceof BlockOresAbstract) {
                TileEntity tTileEntity = aCrop.getWorld().getTileEntity(
                        aCrop.getLocation().posX,
                        aCrop.getLocation().posY - i,
                        aCrop.getLocation().posZ);
                if (tTileEntity instanceof TileEntityOres) {
                    Materials tMaterial = GregTechAPI.sGeneratedMaterials[((TileEntityOres) tTileEntity).mMetaData
                            % 1000];
                    if (tMaterial != null && tMaterial != Materials._NULL) {
                        return tMaterial == Materials.GarnetRed || tMaterial == Materials.GarnetYellow;
                    }
                }
            } else {
                int tMetaID = aCrop.getWorld().getBlockMetadata(
                        aCrop.getLocation().posX,
                        aCrop.getLocation().posY - i,
                        aCrop.getLocation().posZ);
                ItemData tAssotiation = GTOreDictUnificator.getAssociation(new ItemStack(tBlock, 1, tMetaID));
                if (tAssotiation != null
                        && (tAssotiation.mPrefix.toString().startsWith("ore")
                                || tAssotiation.mPrefix == OrePrefixes.block)
                        && (tAssotiation.mMaterial.mMaterial == Materials.GarnetRed
                                || tAssotiation.mMaterial.mMaterial == Materials.GarnetYellow)) {
                    return true;
                }
            }
        }

        return false;

    }

    @Override
    public boolean canBeHarvested(ICropTile iCropTile) {
        return iCropTile.getSize() >= this.maxSize();
    }

    @Override
    public ItemStack getGain(ICropTile iCropTile) {
        Materials dropMat;
        ItemStack drop;
        if (XSTR.XSTR_INSTANCE.nextInt(100) >= 50) dropMat = Materials.GarnetRed;
        else dropMat = Materials.GarnetYellow;

        int chance = XSTR.XSTR_INSTANCE.nextInt(100);

        if (chance >= 95) drop = GTOreDictUnificator.get(OrePrefixes.gemExquisite, dropMat, 1);
        else if (chance >= 80) drop = dropMat.getGems(1);
        else if (chance == 42) drop = GTOreDictUnificator.get(OrePrefixes.crushedPurified, Materials.GarnetSand, 1);
        else if (chance >= 40) drop = dropMat.getDust(1);
        else if (chance == 23) drop = GTOreDictUnificator.get(OrePrefixes.crushedPurified, dropMat, 1);
        else if (chance >= 20) drop = dropMat.getDustSmall(1);
        else if (chance == 13 || chance == 17) drop = GTOreDictUnificator.get(OrePrefixes.crushedPurified, dropMat, 1);
        else drop = dropMat.getDustTiny(1);
        return drop;
    }

    @Override
    public List<String> getCropInformation() {
        return Arrays.asList(
                "Needs a block or ore of Yellow or Red Garnet below to fully mature.",
                "Has decreased humidity and air requirements (x0.5)",
                "Has increased nutrient requirements (x2.0)");
    }

    @Override
    public ItemStack getDisplayItem() {
        return Materials.GarnetRed.getGems(1);
    }
}
