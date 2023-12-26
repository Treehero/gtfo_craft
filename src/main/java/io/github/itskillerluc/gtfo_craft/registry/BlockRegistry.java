package io.github.itskillerluc.gtfo_craft.registry;

import io.github.itskillerluc.gtfo_craft.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialTransparent;
import net.minecraftforge.event.RegistryEvent;

public class BlockRegistry {
    public static final Material FOG_MATERIAL = new MaterialTransparent(MapColor.AIR);
    public static final BlockFog FOG = new BlockFog();
    public static final BlockFogEmpty EMPTY_FOG = new BlockFogEmpty();
    public static final Block TURRET_SLOW = new BlockTurret(Material.CIRCUITS, MapColor.IRON, "turret_slow", 8, 40, 18);
    public static final Block TURRET_MEDIUM = new BlockTurret(Material.CIRCUITS, MapColor.IRON, "turret_medium", 5, 25, 18);
    public static final Block TURRET_FAST = new BlockTurret(Material.CIRCUITS, MapColor.IRON, "turret_fast", 2, 10, 18);
    public static final BlockGlowStick GLOW_STICK_BLOCK = new BlockGlowStick();
    public static final BlockGenerator GENERATOR = new BlockGenerator(Material.IRON);
    public static final Block BATTERY = new BlockBattery().setBlockUnbreakable();
    public static final BlockTripMine TRIP_MINE = new BlockTripMine(Material.IRON, MapColor.IRON);
    public static final Block SECURITY_DOOR_SMALL_CONTROLLER = new BlockSecurityDoorSmallController(Material.IRON, MapColor.IRON).setBlockUnbreakable();
    public static final Block SECURITY_DOOR_SMALL_HELPER = new BlockSecurityDoorSmallHelper(Material.IRON, MapColor.IRON).setBlockUnbreakable();
    public static final Block SECURITY_DOOR_LARGE_CONTROLLER = new BlockSecurityDoorLargeController(Material.IRON, MapColor.IRON).setBlockUnbreakable();
    public static final Block SECURITY_DOOR_LARGE_HELPER = new BlockSecurityDoorLargeHelper(Material.IRON, MapColor.IRON).setBlockUnbreakable();
    public static final Block POSSESSED_SECURITY_DOOR_LARGE_CONTROLLER = new BlockPossessedSecurityDoorLargeController(Material.IRON, MapColor.IRON).setBlockUnbreakable();
    public static final Block POSSESSED_SECURITY_DOOR_LARGE_HELPER = new BlockPossessedSecurityDoorLargeHelper(Material.IRON, MapColor.IRON).setBlockUnbreakable();
    public static final Block POSSESSED_SECURITY_DOOR_SMALL_CONTROLLER = new BlockPossessedSecurityDoorSmallController(Material.IRON, MapColor.IRON).setBlockUnbreakable();
    public static final Block POSSESSED_SECURITY_DOOR_SMALL_HELPER = new BlockPossessedSecurityDoorSmallHelper(Material.IRON, MapColor.IRON).setBlockUnbreakable();
    public static final Block APEX_DOOR_LARGE_CONTROLLER = new BlockApexDoorLargeController(Material.IRON, MapColor.IRON).setBlockUnbreakable();
    public static final Block APEX_DOOR_LARGE_HELPER = new BlockApexDoorLargeHelper(Material.IRON, MapColor.IRON).setBlockUnbreakable();
    public static final Block APEX_DOOR_SMALL_CONTROLLER = new BlockApexDoorSmallController(Material.IRON, MapColor.IRON).setBlockUnbreakable();
    public static final Block APEX_DOOR_SMALL_HELPER = new BlockApexDoorSmallHelper(Material.IRON, MapColor.IRON).setBlockUnbreakable();
    public static final Block BULKHEAD_DOOR_LARGE_CONTROLLER = new BlockBulkheadDoorLargeController(Material.IRON, MapColor.IRON).setBlockUnbreakable();
    public static final Block BULKHEAD_DOOR_LARGE_HELPER = new BlockBulkheadDoorLargeHelper(Material.IRON, MapColor.IRON).setBlockUnbreakable();
    public static final Block BULKHEAD_DOOR_SMALL_CONTROLLER = new BlockBulkheadDoorSmallController(Material.IRON, MapColor.IRON).setBlockUnbreakable();
    public static final Block BULKHEAD_DOOR_SMALL_HELPER = new BlockBulkheadDoorSmallHelper(Material.IRON, MapColor.IRON).setBlockUnbreakable();
    public static final Block COMMON_DOOR_SMALL_CONTROLLER = new BlockCommonDoorSmallController(Material.IRON, MapColor.IRON).setBlockUnbreakable();
    public static final Block COMMON_DOOR_SMALL_HELPER = new BlockCommonDoorSmallHelper(Material.IRON, MapColor.IRON).setBlockUnbreakable();
    public static final Block COMMON_DOOR_LARGE_CONTROLLER = new BlockCommonDoorLargeController(Material.IRON, MapColor.IRON).setBlockUnbreakable();
    public static final Block COMMON_DOOR_LARGE_HELPER = new BlockCommonDoorLargeHelper(Material.IRON, MapColor.IRON).setBlockUnbreakable();
    public static final BlockCocoon COCOON = new BlockCocoon(Material.WEB, MapColor.WHITE_STAINED_HARDENED_CLAY);
    public static final BlockFogTemporary FOG_TEMPORARY = new BlockFogTemporary();
    public static final Block TERMINAL = new BlockTerminal(Material.IRON).setBlockUnbreakable();

