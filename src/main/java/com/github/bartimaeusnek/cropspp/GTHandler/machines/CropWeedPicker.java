package com.github.bartimaeusnek.cropspp.GTHandler.machines;

import static gregtech.api.enums.GTValues.V;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

import com.github.bartimaeusnek.cropspp.items.CppItems;
import com.gtnewhorizons.modularui.api.screen.ModularWindow;
import com.gtnewhorizons.modularui.api.screen.UIBuildContext;
import com.gtnewhorizons.modularui.common.widget.DrawableWidget;
import com.gtnewhorizons.modularui.common.widget.SlotWidget;
import com.gtnewhorizons.modularui.common.widget.TextWidget;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.enums.Materials;
import gregtech.api.enums.Textures;
import gregtech.api.gui.modularui.GTUITextures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.MTEHatch;
import gregtech.api.render.TextureFactory;
import gregtech.api.util.GTUtility;
import gregtech.common.tileentities.machines.basic.MTEPump;
import ic2.core.crop.TileEntityCrop;

public class CropWeedPicker extends MTEHatch {

    private static ITexture OVERLAY;

    public CropWeedPicker(int aID, String aName, String aNameRegional, int aTier) {
        super(
                aID,
                aName,
                aNameRegional,
                aTier,
                1,
                new String[] { "Automatically picks Weeds", "Range = Tier", "Takes in 1A",
                        "Needs a Weeding Trovel or a Spade in its Inventory",
                        "Need to be supplied with 1L Lubricant per tick." });
    }

    public CropWeedPicker(String mName, byte mTier, String[] mDescriptionArray, ITexture[][][] mTextures) {
        super(mName, mTier, 1, mDescriptionArray, mTextures);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity arg0) {
        return new CropWeedPicker(this.mName, this.mTier, this.mDescriptionArray, this.mTextures);
    }

    @Override
    public boolean isFluidInputAllowed(FluidStack aFluid) {
        return (aFluid.getFluid().equals(Materials.Lubricant.getFluid(1L).getFluid()))
                || (super.isFluidInputAllowed(aFluid));
    }

    @Override
    public int getCapacity() {
        return 1000 * mTier;
    }

    @Override
    public boolean canInsertItem(int aIndex, ItemStack aStack, int ordinalSide) {
        return isValidSlot(aIndex) && aStack != null
                && aIndex < mInventory.length
                && (mInventory[aIndex] == null || GTUtility.areStacksEqual(aStack, mInventory[aIndex]))
                && allowPutStack(getBaseMetaTileEntity(), aIndex, ForgeDirection.getOrientation(ordinalSide), aStack);
    }

    @Override
    public boolean doesFillContainers() {
        return false;
    }

    @Override
    public boolean doesEmptyContainers() {
        return true;
    }

    @Override
    public boolean canTankBeFilled() {
        return true;
    }

    @Override
    public boolean canTankBeEmptied() {
        return false;
    }

