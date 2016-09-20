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

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
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

        this.addPropertyOverride(new ResourceLocation("scomp", "scomp:angle"), new IItemPropertyGetter() {

            double rotation;
            double rota;
            long lastUpdateTick;

            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {

                if (entityIn == null && !stack.isOnItemFrame()) {

                    return 0.0F;
                }
                else {

                    //true if compass in player's hand
                    boolean flag = entityIn != null;

                    //entity becomes either the player(EntityLivingBase) or the ItemFrame(EntityItemFrame)
                    Entity entity = flag ? entityIn : stack.getItemFrame();

                    if (worldIn == null) {

                        worldIn = entity.worldObj;
                    }

                    //this is the float returned
                    double d0;

                    if (worldIn.provider.isSurfaceWorld()) {

                        double d1 = flag ? (double)entity.rotationYaw : this.getFrameRotation((EntityItemFrame)entity);
                        d1 = (double) MathHelper.positiveModulo( (float)d1 / 360F, 1.0F );
                        //this adds an offset because of how compass textures are ordered.
                        d0 = 0.5D - d1;
                    }
                    else {

                        d0 = Math.random();
                    }

                    if (flag) {

                        d0 = this.wobble(worldIn, d0);
                    }

                    System.out.println(MathHelper.positiveModulo((float)d0, 1.0F));
                    return MathHelper.positiveModulo((float)d0, 1.0F);
                }
            }

            //vanilla wobble method
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

            private double getFrameRotation(EntityItemFrame entFrame) {

                return (double)MathHelper.clampAngle(180 + entFrame.facingDirection.getHorizontalIndex() * 90);
            }
        });
    }

    /*

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if (!(stack.getTagCompound() == null)) {
            if(stack.getTagCompound().hasKey("Description")) {
                tooltip.add(stack.getTagCompound().getString("Description"));
            }
        }
    }


    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if (stack.getTagCompound() == null)
        {
            stack.setTagCompound(new NBTTagCompound());
        }

        if (worldIn.getBlockState(pos).getBlock() instanceof BlockCoil) {
            if (!worldIn.isRemote) {
                String desc = "Resonating, x" + pos.getX() + " y" + pos.getY() + " z" + pos.getZ();
                playerIn.addChatMessage(new TextComponentString("Resonanting with the coil at x: " + pos.getX() + " y: " + pos.getY() + " z: " + pos.getZ()));
                stack.getTagCompound().setString("Description", desc);
            }
        }

        return EnumActionResult.SUCCESS;
    }
    */
}
