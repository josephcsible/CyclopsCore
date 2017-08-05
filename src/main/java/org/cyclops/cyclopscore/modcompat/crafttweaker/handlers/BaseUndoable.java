package org.cyclops.cyclopscore.modcompat.crafttweaker.handlers;


import crafttweaker.IAction;

/**
 * Taken from jared's MTlib
 */
public abstract class BaseUndoable implements IAction {

    // Holds the name of the mod / machine this action manipulates
    protected final String name;

    // Basic indicator, if the action was successful and can be reverted
    protected boolean success = false;

    protected BaseUndoable(String name) {
        this.name = name;
    }

    protected String getRecipeInfo() {
        return "Unknown item";
    }

    @Override
    public String describe() {
        return String.format("Altering %s Recipe(s) for %s", this.name, this.getRecipeInfo());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;

        if(!(obj instanceof BaseUndoable)) {
            return false;
        }

        BaseUndoable u = (BaseUndoable) obj;

        if(!name.equals(u.name))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
