package com.github.bartimaeusnek.cropspp.GTHandler.machines;

import java.util.HashMap;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

import com.gtnewhorizons.modularui.api.drawable.FallbackableUITexture;
import com.gtnewhorizons.modularui.api.drawable.IDrawable;
import com.gtnewhorizons.modularui.api.math.Pos2d;
import com.gtnewhorizons.modularui.common.widget.SlotWidget;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.SoundResource;
import gregtech.api.enums.Textures;
import gregtech.api.gui.modularui.GTUITextures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.MTEBasicMachine;
import gregtech.api.recipe.BasicUIProperties;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GTUtility;
import gregtech.common.items.behaviors.BehaviourDataOrb;
import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;

public class CropSynthesiser extends MTEBasicMachine {

    public CropSynthesiser(int aID, String aName, String aNameRegional, int aTier) {
        super(
                aID,
                aName,
                aNameRegional,
                aTier,
                16,
                new String[] { "It can make Crops from DataOrbs", "It needs a crop's tier/2*1000L of UUM per Crop",
                        "Takes in 16A",
                        "Needs crop's (tier+2)/2 as Voltage level, round down (Tier 5 crop needs 7/2=~3=HV)",
                        "Can process crops up to tier " + getMaxCropTier(aTier) },
                4,
                1,
                TextureFactory.of(
                        TextureFactory
                                .of(new Textures.BlockIcons.CustomIcon("basicmachines/scanner/OVERLAY_SIDE_ACTIVE")),
                        TextureFactory.builder().addIcon(
                                new Textures.BlockIcons.CustomIcon("basicmachines/scanner/OVERLAY_SIDE_ACTIVE_GLOW"))
                                .glow().build()),
                TextureFactory.of(
                        TextureFactory.of(new Textures.BlockIcons.CustomIcon("basicmachines/scanner/OVERLAY_SIDE")),
                        TextureFactory.builder()
                                .addIcon(new Textures.BlockIcons.CustomIcon("basicmachines/scanner/OVERLAY_SIDE_GLOW"))
                                .glow().build()),
                TextureFactory.of(
                        TextureFactory
                                .of(new Textures.BlockIcons.CustomIcon("basicmachines/scanner/OVERLAY_FRONT_ACTIVE")),
                        TextureFactory.builder().addIcon(
                                new Textures.BlockIcons.CustomIcon("basicmachines/scanner/OVERLAY_FRONT_ACTIVE_GLOW"))
                                .glow().build()),
                TextureFactory.of(
                        TextureFactory.of(new Textures.BlockIcons.CustomIcon("basicmachines/scanner/OVERLAY_FRONT")),
                        TextureFactory.builder()
                                .addIcon(new Textures.BlockIcons.CustomIcon("basicmachines/scanner/OVERLAY_FRONT_GLOW"))
                                .glow().build()),
                TextureFactory.of(
                        TextureFactory
                                .of(new Textures.BlockIcons.CustomIcon("basicmachines/scanner/OVERLAY_TOP_ACTIVE")),
                        TextureFactory.builder().addIcon(
                                new Textures.BlockIcons.CustomIcon("basicmachines/scanner/OVERLAY_TOP_ACTIVE_GLOW"))
                                .glow().build()),
                TextureFactory.of(
                        TextureFactory.of(new Textures.BlockIcons.CustomIcon("basicmachines/scanner/OVERLAY_TOP")),
                        TextureFactory.builder()
                                .addIcon(new Textures.BlockIcons.CustomIcon("basicmachines/scanner/OVERLAY_TOP_GLOW"))
                                .glow().build()),
                TextureFactory.of(
                        TextureFactory
                                .of(new Textures.BlockIcons.CustomIcon("basicmachines/scanner/OVERLAY_BOTTOM_ACTIVE")),
                        TextureFactory.builder().addIcon(
                                new Textures.BlockIcons.CustomIcon("basicmachines/scanner/OVERLAY_BOTTOM_ACTIVE_GLOW"))
                                .glow().build()),
                TextureFactory.of(
                        TextureFactory.of(new Textures.BlockIcons.CustomIcon("basicmachines/scanner/OVERLAY_BOTTOM")),
                        TextureFactory.builder()
                                .addIcon(
                                        new Textures.BlockIcons.CustomIcon("basicmachines/scanner/OVERLAY_BOTTOM_GLOW"))
                                .glow().build()));
    }

    public CropSynthesiser(String mName, byte mTier, String[] mDescriptionArray, ITexture[][][] mTextures) {
        super(mName, mTier, 16, mDescriptionArray, mTextures, 4, 1);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity arg0) {
        return new CropSynthesiser(this.mName, this.mTier, this.mDescriptionArray, this.mTextures);
    }

    public static int getMaxCropTier(int mTier) {
        return (mTier * 2 - 1);
    }

