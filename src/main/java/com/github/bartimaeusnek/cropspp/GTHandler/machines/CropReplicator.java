package com.github.bartimaeusnek.cropspp.GTHandler.machines;

import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_BOTTOM_SCANNER;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_BOTTOM_SCANNER_ACTIVE;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_BOTTOM_SCANNER_ACTIVE_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_BOTTOM_SCANNER_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_FRONT_SCANNER;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_FRONT_SCANNER_ACTIVE;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_FRONT_SCANNER_ACTIVE_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_FRONT_SCANNER_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_SIDE_SCANNER;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_SIDE_SCANNER_ACTIVE;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_SIDE_SCANNER_ACTIVE_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_SIDE_SCANNER_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_TOP_SCANNER;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_TOP_SCANNER_ACTIVE;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_TOP_SCANNER_ACTIVE_GLOW;
import static gregtech.api.enums.Textures.BlockIcons.OVERLAY_TOP_SCANNER_GLOW;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

import com.github.bartimaeusnek.cropspp.GTHandler.CPP_UITextures;
import com.gtnewhorizons.modularui.api.drawable.FallbackableUITexture;
import com.gtnewhorizons.modularui.api.drawable.IDrawable;
import com.gtnewhorizons.modularui.api.math.Pos2d;
import com.gtnewhorizons.modularui.common.widget.SlotWidget;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.SoundResource;
import gregtech.api.gui.modularui.GT_UITextures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtech.api.recipe.BasicUIProperties;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GT_Utility;
import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;

public class CropReplicator extends GT_MetaTileEntity_BasicMachine {

    public CropReplicator(int aID, String aName, String aNameRegional, int aTier) {
        super(
                aID,
                aName,
                aNameRegional,
                aTier,
                6,
                new String[] { "It can replicate Crops", "It needs a Cell of UUM per crop's tier", "Takes in 6A",
                        "Needs crop's (tier+2)/2 as Voltage level, round down (Tier 5 crop needs 7/2=~3=HV)",
                        "Can process crops up to tier " + getMaxCropTier(aTier) },
                2,
                2,
                TextureFactory.of(
                        TextureFactory.of(OVERLAY_SIDE_SCANNER_ACTIVE),
                        TextureFactory.builder().addIcon(OVERLAY_SIDE_SCANNER_ACTIVE_GLOW).glow().build()),
                TextureFactory.of(
                        TextureFactory.of(OVERLAY_SIDE_SCANNER),
                        TextureFactory.builder().addIcon(OVERLAY_SIDE_SCANNER_GLOW).glow().build()),
                TextureFactory.of(
                        TextureFactory.of(OVERLAY_FRONT_SCANNER_ACTIVE),
                        TextureFactory.builder().addIcon(OVERLAY_FRONT_SCANNER_ACTIVE_GLOW).glow().build()),
                TextureFactory.of(
                        TextureFactory.of(OVERLAY_FRONT_SCANNER),
                        TextureFactory.builder().addIcon(OVERLAY_FRONT_SCANNER_GLOW).glow().build()),
                TextureFactory.of(
                        TextureFactory.of(OVERLAY_TOP_SCANNER_ACTIVE),
                        TextureFactory.builder().addIcon(OVERLAY_TOP_SCANNER_ACTIVE_GLOW).glow().build()),
                TextureFactory.of(
                        TextureFactory.of(OVERLAY_TOP_SCANNER),
                        TextureFactory.builder().addIcon(OVERLAY_TOP_SCANNER_GLOW).glow().build()),
                TextureFactory.of(
                        TextureFactory.of(OVERLAY_BOTTOM_SCANNER_ACTIVE),
                        TextureFactory.builder().addIcon(OVERLAY_BOTTOM_SCANNER_ACTIVE_GLOW).glow().build()),
                TextureFactory.of(
                        TextureFactory.of(OVERLAY_BOTTOM_SCANNER),
                        TextureFactory.builder().addIcon(OVERLAY_BOTTOM_SCANNER_GLOW).glow().build()));
    }

    public CropReplicator(String mName, byte mTier, String[] mDescriptionArray, ITexture[][][] mTextures) {
        super(mName, mTier, 6, mDescriptionArray, mTextures, 2, 2);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity arg0) {
        return new CropReplicator(this.mName, this.mTier, this.mDescriptionArray, this.mTextures);
    }

