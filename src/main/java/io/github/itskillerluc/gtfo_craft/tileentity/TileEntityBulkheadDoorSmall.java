package io.github.itskillerluc.gtfo_craft.tileentity;

import io.github.itskillerluc.gtfo_craft.block.BlockBulkheadDoorSmallController;
import io.github.itskillerluc.gtfo_craft.block.BlockBulkheadDoorSmallHelper;
import io.github.itskillerluc.gtfo_craft.registry.BlockRegistry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class TileEntityBulkheadDoorSmall extends TileEntity implements IAnimatable {
    private final AnimationFactory manager = new AnimationFactory(this);

    private <E extends TileEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (world.getBlockState(pos).getValue(BlockBulkheadDoorSmallController.OPEN) && event.getController().isJustStarting) {
            event.getController().setAnimation(new AnimationBuilder().playAndHold("open"));
        } else if (world.getBlockState(pos).getValue(BlockBulkheadDoorSmallController.OPEN) && event.getController().getAnimationState() == AnimationState.Stopped) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("idle_open"));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.manager;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

    public void open() {
        IBlockState controller = world.getBlockState(pos);
        EnumFacing facing = controller.getValue(BlockBulkheadDoorSmallController.FACING);

        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j <= 2; j++) {
                BlockPos xz = pos.offset(facing, -j);
                BlockPos y = pos.offset(EnumFacing.UP, i);
                if (!world.getBlockState(pos).getBlock().equals(BlockRegistry.BULKHEAD_DOOR_SMALL_CONTROLLER)) continue;
                boolean eastWest = facing == EnumFacing.EAST || facing == EnumFacing.WEST;
                BlockPos blockPos = new BlockPos(eastWest ? xz.getX() : pos.getX(), y.getY(), eastWest ? pos.getZ() : xz.getZ());
                if (world.getBlockState(blockPos).getBlock().equals(BlockRegistry.BULKHEAD_DOOR_SMALL_CONTROLLER)) continue;
                TileEntityBulkheadDoorHelper.Location location;
                world.setBlockState(blockPos, world.getBlockState(blockPos).withProperty(BlockBulkheadDoorSmallHelper.OPEN, true).withProperty(BlockBulkheadDoorSmallHelper.POWERED, true));
                ((TileEntityBulkheadDoorHelper) world.getTileEntity(blockPos)).master = this.pos;
                if (j == 2 && i == 2) {
                    location = TileEntityBulkheadDoorHelper.Location.CORNERR;
                } else if (j == 2) {
                    location = TileEntityBulkheadDoorHelper.Location.RIGHT;
                } else if (j == 0 && i == 2) {
                    location = TileEntityBulkheadDoorHelper.Location.CORNERL;
                } else if (j == 0) {
                    location = TileEntityBulkheadDoorHelper.Location.LEFT;
                } else if (i == 2) {
                    location = TileEntityBulkheadDoorHelper.Location.TOP;
                } else {
                    location = TileEntityBulkheadDoorHelper.Location.CENTER;
                }
                ((TileEntityBulkheadDoorHelper) world.getTileEntity(new BlockPos(eastWest ? xz.getX() : pos.getX(), y.getY(), eastWest ? pos.getZ() : xz.getZ()))).setLocation(location);
            }
        }
        world.setBlockState(pos, world.getBlockState(pos).withProperty(BlockBulkheadDoorSmallController.OPEN, true));
    }
}
