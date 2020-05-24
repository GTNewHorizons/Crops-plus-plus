package com.github.bartimaeusnek.cropspp.GTHandler.machines;

import com.github.bartimaeusnek.cropspp.items.CppItems;
import gregtech.api.enums.Materials;
import gregtech.api.enums.Textures;
import gregtech.api.gui.GT_Container_BasicTank;
import gregtech.api.gui.GT_GUIContainer_BasicTank;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch;
import gregtech.api.objects.GT_RenderedTexture;
import gregtech.api.util.GT_Utility;
import gregtech.common.tileentities.machines.basic.GT_MetaTileEntity_Pump;
import ic2.core.crop.TileEntityCrop;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fluids.FluidStack;

import static gregtech.api.enums.GT_Values.V;

public class CropWeedPicker extends GT_MetaTileEntity_Hatch {

    private FakePlayer mFakePlayer = null;

    public CropWeedPicker(int aID, String aName, String aNameRegional, int aTier) {
        super(aID, aName, aNameRegional, aTier, 1, new String[]{"Automatically picks Weeds", "Range = Tier", "Takes in 1A", "Needs a Weeding Trovel or a Spade in its Inventory", "Need to be supplied with 1L Lubricant per tick."}, new ITexture[]{new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_SIDE_SCANNER_ACTIVE), new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_SIDE_SCANNER), new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_FRONT_SCANNER_ACTIVE), new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_FRONT_SCANNER), new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_TOP_SCANNER_ACTIVE), new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_TOP_SCANNER), new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_BOTTOM_SCANNER_ACTIVE), new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_BOTTOM_SCANNER)});
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
        return (aFluid.getFluid().equals(Materials.Lubricant.getFluid(1L).getFluid())) || (super.isFluidInputAllowed(aFluid));
    }

    @Override
    public int getCapacity() {
        return 1000 * mTier;
    }

    @Override
    public boolean canInsertItem(int aIndex, ItemStack aStack, int aSide) {
        return isValidSlot(aIndex) && aStack != null && aIndex < mInventory.length && (mInventory[aIndex] == null || GT_Utility.areStacksEqual(aStack, mInventory[aIndex])) && allowPutStack(getBaseMetaTileEntity(), aIndex, (byte) aSide, aStack);
    }

    @Override
    public Object getServerGUI(int aID, InventoryPlayer aPlayerInventory, IGregTechTileEntity aBaseMetaTileEntity) {
        return new GT_Container_BasicTank(aPlayerInventory, aBaseMetaTileEntity);
    }

    @Override
    public Object getClientGUI(int aID, InventoryPlayer aPlayerInventory, IGregTechTileEntity aBaseMetaTileEntity) {
        return new GT_GUIContainer_BasicTank(aPlayerInventory, aBaseMetaTileEntity, getLocalName());
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
    public boolean displaysItemStack() {
        return true;
    }

    @Override
    public boolean displaysStackSize() {
        return true;
    }

    @Override
    public void onPostTick(IGregTechTileEntity aBaseMetaTileEntity, long aTick) {

        super.onPostTick(aBaseMetaTileEntity, aTick);

        if (this.getBaseMetaTileEntity().isServerSide()) {

            if (!((this.getBaseMetaTileEntity().isAllowedToWork()) && (GT_Utility.areStacksEqual(mInventory[0], CppItems.itemSpadeStack) || GT_Utility.areStacksEqual(mInventory[0], ic2.core.Ic2Items.weedingTrowel)) && (this.getFluid().amount >= 20) && (this.getBaseMetaTileEntity().isUniversalEnergyStored(GT_MetaTileEntity_Pump.getEuUsagePerTier(this.mTier)))))
                return;

            this.getBaseMetaTileEntity().decreaseStoredEnergyUnits(GT_MetaTileEntity_Pump.getEuUsagePerTier(this.mTier), true);
            this.getFluid().amount -= 20;

            int xmin = this.getBaseMetaTileEntity().getXCoord() > 0 ? this.getBaseMetaTileEntity().getXCoord() - this.mTier : this.getBaseMetaTileEntity().getXCoord() + this.mTier;
            int xmax = this.getBaseMetaTileEntity().getXCoord() > 0 ? this.getBaseMetaTileEntity().getXCoord() + this.mTier : this.getBaseMetaTileEntity().getXCoord() - this.mTier;

            int zmin = this.getBaseMetaTileEntity().getZCoord() > 0 ? this.getBaseMetaTileEntity().getZCoord() - this.mTier : this.getBaseMetaTileEntity().getZCoord() + this.mTier;
            int zmax = this.getBaseMetaTileEntity().getZCoord() > 0 ? this.getBaseMetaTileEntity().getZCoord() + this.mTier : this.getBaseMetaTileEntity().getZCoord() - this.mTier;

            for (int x = xmin; x <= xmax; ++x) {
                for (int z = zmin; z <= zmax; ++z) {

                    TileEntity possibleCrop = this.getBaseMetaTileEntity().getWorld().getTileEntity(x, this.getBaseMetaTileEntity().getYCoord(), z);

                    if (!(possibleCrop instanceof TileEntityCrop))
                        continue;

                    TileEntityCrop cropTE = (TileEntityCrop) possibleCrop;

                    if (cropTE.getCrop() == null)
                        continue;

                    if (cropTE.getCrop().tier() <= 0)
                        cropTE.reset();

                    cropTE.weedlevel = 0;
                    cropTE.updateState();
                    cropTE.markDirty();

                }
            }
        }
    }

    @Override
    public boolean onRightclick(IGregTechTileEntity aBaseMetaTileEntity, EntityPlayer aPlayer) {
        if (aBaseMetaTileEntity.isClientSide()) return true;
        aBaseMetaTileEntity.openGUI(aPlayer);
        return true;
    }

    @Override
    public boolean isSimpleMachine() {
        return false;
    }

    @Override
    public boolean isOverclockerUpgradable() {
        return false;
    }

    @Override
    public boolean isTransformerUpgradable() {
        return false;
    }

    @Override
    public boolean isElectric() {
        return true;
    }

    @Override
    public boolean isFacingValid(byte aFacing) {
        return true;
    }

    @Override
    public boolean isEnetInput() {
        return true;
    }

    @Override
    public boolean isInputFacing(byte aSide) {
        return true;
    }

    @Override
    public boolean isOutputFacing(byte aSide) {
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
    public int getTankPressure() {
        return 100;
    }

    @Override
    public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, byte aSide, byte aFacing, byte aColorIndex, boolean aActive, boolean aRedstone) {
        return new ITexture[]{Textures.BlockIcons.MACHINE_CASINGS[mTier][aColorIndex + 1], (aSide == 0 || aSide == 1) ? new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_PIPE_OUT) : new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ADV_PUMP)};
    }

    @Override
    public ITexture[] getTexturesActive(ITexture aBaseTexture) {
        return getTexturesInactive(aBaseTexture);
    }

    @Override
    public ITexture[] getTexturesInactive(ITexture aBaseTexture) {
        return new ITexture[]{
                new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ADV_PUMP), new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ADV_PUMP),
                new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ADV_PUMP), new GT_RenderedTexture(Textures.BlockIcons.OVERLAY_ADV_PUMP),};
    }

    protected FakePlayer getFakePlayer(IGregTechTileEntity aBaseTile) {
        if (mFakePlayer == null) mFakePlayer = GT_Utility.getFakePlayer(aBaseTile);
        mFakePlayer.setWorld(aBaseTile.getWorld());
        mFakePlayer.setPosition(aBaseTile.getXCoord(), aBaseTile.getYCoord(), aBaseTile.getZCoord());
        return mFakePlayer;
    }

}