    public static void registerBlocks(RegistryEvent.Register<Block> registryEvent) {
        registryEvent.getRegistry().register(FOG);
        registryEvent.getRegistry().register(EMPTY_FOG);
        registryEvent.getRegistry().register(GLOW_STICK_BLOCK);
        registryEvent.getRegistry().register(TURRET_SLOW);
        registryEvent.getRegistry().register(TURRET_MEDIUM);
        registryEvent.getRegistry().register(TURRET_FAST);
        registryEvent.getRegistry().register(COMMON_DOOR_SMALL_CONTROLLER);
        registryEvent.getRegistry().register(COMMON_DOOR_SMALL_HELPER);
        registryEvent.getRegistry().register(GENERATOR);
        registryEvent.getRegistry().register(BATTERY);
        registryEvent.getRegistry().register(TRIP_MINE);
        registryEvent.getRegistry().register(SECURITY_DOOR_SMALL_CONTROLLER);
        registryEvent.getRegistry().register(SECURITY_DOOR_SMALL_HELPER);
        registryEvent.getRegistry().register(SECURITY_DOOR_LARGE_CONTROLLER);
        registryEvent.getRegistry().register(SECURITY_DOOR_LARGE_HELPER);
        registryEvent.getRegistry().register(COMMON_DOOR_LARGE_CONTROLLER);
        registryEvent.getRegistry().register(COMMON_DOOR_LARGE_HELPER);
        registryEvent.getRegistry().register(COCOON);
        registryEvent.getRegistry().register(FOG_TEMPORARY);
        registryEvent.getRegistry().register(POSSESSED_SECURITY_DOOR_LARGE_CONTROLLER);
        registryEvent.getRegistry().register(POSSESSED_SECURITY_DOOR_LARGE_HELPER);
        registryEvent.getRegistry().register(POSSESSED_SECURITY_DOOR_SMALL_CONTROLLER);
        registryEvent.getRegistry().register(POSSESSED_SECURITY_DOOR_SMALL_HELPER);
        registryEvent.getRegistry().register(APEX_DOOR_LARGE_CONTROLLER);
        registryEvent.getRegistry().register(APEX_DOOR_LARGE_HELPER);
        registryEvent.getRegistry().register(APEX_DOOR_SMALL_CONTROLLER);
        registryEvent.getRegistry().register(APEX_DOOR_SMALL_HELPER);
        registryEvent.getRegistry().register(BULKHEAD_DOOR_LARGE_CONTROLLER);
        registryEvent.getRegistry().register(BULKHEAD_DOOR_LARGE_HELPER);
        registryEvent.getRegistry().register(BULKHEAD_DOOR_SMALL_CONTROLLER);
        registryEvent.getRegistry().register(BULKHEAD_DOOR_SMALL_HELPER);
        registryEvent.getRegistry().register(TERMINAL);
    }
}
