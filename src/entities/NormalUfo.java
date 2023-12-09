package entities;

public class NormalUfo extends UFO{
    private final String imagePath = "resource/image/Ufo2.gif";
    public NormalUfo(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);
        loadImage(imagePath);
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
        this.speed_x = 1.75;
        this.speed_y = 1.75;
        this.shootProbability = 600;
    }
}
