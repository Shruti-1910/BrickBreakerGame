import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class BrickBreaker {
    public static void main(String[] args) {
        JFrame j_ob = new JFrame();
        GamePlay g_ob = new GamePlay();
        j_ob.setBounds(10,10,700,600);
        j_ob.setTitle("Brick Breaker Game");
        j_ob.setResizable(false);
        j_ob.setVisible(true);
        j_ob.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j_ob.add(g_ob);
    }
}