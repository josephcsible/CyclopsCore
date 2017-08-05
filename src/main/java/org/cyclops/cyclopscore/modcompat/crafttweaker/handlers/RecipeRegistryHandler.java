package org.cyclops.cyclopscore.modcompat.crafttweaker.handlers;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import org.cyclops.cyclopscore.CyclopsCore;
import org.cyclops.cyclopscore.recipe.custom.api.*;

/**
 * Main handler for the Cyclops {@link org.cyclops.cyclopscore.recipe.custom.api.IRecipeRegistry}.
 * @author rubensworks
 */
public abstract class RecipeRegistryHandler
        <M extends IMachine<M, I, O, P>, I extends IRecipeInput, O extends IRecipeOutput, P extends IRecipeProperties> {

    protected abstract M getMachine();
    protected abstract String getRegistryName();

    public void add(IRecipe<I, O, P> recipe) {
        CraftTweakerAPI.apply(new RecipeRegistryAddition<>(getRegistryName(), getMachine(), recipe));
    }

    public void remove(IRecipe<I, O, P> recipe) {
        CraftTweakerAPI.apply(new RecipeRegistryRemoval<>(getRegistryName(), getMachine(), recipe));
    }

    public void remove(O output) {
        CraftTweakerAPI.apply(new RecipeRegistryRemoval<>(getRegistryName(), getMachine(), output));
    }

    public static ItemStack toStack(IItemStack stack) {
        if (stack == null) {
            return null;
        } else {
            Object internal = stack.getInternal();
            if (!(internal instanceof ItemStack)) {
                CyclopsCore.clog("Not a valid item stack: " + stack);
                return null;
            }
            return (ItemStack) internal;
        }
    }

    public static FluidStack toFluid(ILiquidStack stack) {
        if (stack == null) {
            return null;
        } else {
            return FluidRegistry.getFluidStack(stack.getName(), stack.getAmount());
        }
    }

}
