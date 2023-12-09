import javax.swing.JFrame;

public class GameWindow extends JFrame {

    private GamePanel panel;

    public GameWindow() {
        // 初始化用户界面
        initUI();
    }

    private void initUI() {
        // 创建游戏面板并添加到窗口
        panel = new GamePanel();
        add(panel);

        // 设置窗口的标题
        setTitle("UFO Game");

        // 确保窗口关闭时程序也会完全退出
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 设置窗口大小
        setSize(800, 600);

        // 窗口居中显示
        setLocationRelativeTo(null);

        // 不允许用户改变窗口大小
        setResizable(false);
        //添加背景图片
    }
}
