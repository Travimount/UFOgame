public class Main {
    public static void main(String[] args) {
        // 使用事件分派线程来创建和显示游戏窗口，以确保线程安全
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // 创建游戏窗口实例
                GameWindow window = new GameWindow();
                // 设置窗口可见
                window.setVisible(true);
            }
        });
    }
}