    @Override
    public void onPostTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick) {

        super.onPostTick(aBaseMetaTileEntity, aTick);

        if (this.getBaseMetaTileEntity().isServerSide()) {

            if (!((this.getBaseMetaTileEntity().isAllowedToWork())
                    && (GTUtility.areStacksEqual(mInventory[0], CppItems.itemSpadeStack)
                            || GTUtility.areStacksEqual(mInventory[0], ic2.core.Ic2Items.weedingTrowel))
                    && (this.getFluid().amount >= 20)
                    && (this.getBaseMetaTileEntity().isUniversalEnergyStored(MTEPump.getEuUsagePerTier(this.mTier)))))
                return;

            this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(MTEPump.getEuUsagePerTier(this.mTier), true);
            this.getFluid().amount -= 20;

            int xmin = this.getBaseMetaTileEntity().getXCoord() > 0
                    ? this.getBaseMetaTileEntity().getXCoord() - this.mTier
                    : this.getBaseMetaTileEntity().getXCoord() + this.mTier;
            int xmax = this.getBaseMetaTileEntity().getXCoord() > 0
                    ? this.getBaseMetaTileEntity().getXCoord() + this.mTier
                    : this.getBaseMetaTileEntity().getXCoord() - this.mTier;

            int zmin = this.getBaseMetaTileEntity().getZCoord() > 0
                    ? this.getBaseMetaTileEntity().getZCoord() - this.mTier
                    : this.getBaseMetaTileEntity().getZCoord() + this.mTier;
            int zmax = this.getBaseMetaTileEntity().getZCoord() > 0
                    ? this.getBaseMetaTileEntity().getZCoord() + this.mTier
                    : this.getBaseMetaTileEntity().getZCoord() - this.mTier;

            for (int x = xmin; x <= xmax; ++x) {
                for (int z = zmin; z <= zmax; ++z) {

                    TileEntity possibleCrop = this.getBaseMetaTileEntity().getWorld()
                            .getTileEntity(x, this.getBaseMetaTileEntity().getYCoord(), z);

                    if (!(possibleCrop instanceof TileEntityCrop cropTE)) continue;

                    if (cropTE.getCrop() == null) continue;

                    if (cropTE.getCrop().tier() <= 0) cropTE.reset();

                    cropTE.weedlevel = 0;
                    cropTE.updateState();
                    cropTE.markDirty();
                }
            }
        }
    }

    @Override
    public boolean onRightclick(IGregTechTileEntity aBaseMetaTileEntity, EntityPlayer aPlayer) {
        openGui(aPlayer);
        return true;
    }

    @Override
    public boolean isElectric() {
        return true;
    }

    @Override
    public boolean isFacingValid(ForgeDirection facing) {
        return true;
    }

    @Override
    public boolean isEnetInput() {
        return true;
    }

    @Override
    public boolean isInputFacing(ForgeDirection side) {
        return true;
    }

    @Override
    public boolean isOutputFacing(ForgeDirection side) {
        return false;
    }

    @Override
    public boolean isTeleporterCompatible() {
        return false;
    }

    @Override
    public long getMinimumStoredEU() {
        return V[mTier] * 16;
    }

    @Override
    public long maxEUStore() {
        return V[mTier] * 64;
    }

    @Override
    public long maxEUInput() {
        return V[mTier];
    }

    @Override
    public long maxSteamStore() {
        return maxEUStore();
    }

    @Override
    public long maxAmperesIn() {
        return 1;
    }

    @Override
    public int getStackDisplaySlot() {
        return 2;
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister aBlockIconRegister) {
        super.registerIcons(aBlockIconRegister);
        OVERLAY = TextureFactory.of(new Textures.BlockIcons.CustomIcon("bpp:OVERLAY_WEED_PICKER"));
    }

    @Override
    public ITexture[] getTexture(IGregTechTileEntity baseMetaTileEntity, ForgeDirection side, ForgeDirection facing,
            int colorIndex, boolean active, boolean redstoneLevel) {
        return new ITexture[] { Textures.BlockIcons.MACHINE_CASINGS[mTier][colorIndex + 1],
                (side == ForgeDirection.DOWN || side == ForgeDirection.UP)
                        ? TextureFactory.of(Textures.BlockIcons.OVERLAY_PIPE_OUT)
                        : OVERLAY };
    }

    @Override
    public ITexture[] getTexturesActive(ITexture aBaseTexture) {
        return getTexturesInactive(aBaseTexture);
    }

    @Override
    public ITexture[] getTexturesInactive(ITexture aBaseTexture) {
        return new ITexture[] { OVERLAY, OVERLAY, OVERLAY, OVERLAY };
    }

    @Override
    public void addUIWidgets(ModularWindow.Builder builder, UIBuildContext buildContext) {
        builder.widget(
                new DrawableWidget().setDrawable(GTUITextures.PICTURE_SCREEN_BLACK).setPos(7, 16).setSize(71, 45))
                .widget(
                        new SlotWidget(inventoryHandler, getInputSlot())
                                .setBackground(getGUITextureSet().getItemSlot(), GTUITextures.OVERLAY_SLOT_IN)
                                .setPos(79, 16))
                .widget(new TextWidget("Liquid Amount").setDefaultColor(COLOR_TEXT_WHITE.get()).setPos(10, 20)).widget(
                        TextWidget.dynamicString(() -> GTUtility.formatNumbers(mFluid != null ? mFluid.amount : 0))
                                .setDefaultColor(COLOR_TEXT_WHITE.get()).setPos(10, 30));
    }
}
