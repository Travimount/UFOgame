package entities;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
public class City {
    private Point position; // 城市的位置
    private Image image; // 当前城市的图像
    private static Image intactImage; // 未受损城市的图像
    private static Image destroyedImage; // 被摧毁城市的图像
    private boolean isDestroyed; // 城市是否被摧毁

    private static int width; // 城市的宽度
    private static int height; // 城市的高度

    static {
        // 加载城市图片并获取宽度和高度
        String intactImagePath = "resource/image/City.gif";
        String destroyedImagePath = "resource/image/CityBurn.gif";
        ImageIcon intactIcon = new ImageIcon(intactImagePath);
        ImageIcon destroyedIcon = new ImageIcon(destroyedImagePath);
        intactImage = intactIcon.getImage();
        destroyedImage = destroyedIcon.getImage();
        width = intactImage.getWidth(null);
        height = intactImage.getHeight(null);
    }

    // 构造函数，初始化城市的位置以及加载图像
    public City(int x, int y) {
        this.position = new Point(x, y);
        this.isDestroyed = false;
        this.image = intactImage; // 初始时城市未被摧毁
    }
    // 获取当前城市图像
    public Image getImage() {
        return image;
    }
    public static int getWidth() {
        return width;
    }
    public static int getHeight() {
        return height;
    }
    // 获取城市的位置
    public Point getPosition() {
        return position;
    }

    // 城市被击中的处理
    public void destroy() {
        image = destroyedImage;
        isDestroyed = true;
    }
    //获取城市边界
    public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, width, height);
    }
}
