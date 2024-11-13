package am.world.blocks.effect;

public class StorageCoreModule {
}
/*
How I expect modules to work:
 Rendered layers from bottom to top: base, base's cells, directional overlay,
directional overlay's cells, icon glow.
 The module would get the cords and direction to get tiles which the module touches.
 If it touches non-core block - disallow placement.
 If touches core block - allow unit construction and make it act like connected to
the core as container.
 If commanding module touches core block - make it act as static wall that increases
unit cap.
 If module touches module managers - count it as core block, because module managers
must touch core or other module managers.
 Check once every second if module still touches core blocks - if the core is
destroyed or module manager is deconstructed, the touching it modules will destroy
their units and deactivate effects.
 If module manager doesn't touch the core - deactivate it and connected modules.
*/
