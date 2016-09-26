/*
 * MIT License
 *
 * Copyright (c) 2016 secknv
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */


package net.secknv.scomp.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.secknv.scomp.block.BlockCoil;
import net.secknv.scomp.reference.Reference;

import javax.annotation.Nullable;
import java.util.List;

public class ItemResonantCompass extends Item {

    private String name = "resonant_compass";

    public ItemResonantCompass() {
        super();
        this.setRegistryName(name);
        this.setUnlocalizedName(Reference.MOD_ID + "." + name);
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.TOOLS);

        this.addPropertyOverride(new ResourceLocation("scomp", "angle"), new IItemPropertyGetter() {

            @SideOnly(Side.CLIENT)
            double rotation;
            @SideOnly(Side.CLIENT)
            double rota;
            @SideOnly(Side.CLIENT)
            long lastUpdateTick;
            @SideOnly(Side.CLIENT)

            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {

                if (entityIn == null && !stack.isOnItemFrame()) {

                    return 0.0F;
                }
                else {

                    boolean flag = entityIn != null;
                    Entity entity = flag ? entityIn : stack.getItemFrame();

                    if (worldIn == null) {

                        worldIn = entity.worldObj;
                    }

                    double d0 = 0;

                    double d1 = flag ? (double)entity.rotationYaw : this.getFrameRotation((EntityItemFrame)entity);
                    d1 = (double)MathHelper.positiveModulo( (float)d1 / 360F, 1.0F );

                    if (!(stack.getTagCompound() == null)) {
                        if(stack.getTagCompound().hasKey("pos")) {
                            BlockPos blockpos = NBTUtil.getPosFromTag(stack.getTagCompound().getCompoundTag("pos"));
                            if(worldIn.getBlockState(blockpos).getValue(BlockCoil.ENABLED)) {
                                double d2 = (Math.atan2((double) blockpos.getZ() - entity.posZ, (double) blockpos.getX() - entity.posX)) / (Math.PI * 2D);
                                d0 = 0.5D - ((d1 - 0.25D) - d2);
                            }
                        }
                    }



                    if (flag) {

                        d0 = this.wobble(worldIn, d0);
                    }

                    return MathHelper.positiveModulo((float)d0, 1.0F);
                }
            }

            @SideOnly(Side.CLIENT)
            private double wobble(World worldIn, double num) {

                if (worldIn.getTotalWorldTime() != this.lastUpdateTick) {

                    this.lastUpdateTick = worldIn.getTotalWorldTime();
                    double d0 = num - this.rotation;
                    d0 = (double)MathHelper.positiveModulo( (float)d0 + 0.5F, 1.0F ) - 0.5D;
                    this.rota += d0 * 0.1D;
                    this.rota *= 0.8D;
                    this.rotation += this.rota;
                    this.rotation = (double)MathHelper.positiveModulo( (float)this.rotation, 1.0F );
                }

                return this.rotation;
            }

            @SideOnly(Side.CLIENT)
            private double getFrameRotation(EntityItemFrame frame) {

                return (double)MathHelper.clampAngle(180 + frame.facingDirection.getHorizontalIndex() * 90);
            }
        });
    }


    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if (!(stack.getTagCompound() == null)) {
            if(stack.getTagCompound().hasKey("pos")) {
                BlockPos asd = NBTUtil.getPosFromTag(stack.getTagCompound().getCompoundTag("pos"));
                tooltip.add("Resonating with coil at x:" + asd.getX() + " y:" + asd.getY() + " z:" + asd.getZ());
                tooltip.add(playerIn.worldObj.getBlockState(asd).getValue(BlockCoil.ENABLED) ? "Coil Enabled" : "Coil Disabled");
            }
        }
    }


    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if (stack.getTagCompound() == null) {

            stack.setTagCompound(new NBTTagCompound());
        }

        if (worldIn.getBlockState(pos).getBlock() instanceof BlockCoil) {

            if (!worldIn.isRemote) {
                playerIn.addChatMessage(new TextComponentString("Resonating with coil at x:" + pos.getX() + " y:" + pos.getY() + " z:" + pos.getZ()));
                stack.getTagCompound().setTag("pos", NBTUtil.createPosTag(pos));
            }
        }

        return EnumActionResult.SUCCESS;
    }
}