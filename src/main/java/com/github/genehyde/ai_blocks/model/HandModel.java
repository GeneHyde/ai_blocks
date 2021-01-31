package com.github.genehyde.ai_blocks.model;// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class HandModel<T extends Entity> extends SegmentedModel<T> {
	private final ModelRenderer master;
	private final ModelRenderer index1;
	private final ModelRenderer index2;
	private final ModelRenderer index3;
	private final ModelRenderer middle1;
	private final ModelRenderer middle2;
	private final ModelRenderer middle3;
	private final ModelRenderer pinky1;
	private final ModelRenderer pinky2;
	private final ModelRenderer pinky3;
	private final ModelRenderer thumb1;
	private final ModelRenderer thumb2;
	private final ModelRenderer thumb3;
	private final ModelRenderer palm;
	private final ModelRenderer wrist;

	public HandModel() {
		textureWidth = 128;
		textureHeight = 128;

		master = new ModelRenderer(this);
		master.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		index1 = new ModelRenderer(this);
		index1.setRotationPoint(-5.0F, -14.0F, -8.0F);
		master.addChild(index1);
		index1.setTextureOffset(17, 46).addBox(-2.0F, -2.0F, -8.0F, 4.0F, 4.0F, 8.0F, 0.0F, false);

		index2 = new ModelRenderer(this);
		index2.setRotationPoint(0.0F, 1.0F, -8.0F);
		index1.addChild(index2);
		index2.setTextureOffset(13, 59).addBox(-2.0F, -1.0F, -4.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		index3 = new ModelRenderer(this);
		index3.setRotationPoint(0.0F, 5.0F, -3.0F);
		index2.addChild(index3);
		index3.setTextureOffset(49, 0).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		middle1 = new ModelRenderer(this);
		middle1.setRotationPoint(1.0F, -14.0F, -8.0F);
		master.addChild(middle1);
		middle1.setTextureOffset(0, 34).addBox(-2.0F, -2.0F, -8.0F, 4.0F, 4.0F, 8.0F, 0.0F, false);

		middle2 = new ModelRenderer(this);
		middle2.setRotationPoint(0.0F, 1.0F, -8.0F);
		middle1.addChild(middle2);
		middle2.setTextureOffset(58, 39).addBox(-2.0F, -1.0F, -4.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		middle3 = new ModelRenderer(this);
		middle3.setRotationPoint(0.0F, 5.0F, -3.0F);
		middle2.addChild(middle3);
		middle3.setTextureOffset(0, 47).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		pinky1 = new ModelRenderer(this);
		pinky1.setRotationPoint(7.0F, -14.0F, -8.0F);
		master.addChild(pinky1);
		pinky1.setTextureOffset(33, 33).addBox(-2.0F, -2.0F, -8.0F, 4.0F, 4.0F, 8.0F, 0.0F, false);

		pinky2 = new ModelRenderer(this);
		pinky2.setRotationPoint(0.0F, 1.0F, -8.0F);
		pinky1.addChild(pinky2);
		pinky2.setTextureOffset(55, 55).addBox(-2.0F, -1.0F, -4.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		pinky3 = new ModelRenderer(this);
		pinky3.setRotationPoint(0.0F, 5.0F, -3.0F);
		pinky2.addChild(pinky3);
		pinky3.setTextureOffset(42, 46).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		thumb1 = new ModelRenderer(this);
		thumb1.setRotationPoint(-7.0F, -14.0F, -1.0F);
		master.addChild(thumb1);
		thumb1.setTextureOffset(30, 59).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		thumb2 = new ModelRenderer(this);
		thumb2.setRotationPoint(-4.0F, 2.0F, 0.0F);
		thumb1.addChild(thumb2);
		thumb2.setTextureOffset(54, 28).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		thumb3 = new ModelRenderer(this);
		thumb3.setRotationPoint(0.0F, 6.0F, 0.0F);
		thumb2.addChild(thumb3);
		thumb3.setTextureOffset(41, 21).addBox(-4.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		palm = new ModelRenderer(this);
		palm.setRotationPoint(1.0F, -16.0F, 0.0F);
		master.addChild(palm);
		palm.setTextureOffset(0, 0).addBox(-8.0F, 0.0F, -8.0F, 16.0F, 4.0F, 16.0F, 0.0F, false);

		wrist = new ModelRenderer(this);
		wrist.setRotationPoint(0.0F, 0.0F, 8.0F);
		palm.addChild(wrist);
		wrist.setTextureOffset(0, 21).addBox(-8.0F, -8.0F, -3.0F, 16.0F, 8.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		master.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public Iterable<ModelRenderer> getParts() {
		return ImmutableList.of(this.master, this.index1, this.index2, this.index3, this.middle1,
				this.middle2, this.middle3, this.pinky1, this.pinky2, this.pinky3, this.thumb1,
				this.thumb2, this.thumb3, this.palm, this.wrist);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}