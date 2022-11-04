package com.github.bartimaeusnek.cropspp.GTHandler;

import com.github.bartimaeusnek.croploadcore.ModsLoaded;

public class GTHandler implements Runnable {

    private static Runnable MachineReps;
    private static GTCraftingRecipeLoader CraftingReps;

    public GTHandler() {
        super();
        if (ModsLoaded.dreamcraft) MachineReps = new GTNHMachineRecipeLoader();
        else MachineReps = new GTVanillaMachineRecipeLoader();
        CraftingReps = new GTCraftingRecipeLoader();
    }

    @Override
    public void run() {
        MachineReps.run();
        CraftingReps.run();
    }
}
