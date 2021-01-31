package com.github.genehyde.ai_blocks.render;

import com.github.genehyde.ai_blocks.entity.HandEntity;
import com.github.genehyde.ai_blocks.model.HandModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HandRender extends MobRenderer<HandEntity, HandModel<HandEntity>> {
    private static final Logger LOGGER = LogManager.getLogger();

    public HandRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HandModel<>(), 1F);
    }

    @Override
    public ResourceLocation getEntityTexture(HandEntity entity) {
        return new ResourceLocation(com.github.genehyde.ai_blocks.Main.MODID, "textures/entity/hand.png");
    }

    public static class RenderHandFactory implements IRenderFactory<HandEntity> {
        public static final RenderHandFactory INSTANCE = new RenderHandFactory();

        @Override
        public EntityRenderer<? super HandEntity> createRenderFor(EntityRendererManager manager) {
            return new HandRender(manager);
        }
    }
}
