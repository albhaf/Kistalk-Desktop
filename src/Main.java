import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
  public Main() {
    setBackground(Color.white);
  }
  public void paint(Graphics g) {
    try {
      Graphics2D g2D;
      g2D = (Graphics2D) g;
      g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      String fileName = "C:\\Users\\Public\\Pictures\\Sample Pictures\\desert.jpg";
      Image img = getToolkit().getImage(fileName);
      AffineTransform aTran = new AffineTransform();
      aTran.translate(50.0f, 20.0f);
      g2D.transform(aTran);
      g2D.drawImage(img, new AffineTransform(), this);
    } catch (Exception e) {
    }
  }

  public static void main(String s[]) {
    JFrame frame1 = new JFrame("2D Images ");
    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame1.getContentPane().add("Center", new Main());
    frame1.pack();
    frame1.setSize(new Dimension(300, 300));
    frame1.setVisible(true);
  }
}
