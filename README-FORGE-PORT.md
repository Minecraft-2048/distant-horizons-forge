# Distant Horizons — unofficial MinecraftForge port (26.2)

This branch is an unofficial port of Distant Horizons to **MinecraftForge 26.2**, published to
satisfy the source-availability requirement of the **LGPL v3**, the licence Distant Horizons is
released under.

Upstream project: <https://gitlab.com/distant-horizons-team/distant-horizons>
Upstream authors: James Seibel and the Distant Horizons team. All credit for the mod itself is
theirs; this branch only adapts it to another mod loader.

## What this port changes

Everything below is the complete list of differences from upstream. Two repositories are involved,
because the mod is split into a loader project and a shared core.

### In this repository

| Area | Change |
|---|---|
| `forge/build.gradle`, `versionProperties/26.2.0.properties` | Target Forge 26.2; build chain realigned |
| `forge/.../ForgeMain.java`, `ForgeClientProxy.java`, `ForgeServerProxy.java` | Forge's event bus and lifecycle in place of NeoForge's |
| `forge/.../mixins/**` | Injection points and signatures revised for the Forge jar, which differs from the NeoForge one in level rendering, fog, the light texture and the debug overlay |
| `MixinChunkSectionsToRender.java` *(new)* | Chunk section collection under Forge |
| `MixinGameRenderer.java` *(new)* | Render hook Forge does not provide as an event |
| `MixinIrisFrameBuffer.java` *(new)* | Fixes the stencil buffer when Iris is installed |
| `forge/src/main/resources/META-INF/mods.toml`, `DistantHorizons.forge.mixins.json` | Forge metadata |
| `common/.../AbstractModInitializer.java`, `DhConfigScreen.java` | API differences on the shared side |
| `buildSrc/.../dh-loader.gradle` | Loader selection |

### In the core sub-project

The `coreSubProjects` submodule points at a companion branch carrying one change: **the
auto-updater is neutralised**. CurseForge's moderation policy refuses mods that can download and
install code at runtime, so `SelfUpdater`, `WebDownloader`, `GitlabGetter` and `ModrinthGetter`
return without performing any network access. The classes are kept rather than deleted, so the
difference from upstream stays readable.

## Contributing this upstream

The Distant Horizons team has offered to review a merge request for Forge support. That is the
better home for this work, and it is being prepared separately.

## Licence

Distant Horizons is licensed under the **GNU LGPL v3**, and this port keeps that licence
unchanged. See `LICENSE.txt` and `LICENSE.LESSER.txt`.
