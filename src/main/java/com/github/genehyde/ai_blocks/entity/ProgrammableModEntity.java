package com.github.genehyde.ai_blocks.entity;

import com.github.genehyde.ai_blocks.network.datasync.ModDataSerializers;
import com.github.genehyde.ai_blocks.util.ProgrammableEntityGoalParser;
import com.mojang.datafixers.Dynamic;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTDynamicOps;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class ProgrammableModEntity extends CreatureEntity {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final DataParameter<SimpleProgrammableEntityData> GOAL_DATA;

    protected ProgrammableModEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        this.setGoalData(this.getGoalData());
    }

    @Override
    protected void registerGoals() {
        SimpleProgrammableEntityData data = getGoalData();
        if (data != null) {
            setProgrammableTargetsInternal(data.getGoals());
        }
    }

    public void setGoalData(SimpleProgrammableEntityData goalData) {
        this.dataManager.set(GOAL_DATA, goalData);
    }

    public SimpleProgrammableEntityData getGoalData() {
        return this.dataManager.get(GOAL_DATA);
    }

    public void setProgrammableTargets(Map<Integer, List<String>> goalMap) {
        if (goalMap == null) {
            return;
        }

        setProgrammableTargetsInternal(goalMap);

        setGoalData(new SimpleProgrammableEntityData(goalMap));
    }

    public void setProgrammableTargetsInternal(Map<Integer, List<String>> goalMap) {
        if (goalMap == null) {
            return;
        }

        for (Map.Entry<Integer, List<String>> entry: goalMap.entrySet()) {
            Integer priority = entry.getKey();
            List<Goal> goals = ProgrammableEntityGoalParser.parse(this, entry.getValue());
            for (Goal goal: goals) {
                this.targetSelector.addGoal(priority, goal);
            }
        }
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(GOAL_DATA, new SimpleProgrammableEntityData(Collections.emptyMap()));
    }


    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.put("GoalData", this.getGoalData().serialize(NBTDynamicOps.INSTANCE));
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("GoalData")) {
            this.setGoalData(new SimpleProgrammableEntityData(new Dynamic<>(NBTDynamicOps.INSTANCE, compound.get("GoalData"))));
            registerGoals();
        }
    }

    static {
        GOAL_DATA = EntityDataManager.createKey(ProgrammableModEntity.class, ModDataSerializers.GOAL_DATA);
    }
}
