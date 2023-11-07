// public class App {
//     public static void main(String[] args) throws Exception {
//         System.out.println("Hello, World!");
//     }
    
// }

import javax.swing.*;

public class ShootingFrame extends JFrame{
    public ShootingPanel panel;

    public ShootingFrame(){

        panel=new ShootingPanel();

        this.add(panel);
        // JFrame frame = new JFrame("MyTitle");
        this.setTitle("Shooting");
        this.setBounds(100, 100, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.addKeyListener(new Keyboard());
    }
}