    @Override
    public int checkRecipe(boolean skipOC) {
        ItemStack[] aStacks = { getInputAt(0), getInputAt(1), getInputAt(2), getInputAt(3) };

        if (ItemList.Tool_DataOrb.isStackEqual(aStacks[0], false, true)
                && ItemList.Tool_DataOrb.isStackEqual(aStacks[1], false, true)
                && ItemList.Tool_DataOrb.isStackEqual(aStacks[2], false, true)
                && ItemList.Tool_DataOrb.isStackEqual(aStacks[3], false, true)) {

            if (this.mFluid == null || !this.mFluid.getFluid().getName().equals("ic2uumatter"))
                return DID_NOT_FIND_RECIPE;

            if (getOutputAt(0) != null) return DID_NOT_FIND_RECIPE;

            HashMap<String, String> CropStats = new HashMap<>();

            for (byte i = 0; i <= 3; ++i)
                CropStats.put(BehaviourDataOrb.getDataTitle(aStacks[i]), BehaviourDataOrb.getDataName(aStacks[i]));

            String owner = CropStats.get("Crop-Specimen-Scan");
            String name = owner.split(":")[1];
            owner = owner.split(":")[0];

            if (CropStats.get("Crop-Growth-Scan").isEmpty() || CropStats.get("Crop-Gain-Scan").isEmpty()
                    || CropStats.get("Crop-Resistance-Scan").isEmpty()
                    || CropStats.get("Crop-Specimen-Scan").isEmpty())
                return DID_NOT_FIND_RECIPE;

            CropCard card = Crops.instance.getCropCard(owner, name);

            if (card == null) return DID_NOT_FIND_RECIPE;

            if (card.tier() > getMaxCropTier(this.mTier)) return DID_NOT_FIND_RECIPE;

            long neededUUM = ((long) (((float) card.tier() / 2) * 1000));

            if (this.mFluid.amount < neededUUM) return FOUND_RECIPE_BUT_DID_NOT_MEET_REQUIREMENTS;

            NBTTagCompound tNBT = new NBTTagCompound();
            ItemStack op = ItemList.IC2_Crop_Seeds.get(1L);
            tNBT.setByte("growth", Byte.parseByte(CropStats.get("Crop-Growth-Scan")));
            tNBT.setByte("gain", Byte.parseByte(CropStats.get("Crop-Gain-Scan")));
            tNBT.setByte("resistance", Byte.parseByte(CropStats.get("Crop-Resistance-Scan")));
            tNBT.setString("owner", owner);
            tNBT.setString("name", name);
            tNBT.setByte("scan", (byte) 4);
            op.setTagCompound(tNBT);
            this.mOutputItems[0] = op;

            this.mFluid.amount -= neededUUM;

            long power = GTValues.V[(card.tier() + 2) / 2];
            power *= mAmperage;

            calculateOverclockedNess((int) (power - (power / 10)), 12000);
            if (mMaxProgresstime == Integer.MAX_VALUE - 1 && mEUt == Integer.MAX_VALUE - 1)
                return FOUND_RECIPE_BUT_DID_NOT_MEET_REQUIREMENTS;
            return FOUND_AND_SUCCESSFULLY_USED_RECIPE;
        }
        return DID_NOT_FIND_RECIPE;
    }

    @Override
    public void startSoundLoop(byte aIndex, double aX, double aY, double aZ) {
        super.startSoundLoop(aIndex, aX, aY, aZ);
        if (aIndex == 1) {
            GTUtility.doSoundAtClient(SoundResource.IC2_MACHINES_MAGNETIZER_LOOP, 10, 1.0F, aX, aY, aZ);
        }
    }

    @Override
    public void startProcess() {
        sendLoopStart((byte) 1);
    }

    @Override
    public boolean isFluidInputAllowed(FluidStack aFluid) {
        return (aFluid.getFluid().getName().equals("ic2uumatter")) || (super.isFluidInputAllowed(aFluid));
    }

    @Override
    public int getCapacity() {
        return 3000 * mTier;
    }

    @Override
    public boolean canInsertItem(int aIndex, ItemStack aStack, int ordinalSide) {
        if (ItemList.Tool_DataOrb.isStackEqual(aStack, false, true)) return isValidSlot(aIndex) && aStack != null
                && aIndex < mInventory.length
                && (mInventory[aIndex] == null || GTUtility.areStacksEqual(aStack, mInventory[aIndex]))
                && allowPutStack(getBaseMetaTileEntity(), aIndex, ForgeDirection.getOrientation(ordinalSide), aStack);
        return false;
    }

    private static final FallbackableUITexture progressBarTexture = GTUITextures
            .fallbackableProgressbar("crop_synthesiser", GTUITextures.PROGRESSBAR_ARROW);

    @Override
    protected BasicUIProperties getUIProperties() {
        return super.getUIProperties().toBuilder().progressBarTexture(progressBarTexture).build();
    }

    @Override
    protected SlotWidget createItemInputSlot(int index, IDrawable[] backgrounds, Pos2d pos) {
        return (SlotWidget) super.createItemInputSlot(index, backgrounds, pos)
                .setBackground(getGUITextureSet().getItemSlot(), GTUITextures.OVERLAY_SLOT_DATA_ORB);
    }
}
