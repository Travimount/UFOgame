package entities;

import java.awt.Point;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.util.Random;
public class UFO {
    protected int screen_width;
    protected int screen_height;
    protected int width;
    protected int height;
    protected Point position; // UFO的当前位置
    protected Image image; // UFO的图像
    protected boolean direction_x; //true表示UFO向左移动,false表示UFO向右移动
    protected boolean direction_y; //true表示UFO向上移动,false表示UFO向下移动
    protected boolean isAlive; // UFO是否存活
    protected double speed_x; //横轴速度
    protected double speed_y; //纵轴速度
    //发射概率
    protected int shootProbability;

    // 构造函数，初始化UFO的位置和移动算法
    public UFO(int screenWidth, int screenHeight) {
        int startX = new Random().nextInt(screenWidth);
        int startY = new Random().nextInt(screenHeight/3);
        this.position = new Point(startX, startY);
        this.isAlive = true;
        this.screen_height = screenHeight;
        this.screen_width = screenWidth;
        //随机生成UFO的移动方向
        this.direction_x = new Random().nextBoolean();
        this.direction_y = new Random().nextBoolean();
    }
    // 获取UFO边界的方法
    public Rectangle getBounds() {
        return new Rectangle(position.x, position.y, width, height);
    }
    // 加载UFO的图像
    protected void loadImage(String imagePath) {
        ImageIcon ii = new ImageIcon(imagePath);
        image = ii.getImage();
    }
    // 获取UFO的图像
    public Image getImage() {
        return image;
    }

    // 获取UFO的位置
    public Point getPosition() {
        return position;
    }

    // 设置UFO的位置
    public void setPosition(Point position) {
        this.position = position;
    }

    // 移动UFO到新的位置
    public void move() {
        if (isAlive) {
            position = calculateNextPosition(position,this.direction_x,this.direction_y);
        }
    }

    protected Point calculateNextPosition(Point position,boolean dir_x,boolean dir_y) {
        //设定UFO的移动边界为400*600,当UFO到达边界时改变移动方向,类似与弹力球碰撞边界的效果，请你编写代码
        if (dir_x && dir_y && position.x >0 && position.y > 0) {
            position.x -= this.speed_x;
            position.y -= this.speed_y;
        } else if (!dir_x && dir_y && position.x < screen_width - width && position.y > 0) {
            position.x += this.speed_x;
            position.y -= this.speed_y;
        } else if (dir_x && !dir_y && position.x > 0 && position.y < screen_height/3 - height) {
            position.x -= this.speed_x;
            position.y += this.speed_y;
        } else if (!dir_x && !dir_y && position.x <screen_width - width && position.y < screen_height/3 - height ) {
            position.x += this.speed_x;
            position.y += this.speed_y;
        }
        //当UFO到达边界时改变移动方向
        if (position.x <= 0) {
            this.direction_x = false;
        } else if (position.x >= screen_width - width) {
            this.direction_x = true;
        }
        if (position.y <= 0) {
            this.direction_y = false;
        } else if (position.y >= screen_height/3 - height) {
            this.direction_y = true;
        }
        return position;
    }
    //判断UFO是否发射炮弹
    public boolean shoot() {
        if (new Random().nextInt(shootProbability) == 1) {
            return true;
        }
        return false;
    }
    // 判断UFO是否存活
    public boolean isAlive() {
        return isAlive;
    }

    // 设置UFO为不存活状态
    public void destroy() {
        isAlive = false;
    }
}
