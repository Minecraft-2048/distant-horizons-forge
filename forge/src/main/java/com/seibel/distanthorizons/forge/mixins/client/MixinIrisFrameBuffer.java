/*
 *    This file is part of the Distant Horizons mod
 *    licensed under the GNU LGPL v3 License.
 *
 *    Copyright (C) 2020 James Seibel
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU Lesser General Public License as published by
 *    the Free Software Foundation, version 3.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public License
 *    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.seibel.distanthorizons.forge.mixins.client;

#if MC_VER <= MC_1_20_4
import net.coderbot.iris.gl.IrisRenderSystem;
#else
import net.irisshaders.iris.gl.IrisRenderSystem;
#endif

import org.lwjgl.opengl.GL30C;

import com.seibel.distanthorizons.core.wrapperInterfaces.modAccessor.IIrisAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = IIrisAccessor.FRAMEBUFFER_MIXIN_CLASS, remap = false)
public abstract class MixinIrisFrameBuffer
{
	@Redirect(
		method = "addDepthAttachment",
		at = @At(
			value = "INVOKE",
			target = "Lnet/irisshaders/iris/gl/IrisRenderSystem;framebufferTexture2D(IIIIII)V"))
	private void releaseStaleDepthPoints(
		final int framebuffer, final int framebufferTarget,
		final int attachment, final int textureTarget, final int texture, final int levels)
	{
		IrisRenderSystem.framebufferTexture2D(framebuffer, framebufferTarget, GL30C.GL_DEPTH_ATTACHMENT, textureTarget, 0, 0);
		IrisRenderSystem.framebufferTexture2D(framebuffer, framebufferTarget, GL30C.GL_STENCIL_ATTACHMENT, textureTarget, 0, 0);
		IrisRenderSystem.framebufferTexture2D(framebuffer, framebufferTarget, attachment, textureTarget, texture, levels);
	}
}
