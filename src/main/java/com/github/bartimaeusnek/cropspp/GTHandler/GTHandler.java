package com.github.bartimaeusnek.cropspp.GTHandler;

public class GTHandler implements Runnable {

    private static Runnable MachineReps;
    private static GTCraftingRecipeLoader CraftingReps;

    public GTHandler() {
        super();
        MachineReps = new GTNHMachineRecipeLoader();
        CraftingReps = new GTCraftingRecipeLoader();
    }

    @Override
    public void run() {
        MachineReps.run();
        CraftingReps.run();
    }
}
