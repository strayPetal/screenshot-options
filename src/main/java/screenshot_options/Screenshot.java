package screenshot_options;

import com.mojang.blaze3d.texture.NativeImage;
import screenshot_options.utils.TransferableImage;
import screenshot_options.utils.io.IO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static screenshot_options.ScreenshotOptionsMod.LOGGER;

public class Screenshot {
    public final NativeImage image;
    
    public Path fileLocation;
    
    public Screenshot(NativeImage image) {
        this.image = image;
    }
    
    public void saveTo(Path destination) {
        try (image) {
            image.writeFile(destination);
            fileLocation = destination;
        } catch (Exception e) {
            LOGGER.error("An error occurred while trying to save the image.", e);
        }
    }
    
    public void renameTo(Path destination) {
        try {
            if (fileLocation == null) {
                LOGGER.error("Rename error: file was never saved!");
                return;
            } else if (Files.exists(fileLocation)) {
                LOGGER.error("Rename error: file does not exist!");
            }
            Files.move(fileLocation, destination);
        } catch (Exception e) {
            LOGGER.error("An error occurred while renaming the file.", e);
        }
    }
    
    public void delete() {
        try {
            Files.delete(fileLocation);
        } catch (IOException e) {
            LOGGER.error("An error occurred while deleting the file.", e);
        }
    }
    
    public void copyToClipboard(Path source) {
        if (IO.isHeadless) return;
        BufferedImage image;
        
        try {
            image = ImageIO.read(Files.newInputStream(fileLocation));
        } catch (Exception e) {
            LOGGER.error("An error occurred trying to read the image file.", e);
            return;
        }
        
        if (image == null) return;
        IO.copyToClipboard(new TransferableImage(image));
    }
}
