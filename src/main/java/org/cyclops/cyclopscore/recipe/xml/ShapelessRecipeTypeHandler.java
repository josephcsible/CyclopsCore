package org.cyclops.cyclopscore.recipe.xml;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.cyclops.cyclopscore.helper.CraftingHelpers;
import org.cyclops.cyclopscore.init.RecipeHandler;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Handler for shapeless recipes.
 * @author rubensworks
 *
 */
public class ShapelessRecipeTypeHandler extends GridRecipeTypeHandler {

	@Override
	protected NonNullList<Ingredient> handleIO(RecipeHandler recipeHandler, Element input, ItemStack output)
			throws XmlRecipeLoader.XmlRecipeException {
		NonNullList<Ingredient> inputs = NonNullList.create();
		NodeList inputNodes = input.getElementsByTagName("item");
		for(int i = 0; i < inputNodes.getLength(); i++) {
			inputs.add(getIngredient(recipeHandler, inputNodes.item(i)));
		}

		// Register with the recipe
		ResourceLocation id = CraftingHelpers.newRecipeIdentifier(output);
		CraftingHelpers.registerRecipe(id, new ShapelessOreRecipe(id, inputs, output));
		return inputs;
	}

}
