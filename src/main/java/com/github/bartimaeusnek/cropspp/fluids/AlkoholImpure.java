package com.github.bartimaeusnek.cropspp.fluids;

import static gregtech.api.enums.GTValues.RES_PATH_BLOCK;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import gregtech.api.GregTechAPI;

public class AlkoholImpure extends Fluid implements Runnable {

    public long percentage;
    private String texture;

    public AlkoholImpure(String name, long d, String texture) {
        super(name);
        this.percentage = d;
        this.texture = texture;
        this.viscosity = 1125;
        this.isGaseous = false;
        this.temperature = 295;
        this.density = this.viscosity;
        GregTechAPI.sGTBlockIconload.add(this);
        FluidRegistry.registerFluid(this);
    }

    @Override
    public void run() {
        setIcons(GregTechAPI.sBlockIcons.registerIcon(RES_PATH_BLOCK + "fluids/fluid." + texture));
    }

    public long getPercentage() {
        return percentage;
    }
}
