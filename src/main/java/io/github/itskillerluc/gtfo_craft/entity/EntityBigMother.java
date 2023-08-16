package io.github.itskillerluc.gtfo_craft.entity;

import io.github.itskillerluc.gtfo_craft.registry.BlockRegistry;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityBigMother extends ModEntity implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    private static final DataParameter<Boolean> ATTACKING =
            EntityDataManager.createKey(EntityBigMother.class, DataSerializers.BOOLEAN);

    private static final int SUMMON_TIME_START = 400;
    private static final int SUMMON_TIME_FINISH = 500;
    private static final int SUMMON_COOLDOWN = 15;
    private int time = 0;
    private static final int SMOKE_RADIUS = 4;

    public EntityBigMother(World worldIn) {
        super(worldIn);
        setSize(1.2f, 3.5f);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(ATTACKING, true);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        if (this.dataManager.get(ATTACKING)) {
            compound.setBoolean("attacking", true);
        }
        compound.setInteger("time", time);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.dataManager.set(ATTACKING, compound.getBoolean("attacking"));
        this.time = compound.getInteger("time");
    }

    public boolean isAttacking(){
        return this.dataManager.get(ATTACKING);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(6, new EntityAIAvoidEntity<>(this, EntityPlayer.class, entity -> getAttackTarget() != null && getAttackTarget().isEntityEqual(entity), 17, 1.3, 1.5));

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(75D);
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        AnimationBuilder builder = new AnimationBuilder();
        boolean cont = false;
        if (event.isMoving() && !dataManager.get(ATTACKING)) {
            builder.addAnimation("animation.big_mother.walk", ILoopType.EDefaultLoopTypes.LOOP);
            cont = true;
        }

        if (dataManager.get(ATTACKING)) {
            builder.addAnimation("animation.big_mother.attack", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
            cont = true;
        }
        event.getController().setAnimation(builder);
        return cont ? PlayState.CONTINUE : PlayState.STOP;
    }
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!world.isRemote && getAttackTarget() != null) {
            time++;
            if (time == SUMMON_TIME_START) {
                for (int x = -SMOKE_RADIUS; x < SMOKE_RADIUS; x++) {
                    for (int y = -SMOKE_RADIUS; y < SMOKE_RADIUS; y++) {
                        for (int z = -SMOKE_RADIUS; z < SMOKE_RADIUS; z++) {
                            if (world.getBlockState(new BlockPos(new BlockPos(this.posX + x, this.posY + y, this.posZ + z))).getMaterial().isReplaceable()) {
                                world.setBlockState(new BlockPos(new BlockPos(this.posX + x, this.posY + y, this.posZ + z)), BlockRegistry.FOG_TEMPORARY.getDefaultState());
                            }
                        }
                    }
                }
            }
            if (time >= SUMMON_TIME_START) {
                if (time % SUMMON_COOLDOWN == 0) {
                    EntityBaby entity = new EntityBaby(world);
                    entity.setPosition(this.posX, this.posY, this.posZ);
                    world.spawnEntity(entity);
                }
                if (time >= SUMMON_TIME_FINISH) {
                    time = 0;
                }
            }
        }
    }
}
