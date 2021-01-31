package com.github.genehyde.ai_blocks.entity;

import com.github.genehyde.ai_blocks.init.ModEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


/**
 * Monster Hand entity, used to test simple behavior changes
 */
public class HandEntity extends ProgrammableModEntity {

    public HandEntity(EntityType<? extends HandEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public HandEntity(World worldIn) {
        this(ModEntities.HAND_ENTITY, worldIn);
    }

    protected void registerGoals() {
        super.registerGoals();
//        this.applyEntityAI();
    }

    protected void applyEntityAI() {
//        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
    }

    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
//        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
//        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
    }

    protected int getExperiencePoints(PlayerEntity player) {
        return super.getExperiencePoints(player);
    }

//        public void livingTick() {
//            super.livingTick();
//        }

    public boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        Item item = itemstack.getItem();
        if (item instanceof SpawnEggItem && ((SpawnEggItem) item).hasType(itemstack.getTag(), this.getType())) {
            if (!this.world.isRemote) {
                HandEntity handEntity = (HandEntity) this.getType().create(this.world);
                if (handEntity != null) {
                    handEntity.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), 0.0F, 0.0F);
                    this.world.addEntity(handEntity);
                    if (itemstack.hasDisplayName()) {
                        handEntity.setCustomName(itemstack.getDisplayName());
                    }

                    if (!player.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }
                }
            }

            return true;
        } else {
            return super.processInteract(player, hand);
        }
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_ZOMBIE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ZOMBIE_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_ZOMBIE_STEP;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEAD;
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.93F;
    }

    public double getYOffset() {
        return 0.0D;
    }
}
