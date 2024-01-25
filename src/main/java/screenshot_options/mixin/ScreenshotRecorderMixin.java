package screenshot_options.mixin;

import com.mojang.blaze3d.framebuffer.Framebuffer;
import com.mojang.blaze3d.texture.NativeImage;
import net.minecraft.client.util.ScreenshotRecorder;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import screenshot_options.Screenshot;
import screenshot_options.ScreenshotOptionsMod;

import java.io.File;
import java.util.function.Consumer;

@Mixin(ScreenshotRecorder.class)
public abstract class ScreenshotRecorderMixin {
    @Inject(method = "saveScreenshotInner",
            at = @At(value = "HEAD"),
            cancellable = true)
    private static void interceptScreenshot(File gameDirectory, @Nullable String fileName, Framebuffer framebuffer, Consumer<Text> messageReceiver, CallbackInfo ci) {
        NativeImage image = ScreenshotRecorder.takeScreenshot(framebuffer);
        Screenshot screenshot = new Screenshot(image);
        ScreenshotOptionsMod.handleScreenshot(screenshot);
        ci.cancel();
    }
}