    public static int getMaxCropTier(int mTier) {
        return (mTier * 2 - 1);
    }

    @Override
    public int checkRecipe(boolean skipOC) {
        ItemStack aStack = getInputAt(0);
        ItemStack bStack = getInputAt(1);

        if (GT_Utility.areUnificationsEqual(aStack, Materials.UUMatter.getCells(1), true)
                && ItemList.IC2_Crop_Seeds.isStackEqual(bStack, true, true)) {
            ItemStack helper = bStack;
            bStack = aStack;
            aStack = helper;
        }
        if (GT_Utility.areUnificationsEqual(bStack, Materials.UUMatter.getCells(1), true)
                && ItemList.IC2_Crop_Seeds.isStackEqual(aStack, true, true)) {
            NBTTagCompound tNBT = aStack.getTagCompound();

            if (tNBT == null || tNBT.getString("name").isEmpty()) return DID_NOT_FIND_RECIPE;
            if (getOutputAt(0) != null || getOutputAt(1) != null) return DID_NOT_FIND_RECIPE;
            CropCard card = Crops.instance.getCropCard(tNBT.getString("owner"), tNBT.getString("name"));
            if (card.tier() > getMaxCropTier(this.mTier)) return DID_NOT_FIND_RECIPE;
            if (bStack.stackSize < card.tier()) return FOUND_RECIPE_BUT_DID_NOT_MEET_REQUIREMENTS;

            this.mOutputItems[0] = aStack.copy();
            this.mOutputItems[0].stackSize = 2;

            bStack.stackSize -= card.tier();
            aStack.stackSize -= 1;
            this.mOutputItems[1] = Materials.Empty.getCells(card.tier());

            long power = GT_Values.V[(card.tier() + 2) / 2];
            power *= mAmperage;
            calculateOverclockedNess((int) (power - (power / 10)), 12000);
            if (mMaxProgresstime == Integer.MAX_VALUE - 1 && mEUt == Integer.MAX_VALUE - 1)
                return FOUND_RECIPE_BUT_DID_NOT_MEET_REQUIREMENTS;
            return FOUND_AND_SUCCESSFULLY_USED_RECIPE;
        }
        return DID_NOT_FIND_RECIPE;
    }

    public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {
        super.startSoundLoop(aIndex, aX, aY, aZ);
        if (aIndex == 1) {
            GT_Utility.doSoundAtClient(SoundResource.IC2_MACHINES_MAGNETIZER_LOOP, 10, 1.0F, aX, aY, aZ);
        }
    }

    public void startProcess() {
        sendLoopStart((byte) 1);
    }

    @Override
    public boolean canInsertItem(int aIndex, ItemStack aStack, int ordinalSide) {
        if (GT_Utility.areUnificationsEqual(aStack, Materials.UUMatter.getCells(1), true)
                || ItemList.IC2_Crop_Seeds.isStackEqual(aStack, true, true))
            return isValidSlot(aIndex) && aStack != null
                    && aIndex < mInventory.length
                    && (mInventory[aIndex] == null || GT_Utility.areStacksEqual(aStack, mInventory[aIndex]))
                    && allowPutStack(
                            getBaseMetaTileEntity(),
                            aIndex,
                            ForgeDirection.getOrientation(ordinalSide),
                            aStack);
        return false;
    }

    private static final FallbackableUITexture progressBarTexture = GT_UITextures
            .fallbackableProgressbar("crop_replicator", GT_UITextures.PROGRESSBAR_ARROW);

    @Override
    protected BasicUIProperties getUIProperties() {
        return super.getUIProperties().toBuilder().progressBarTexture(progressBarTexture).build();
    }

    @Override
    protected SlotWidget createItemInputSlot(int index, IDrawable[] backgrounds, Pos2d pos) {
        if (index == 0) {
            return (SlotWidget) super.createItemInputSlot(index, backgrounds, pos)
                    .setBackground(getGUITextureSet().getItemSlot(), GT_UITextures.OVERLAY_SLOT_CANISTER);
        } else {
            return (SlotWidget) super.createItemInputSlot(index, backgrounds, pos)
                    .setBackground(getGUITextureSet().getItemSlot(), CPP_UITextures.OVERLAY_SLOT_SEED);
        }
    }
}
