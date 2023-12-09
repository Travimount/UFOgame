package entities;

public class FastUfo extends UFO {
    private final String imagePath = "resource/image/Ufo3.gif";
    public FastUfo(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);
        loadImage(imagePath);
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
        this.speed_x = 2.5;
        this.speed_y = 2.5;
        this.shootProbability = 700;
    }
}
