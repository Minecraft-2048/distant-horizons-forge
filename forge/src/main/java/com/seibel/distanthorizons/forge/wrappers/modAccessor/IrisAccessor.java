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

package com.seibel.distanthorizons.forge.wrappers.modAccessor;

// Oculus was the Forge fork of Iris and stopped at 1.20.1. From MC 26 onwards the shader mod on
// Forge is the Iris port itself, which registers under the mod id "iris", so this accessor talks
// to the same IrisApi the neoforge module uses.
#if MC_VER <= MC_1_21_11
#else

import com.seibel.distanthorizons.core.wrapperInterfaces.modAccessor.IIrisAccessor;

import net.irisshaders.iris.Iris;
import net.irisshaders.iris.api.v0.IrisApi;

public class IrisAccessor implements IIrisAccessor
{
	@Override
	public String getModName() { return Iris.MODID; }

	@Override
	public boolean isShaderPackInUse() { return IrisApi.getInstance().isShaderPackInUse(); }

	@Override
	public boolean isRenderingShadowPass() { return IrisApi.getInstance().isRenderingShadowPass(); }

}

#endif
