package com.github.bartimaeusnek.cropspp.fluids;

import gregtech.api.enums.Mods;
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
        String ResourcePath = Mods.GregTech.getResourceLocation("fluids/fluid." + texture).toString();
        setIcons(GregTechAPI.sBlockIcons.registerIcon(ResourcePath));
    }

    public long getPercentage() {
        return percentage;
    }
}
