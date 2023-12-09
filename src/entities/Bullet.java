package entities;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public class Bullet {
    private Point position; // 炸弹的当前位置
    private Image image; // 炸弹的图像
    private final int speed; // 炸弹的下落速度
    private boolean isActive; // 炸弹是否处于活跃状态

    // 构造函数，初始化炸弹的位置和图像，并设置速度
    public Bullet(int startX, int startY, String imagePath, int speed) {
        this.position = new Point(startX, startY);
        this.speed = speed;
        this.isActive = true;
        loadImage(imagePath);
    }

    // 加载炸弹的图像
    private void loadImage(String imagePath) {
        ImageIcon ii = new ImageIcon(imagePath);
        image = ii.getImage();
    }

    // 获取炸弹的图像
    public Image getImage() {
        return image;
    }

    // 获取炸弹的位置
    public Point getPosition() {
        return position;
    }

    // 炸弹的移动逻辑
    public void move() {
        // 如果炸弹处于活跃状态，它将向下移动
        if (isActive) {
            position.y += speed;
        }
    }

    // 当炸弹击中城市或离开屏幕时调用
    public void deactivate() {
        isActive = false;
    }

    // 检查炸弹是否活跃
    public boolean isActive() {
        return isActive;
    }
}
