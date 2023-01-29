package com.github.bartimaeusnek.cropspp.GTHandler.machines;

import static gregtech.api.enums.Textures.BlockIcons.*;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.github.bartimaeusnek.cropspp.GTHandler.CPP_UITextures;
import com.gtnewhorizons.modularui.api.drawable.IDrawable;
import com.gtnewhorizons.modularui.api.math.Pos2d;
import com.gtnewhorizons.modularui.api.math.Size;
import com.gtnewhorizons.modularui.api.screen.ModularWindow;
import com.gtnewhorizons.modularui.api.screen.UIBuildContext;
import com.gtnewhorizons.modularui.common.widget.ProgressBar;
import com.gtnewhorizons.modularui.common.widget.SlotWidget;

import gregtech.api.GregTech_API;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.gui.modularui.GT_UITextures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
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
                "Crop_Replicator.png",
                "",
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

    public CropReplicator(String mName, byte mTier, String[] mDescriptionArray, ITexture[][][] mTextures,
            String mGUIName, String mNEIName) {
        super(mName, mTier, 6, mDescriptionArray, mTextures, 2, 2, mGUIName, mNEIName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity arg0) {
        return new CropReplicator(
                this.mName,
                this.mTier,
                this.mDescriptionArray,
                this.mTextures,
                this.mGUIName,
                this.mNEIName);
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
            GT_Utility.doSoundAtClient((String) GregTech_API.sSoundList.get(212), 10, 1.0F, aX, aY, aZ);
        }
    }

    public void startProcess() {
        sendLoopStart((byte) 1);
    }

    @Override
    public boolean canInsertItem(int aIndex, ItemStack aStack, int aSide) {
        if (GT_Utility.areUnificationsEqual((ItemStack) aStack, Materials.UUMatter.getCells(1), true)
                || ItemList.IC2_Crop_Seeds.isStackEqual(aStack, true, true))
            return isValidSlot(aIndex) && aStack != null
                    && aIndex < mInventory.length
                    && (mInventory[aIndex] == null || GT_Utility.areStacksEqual(aStack, mInventory[aIndex]))
                    && allowPutStack(getBaseMetaTileEntity(), aIndex, (byte) aSide, aStack);
        return false;
    }

    @Override
    public boolean useModularUI() {
        return true;
    }

    @Override
    public void addUIWidgets(ModularWindow.Builder builder, UIBuildContext buildContext) {
        super.addUIWidgets(builder, buildContext);
        builder.widget(
                createProgressBar(
                        GT_UITextures.PROGRESSBAR_ARROW,
                        20,
                        ProgressBar.Direction.RIGHT,
                        new Pos2d(78, 24),
                        new Size(20, 18)));
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
