import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import entities.*;

public class GamePanel extends JPanel implements ActionListener {

    private final int UFO_COUNT = 1; // 初始UFO的数量
    private final int CITY_COUNT = 8; // 初始城市的数量
    private int shield = 10;  //初始护盾数量
    private final Random random = new Random();
    private final int DELAY = 15; // 控制游戏更新的延迟时间，单位为毫秒
    private Timer timer;
    private Image backgroundImage; // 背景图片
    private ArrayList<UFO> ufos; // 存放UFO对象
    private ArrayList<City> cities; // 存放城市对象
    private ArrayList<Bullet> bullets; // 存放子弹对象
    private int score = 0; // 玩家得分
    public GamePanel() {
        initPanel();
    }
    private void initGameObjects() {
        // 初始化UFOs
        for (int i = 0; i < UFO_COUNT; i++) {
            UFO ufo = null;
            switch (random.nextInt(3)){
                case 0:ufo =new FastUfo(800,600);break;
                case 1:ufo =new NormalUfo(800,600);break;
                case 2:ufo =new ShootUfo(800,600);break;
            }
            ufos.add(ufo);
        }
        // 初始化Cities
        for (int i = 0; i < CITY_COUNT; i++) {
            int x;
            boolean overlaps;
            do {
                overlaps = false;
                x = random.nextInt(600 - City.getWidth());
                // 检查新的城市是否与现有城市重叠
                for (City city : cities) {
                    if (Math.abs(x - city.getPosition().x) < City.getWidth()+10) {
                        overlaps = true;
                        break;
                    }
                }
            } while (overlaps);
            int y = 500; // 假设城市在屏幕底部
            City city = new City(x, y);
            cities.add(city);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // ... 现有的更新游戏逻辑代码 ...
        updateGame();
        // 定期生成UFO
        generateUFOs();
        // 定期生成子弹
        generateBullets();
        // 重新绘制游戏画面
        repaint();
    }
    private  void  generateUFOs(){
        // 随机生成UFO，可以调整逻辑和频率
        if(random.nextInt(450) < 1){
            UFO ufo = null;
            switch (random.nextInt(3)){
                case 0:ufo =new FastUfo(800,600);break;
                case 1:ufo =new NormalUfo(800,600);break;
                case 2:ufo =new ShootUfo(800,600);break;
            }
            ufos.add(ufo);
        }
    }


    private void generateBullets() {
        // 随机生成子弹，可以调整逻辑和频率
        for (UFO ufo : ufos) {
            if (ufo.shoot()) {
                int x = ufo.getPosition().x; // 从UFO的位置发射
                int y = ufo.getPosition().y; // 从UFO的位置发射
                Bullet bullet = new Bullet(x, y, "resource/image/Bullet.gif", 2); // 速度为5
                bullets.add(bullet);
            }
        }
    }


    private void initPanel() {
        //初始化背景
        ImageIcon BG = new ImageIcon("resource/image/BackGround.gif");
        backgroundImage = BG.getImage();
        // 设置面板的焦点，以便它可以接收键盘事件
        setFocusable(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePress(e);
            }
        });

        // 初始化游戏对象列表
        ufos = new ArrayList<UFO>();
        cities = new ArrayList<City>();
        bullets = new ArrayList<Bullet>();

        // 在这里初始化UFOs, Cities和Bullets
        initGameObjects();
        // 创建并启动定时器，以便定期调用actionPerformed，从而更新游戏逻辑和重绘
        timer = new Timer(DELAY, this);
        timer.start();
    }

    // 处理鼠标点击事件，检查是否击中UFO
    private void handleMousePress(MouseEvent e) {
        Iterator<UFO> it = ufos.iterator();
        while (it.hasNext()) {
            UFO ufo = it.next();
            if (ufo.isAlive() && ufo.getBounds().contains(e.getPoint())) {
                ufo.destroy();
                score++;
                break; // 假设一次点击只能击中一个UFO
            }
        }
    }


    private void updateGame() {
        // 更新UFO位置
        for (UFO ufo : ufos) {
            ufo.move();
            // 可能还需要检查UFO是否离开屏幕等
        }

        // 更新子弹位置
        for (Bullet bullet : bullets) {
            bullet.move();
            // 检查子弹是否击中城市或离开屏幕
            for (City city : cities) {
                if (bullet.isActive() && city.getBounds().contains(bullet.getPosition())) {
                    if(shield > 0){
                        shield--;
                        bullet.deactivate();
                        break;
                    }
                    else {
                        bullet.deactivate();
                        city.destroy();
                    }
                }
            }
        }

        // 清除不再活跃的对象
        ufos.removeIf(ufo -> !ufo.isAlive());
        bullets.removeIf(bullet -> !bullet.isActive());

        // 其他游戏逻辑...
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 在这里绘制游戏的每一个对象
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        //显示背景
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this); // 调整图片大小以适应面板
        }
        //显示分数
        Font font = new Font("Serif", Font.BOLD, 30); // 20是字体大小
        g.setFont(font);
        g.setColor(Color.WHITE); // 设置字体颜色
        g.drawString("Score: " + score, 30, 30);
        g.drawString("CitesLeft: " + cities.size(), 30, 80);
        g.drawString("ShieldLeft: " + shield, 30, 130);
        Graphics2D g2d = (Graphics2D) g;
        //显示地面
        // 在底部绘制蓝色条
        g.setColor(Color.BLUE); // 设置颜色为蓝色
        int panelWidth = this.getWidth();
        int panelHeight = this.getHeight();
        int groundHeight = 55 - City.getHeight()/2; // 地面的高度
        g.fillRect(0, panelHeight - groundHeight, panelWidth, groundHeight); // 绘制矩形

        // 绘制UFOs
        for (UFO ufo : ufos) {
            if (ufo.isAlive()) {
                g2d.drawImage(ufo.getImage(), ufo.getPosition().x, ufo.getPosition().y, this);
            }
        }

        // 绘制城市
        for (City city : cities) {
            g2d.drawImage(city.getImage(), city.getPosition().x, city.getPosition().y, this);
        }

        // 绘制子弹
        for (Bullet bullet : bullets) {
            if (bullet.isActive()) {
                g2d.drawImage(bullet.getImage(), bullet.getPosition().x, bullet.getPosition().y, this);
            }
        }
    }
}
