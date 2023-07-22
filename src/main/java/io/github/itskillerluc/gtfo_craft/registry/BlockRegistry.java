package io.github.itskillerluc.gtfo_craft.registry;

import io.github.itskillerluc.gtfo_craft.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialTransparent;
import net.minecraftforge.event.RegistryEvent;

public class BlockRegistry {
    public static final Material FOG_MATERIAL = new MaterialTransparent(MapColor.AIR);
    public static final BlockFog FOG = new BlockFog();
    public static final BlockFogEmpty EMPTY_FOG = new BlockFogEmpty();
    public static final Block TURRET_SLOW = new BlockTurret(Material.IRON, MapColor.IRON, "turret_slow", 8, 40, 10).setBlockUnbreakable();
    public static final Block TURRET_MEDIUM = new BlockTurret(Material.IRON, MapColor.IRON, "turret_medium", 5, 25, 10).setBlockUnbreakable();
    public static final Block TURRET_FAST = new BlockTurret(Material.IRON, MapColor.IRON, "turret_fast", 2, 10, 10).setBlockUnbreakable();
    public static final BlockBreakableDoor BREAKABLE_DOOR = new BlockBreakableDoor(Material.WOOD);
    public static final BlockGlowStick GLOW_STICK_BLOCK = new BlockGlowStick();
    public static final BlockGenerator GENERATOR = new BlockGenerator(Material.IRON);

    public static void registerBlocks(RegistryEvent.Register<Block> registryEvent) {
        registryEvent.getRegistry().register(FOG);
        registryEvent.getRegistry().register(EMPTY_FOG);
        registryEvent.getRegistry().register(GLOW_STICK_BLOCK);
        registryEvent.getRegistry().register(TURRET_SLOW);
        registryEvent.getRegistry().register(TURRET_MEDIUM);
        registryEvent.getRegistry().register(TURRET_FAST);
        registryEvent.getRegistry().register(BREAKABLE_DOOR);
        registryEvent.getRegistry().register(GENERATOR);
    }
}
