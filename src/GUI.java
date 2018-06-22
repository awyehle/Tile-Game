import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class GUI extends JFrame {
	
	private JPanel panel1 = new JPanel();
    private JButton[][] buttons = new JButton[8][8];


    private JLabel points;
    
    public GUI() throws IOException {
        setTitle("Tile Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createContent();

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setSize(500, 500);
    }
    
    
    
    
    
    
    
    
    



    // ------------------------------------------------------------------------
    // GUI Setup
    // ------------------------------------------------------------------------

    private void createContent() throws IOException {
        createMenu();
        
        // --------------------------------------------------------------------
        // Main Grid (Board Buttons)
        // --------------------------------------------------------------------

        panel1.setLayout(new GridLayout(8, 8));
        add(panel1);

        for (int height = 0; height < 8; height++) {
            for (int across = 0; across < 8; across++) {
            	double rando = Math.random();
            	if(rando % 10 == 1 || rando % 10 == 2 || rando % 10 == 3)
            	{
            		JButton button = new JButton();
            		BufferedImage image = null;
            		URL file = getClass().getResource("resources/BethNew.bmp");
                	image = ImageIO.read(file);
            		
            	}
                JButton button = new JButton();
                button.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
                button.addActionListener(new ButtonListener());

                panel1.add(button);
                buttons[height][across] = button;
            }

        }
        
        
        // --------------------------------------------------------------------
        // Status Bar (Timers, Current Turn Label)
        // --------------------------------------------------------------------

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 3));
        add(panel2, BorderLayout.SOUTH);

        JPanel panel3 = new JPanel();

        panel2.add(panel3);

        points = new JLabel();
        panel3.add(new JLabel("Points:"));
        panel3.add(points);




    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Game");
        menuBar.add(menu);

        // --------------------------------------------------------------------
        // New Game
        // --------------------------------------------------------------------

        JMenuItem newGame = new JMenuItem("New Game");
        menu.add(newGame);


        menu.addSeparator();

 
    }



    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //----------Create Beth Button-----------
            BufferedImage image = null;
            try{
            	URL file = getClass().getResource("resources/BethNew.bmp");
            	image = ImageIO.read(file);
            }catch(IOException ioex){
            	System.err.println("load error: " + ioex.getMessage());
            }
            ImageIcon icon = new ImageIcon(image);
            JButton bethButton = new JButton(icon);
            panel1.add(bethButton);
            //buttons[height][across] = bethButton;
            //--------------End Beth Button Create------------
        	
        	
        	
        }
    }
    


        
        

        

    

    


    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new GUI();
    }
}
