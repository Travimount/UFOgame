package entities;

public class ShootUfo extends UFO{
    private final String imagePath = "resource/image/Ufo1.gif";
    public ShootUfo(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);
        loadImage(imagePath);
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
        this.speed_x = 1.25;
        this.speed_y = 1.25;
        this.shootProbability = 400;
    }
}
