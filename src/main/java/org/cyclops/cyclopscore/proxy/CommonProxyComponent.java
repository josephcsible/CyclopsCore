package org.cyclops.cyclopscore.proxy;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.cyclops.cyclopscore.event.ConfigChangedEventHook;
import org.cyclops.cyclopscore.init.ModBase;

/**
 * Base proxy for server and client side.
 * @author rubensworks
 *
 */
public abstract class CommonProxyComponent implements ICommonProxy {
	
	protected static final String DEFAULT_RESOURCELOCATION_MOD = "minecraft";

    protected abstract ModBase getMod();

    @Override
    public void registerRenderer(Class<? extends Entity> clazz, Render renderer) {
        throw new IllegalArgumentException("Registration of renderers should not be called server side!");
    }

    @Override
    public void registerRenderer(Class<? extends TileEntity> clazz, TileEntitySpecialRenderer renderer) {
        throw new IllegalArgumentException("Registration of renderers should not be called server side!");
    }

    @Override
    public void registerRenderers() {
        // Nothing here as the server doesn't render graphics!
    }

    @Override
    public void registerKeyBindings() {
    }

    @Override
    public void registerPacketHandlers() {
    	// TODO: packet handler
    }

    @Override
    public void registerTickHandlers() {
        
    }

    @Override
    public void registerEventHooks() {
        FMLCommonHandler.instance().bus().register(new ConfigChangedEventHook(getMod()));
    }

    @Override
    public void playSoundMinecraft(BlockPos pos, String sound, float volume, float frequency) {
    	playSoundMinecraft(pos.getX(), pos.getY(), pos.getZ(), sound, volume, frequency);
    }

    @Override
    public void playSoundMinecraft(double x, double y, double z, String sound, float volume, float frequency) {
    	playSound(x, y, z, sound, volume, frequency, DEFAULT_RESOURCELOCATION_MOD);
    }

    @Override
    public void playSound(double x, double y, double z, String sound, float volume, float frequency,
    		String mod) {
    	// No implementation server-side.
    }

    @Override
    public void playSound(double x, double y, double z, String sound, float volume, float frequency) {
    	playSound(x, y, z, sound, volume, frequency, getMod().getModId());
    }

    @Override
    public void sendSoundMinecraft(BlockPos pos, String sound, float volume, float frequency) {
		sendSound(pos.getX(), pos.getY(), pos.getZ(), sound, volume, frequency, DEFAULT_RESOURCELOCATION_MOD);
    }

    @Override
    public void sendSoundMinecraft(double x, double y, double z, String sound, float volume, float frequency) {
		sendSound(x, y, z, sound, volume, frequency, DEFAULT_RESOURCELOCATION_MOD);
    }

    @Override
    public void sendSound(double x, double y, double z, String sound, float volume, float frequency,
    		String mod) {
        // TODO
    	//SoundPacket packet = new SoundPacket(x, y, z, sound, volume, frequency, mod);
		//PacketHandler.sendToServer(packet);
    }

    @Override
    public void sendSound(double x, double y, double z, String sound, float volume, float frequency) {
    	sendSound(x, y, z, sound, volume, frequency, getMod().getModId());
    }
}
