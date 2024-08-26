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
import gregtech.api.enums.SoundResource;
import gregtech.api.gui.modularui.GT_UITextures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtech.api.recipe.BasicUIProperties;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GT_Utility;
import gregtech.common.items.behaviors.Behaviour_DataOrb;
import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;

public class CropGeneExtractor extends GT_MetaTileEntity_BasicMachine {

    public CropGeneExtractor(int aID, String aName, String aNameRegional, int aTier) {
        super(
                aID,
                aName,
                aNameRegional,
                aTier,
                1,
                new String[] { "It can extract CropGenes", "Use a circuit to determine the genes you want to extract,",
                        "1 for Specimen, 2 for Growth, 3 for Gain, 4 for Resistance", "Takes in 1A",
                        "Needs crop's (tier+2)/2 as Voltage level, round down (Tier 5 crop needs 7/2=~3=HV)",
                        "Can process crops up to tier " + getMaxCropTier(aTier) },
                1,
                1,
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

    public CropGeneExtractor(String mName, byte mTier, String[] mDescriptionArray, ITexture[][][] mTextures) {
        super(mName, mTier, 1, mDescriptionArray, mTextures, 1, 1);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity arg0) {
        return new CropGeneExtractor(this.mName, this.mTier, this.mDescriptionArray, this.mTextures);
    }

    public static int getMaxCropTier(int mTier) {
        return (mTier * 2 - 1);
    }

    @Override
    public int checkRecipe(boolean skipOC) {
        ItemStack aStack = getInputAt(0);
        ItemStack bStack = getStackInSlot(getCircuitSlot());
        ItemStack tosave = getSpecialSlot();

        if (ItemList.IC2_Crop_Seeds.isStackEqual(aStack, true, true)
                && ItemList.Tool_DataOrb.isStackEqual(tosave, false, true)
                && bStack != null) {
            NBTTagCompound tNBT = aStack.getTagCompound();
            if (tNBT == null || tNBT.getString("name").isEmpty()) return DID_NOT_FIND_RECIPE;
            if (getOutputAt(0) != null) return DID_NOT_FIND_RECIPE;
            CropCard card = Crops.instance.getCropCard(tNBT.getString("owner"), tNBT.getString("name"));
            if (card.tier() > getMaxCropTier(this.mTier)) return DID_NOT_FIND_RECIPE;
            if (bStack.getItemDamage() < 1 || bStack.getItemDamage() > 4) return DID_NOT_FIND_RECIPE;
            byte[] GrGaRe = new byte[3];
            GrGaRe[0] = tNBT.getByte("growth");
            GrGaRe[1] = tNBT.getByte("gain");
            GrGaRe[2] = tNBT.getByte("resistance");
            this.mOutputItems[0] = ItemList.Tool_DataOrb.get(1L, new Object[0]);

            aStack.stackSize -= 1;
            tosave.stackSize -= 1;

            switch (bStack.getItemDamage()) {
                case 1:
                    Behaviour_DataOrb.setDataTitle(this.mOutputItems[0], "Crop-Specimen-Scan");
                    Behaviour_DataOrb
                            .setDataName(this.mOutputItems[0], tNBT.getString("owner") + ":" + tNBT.getString("name"));
                    break;
                case 2:
                    Behaviour_DataOrb.setDataTitle(this.mOutputItems[0], "Crop-Growth-Scan");
                    Behaviour_DataOrb.setDataName(this.mOutputItems[0], Byte.toString(GrGaRe[0]));
                    break;
                case 3:
                    Behaviour_DataOrb.setDataTitle(this.mOutputItems[0], "Crop-Gain-Scan");
                    Behaviour_DataOrb.setDataName(this.mOutputItems[0], Byte.toString(GrGaRe[1]));
                    break;
                case 4:
                    Behaviour_DataOrb.setDataTitle(this.mOutputItems[0], "Crop-Resistance-Scan");
                    Behaviour_DataOrb.setDataName(this.mOutputItems[0], Byte.toString(GrGaRe[2]));
                    break;
                default:
                    break;
            }
            long power = GT_Values.V[(card.tier() + 2) / 2];
            calculateOverclockedNess((int) (power - (power / 10)), 6000);
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
        if (ItemList.IC2_Crop_Seeds.isStackEqual(aStack, true, true)) return isValidSlot(aIndex) && aStack != null
                && aIndex < mInventory.length
                && (mInventory[aIndex] == null || GT_Utility.areStacksEqual(aStack, mInventory[aIndex]))
                && allowPutStack(getBaseMetaTileEntity(), aIndex, ForgeDirection.getOrientation(ordinalSide), aStack);
        return false;
    }

    @Override
    public boolean allowSelectCircuit() {
        return true;
    }

    private static final FallbackableUITexture progressBarTexture = GT_UITextures
            .fallbackableProgressbar("crop_gene_extractor", GT_UITextures.PROGRESSBAR_ARROW);

    @Override
    protected BasicUIProperties getUIProperties() {
        return super.getUIProperties().toBuilder().progressBarTexture(progressBarTexture).build();
    }

    @Override
    protected SlotWidget createItemInputSlot(int index, IDrawable[] backgrounds, Pos2d pos) {
        return (SlotWidget) super.createItemInputSlot(index, backgrounds, pos)
                .setBackground(getGUITextureSet().getItemSlot(), CPP_UITextures.OVERLAY_SLOT_SEED);
    }

    @Override
    protected SlotWidget createSpecialSlot(IDrawable[] backgrounds, Pos2d pos, BasicUIProperties uiProperties) {
        return (SlotWidget) super.createSpecialSlot(backgrounds, pos, uiProperties)
                .setGTTooltip(() -> mTooltipCache.getData("bpp.machines.special_slot.tooltip"))
                .setBackground(getGUITextureSet().getItemSlot(), GT_UITextures.OVERLAY_SLOT_DATA_ORB);
    }
}
