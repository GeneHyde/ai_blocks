package com.github.genehyde.ai_blocks.init;

import com.github.genehyde.ai_blocks.entity.HandEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;

public class ModEntities {
    public static EntityType<HandEntity> HAND_ENTITY = EntityType
            .Builder.<HandEntity>create(HandEntity::new, EntityClassification.MISC)
            .setShouldReceiveVelocityUpdates(true)
            .setUpdateInterval(1).setTrackingRange(128).size(1F, 1F)
            .build("element:hand");
}
