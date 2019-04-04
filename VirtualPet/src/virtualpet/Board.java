package virtualpet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
/**
 *
 * @author feliciat
 */
public class Board  extends JFrame {
    private JPanel displayPanel;
    private JPanel imagePanel;
    private JPanel buttonPanel;
    
    private final JLabel hungerLabel = new JLabel("Hungriness: 0%");
    private final JLabel happinessLabel = new JLabel("Happiness: 100%");
    private final JLabel cleanLabel = new JLabel("You are clean and shine!");
    private final JLabel imageLabel = new JLabel();
    private final JLabel imageHappyLabel = new JLabel();
    private final JLabel imageSadLabel = new JLabel();
    
    private ImageIcon myPet;
    private ImageIcon myHappyPet;
    private ImageIcon mySadPet;
    private final JButton choiceButton = new JButton("Click me");
    private final Controller cntl;
    
    public Board(Controller cntl) {
        this.cntl = cntl;
        initComponents();
        imageLabel.setVisible(true);
        imageHappyLabel.setVisible(false);
        imageSadLabel.setVisible(false);    
    }
    private void initComponents() {
        setTitle("Virtual Pet");
        setSize(400, 350);
        setLocationRelativeTo(null);  // center the fGridLayoutrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        displayPanel = new JPanel(new GridLayout(3, 1));
        GroupLayout displayLayout = new GroupLayout(displayPanel);
        displayPanel.setLayout(displayLayout);
        displayLayout.setHorizontalGroup(
            displayLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(displayLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(displayLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(hungerLabel)
                .addComponent(happinessLabel)
                .addComponent(cleanLabel))
                .addContainerGap(290, Short.MAX_VALUE))
        );
        displayLayout.setVerticalGroup(
            displayLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(displayLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hungerLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(happinessLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cleanLabel)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        imagePanel = new JPanel();
        myPet =loadImage("/myPet.jpg", 100, 120);
        imageLabel.setIcon(myPet);
        myHappyPet =loadImage("/happyPet.jpg", 100, 120);
        imageHappyLabel.setIcon(myHappyPet);
        mySadPet =loadImage("/sadPet.jpg", 100, 120);
        imageSadLabel.setIcon(mySadPet);
        
        GroupLayout jPanel1Layout = new GroupLayout(imagePanel);
        imagePanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imageHappyLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imageSadLabel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
            )
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(imageLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(imageHappyLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                    .addComponent(imageSadLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            )
        );
        
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton feedButton = new JButton("Feed"); 
        JButton playButton = new JButton("Play");
        JButton cleanButton = new JButton("Clean");
        choiceButton.setBackground(new java.awt.Color(255, 153, 102));
        choiceButton.setPreferredSize(new Dimension(382, 50));         
        choiceButton.setFont(new Font("Arial", Font.PLAIN, 20));
        choiceButton.setHorizontalAlignment(SwingConstants.CENTER); 
        choiceButton.setVisible(false);
            
        feedButton.addActionListener(event -> cntl.onFeed()); 
        playButton.addActionListener(event -> cntl.onPlay());
        cleanButton.addActionListener(event -> cntl.clean());
        choiceButton.addActionListener(event -> cntl.choice());
        
        buttonPanel.add(feedButton); 
        buttonPanel.add(playButton);
        buttonPanel.add(cleanButton);
        buttonPanel.add(choiceButton);
        
        setContentPane(new JPanel(new BorderLayout()));
        getContentPane().add(displayPanel, BorderLayout.NORTH);
        getContentPane().add(imagePanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setPreferredSize(new Dimension(400, 86));

    }
    
    private ImageIcon loadImage(String fileName, int w, int h) {    
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        Image image = icon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(w, h,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        return new ImageIcon(newimg);  // transform it back    
    }
    
    public void DisplayImage(boolean show) {
        imageLabel.setVisible(show);
    }
    public void DisplayHappyImage(boolean show) {
        imageHappyLabel.setVisible(show);
    }
    public void DisplaySadImage(boolean show) {
        imageSadLabel.setVisible(show);
    }
    
    public void setHungerLabel(String value) {
        hungerLabel.setText(value);
    }
    public void setHappinessLabel(String value) {
        happinessLabel.setText(value);
    }    
    public void setCleanLabel(String value) {
        cleanLabel.setText(value);
    }
    public void setChoiceButton(String value, boolean isVisible, String fileName) {
        choiceButton.setVisible(isVisible);
        if (!isVisible) return;
               choiceButton.setIcon(loadImage(fileName, 40, 40)); 
               choiceButton.setText(value);
           }
    public String getChoiceButtonlabel() {
        return choiceButton.getText();
    }
} 

