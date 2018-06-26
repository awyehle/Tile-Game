import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class GUI extends JFrame
{
	private JPanel panel1 = new JPanel();
    private JButton[][] buttons = new JButton[8][8];
    private JLabel points;
    
    public GUI() throws IOException
    {
        setTitle("Tile Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createContent();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setSize(500, 500);
    }

    private void createContent() throws IOException 
    {
        createMenu();

        panel1.setLayout(new GridLayout(8, 8));
        add(panel1);
        
        for (int height = 0; height < 8; height++) {
            for (int across = 0; across < 8; across++) {
                JButton button = new JButton();
                button.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
                button.addActionListener(new ButtonListener(button));

                panel1.add(button);
                buttons[height][across] = button;
            }
        }
        //Points panel bottom
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 3));
        add(panel2, BorderLayout.SOUTH);
        
        JPanel panel3 = new JPanel();
        panel2.add(panel3);
        points = new JLabel();
        panel3.add(new JLabel("Points:"));
        panel3.add(points);
    }

    private void createMenu() 
    {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu = new JMenu("Game");
        menuBar.add(menu);
        
        // New Game
        JMenuItem newGame = new JMenuItem("New Game");
        menu.add(newGame);
        menu.addSeparator();
    }
    
    private class ButtonListener implements ActionListener
    {
    	private JButton _useButton;
        public ButtonListener(JButton button)
        {
			this._useButton = button;
		}

		@Override
        public void actionPerformed(ActionEvent e) 
		{
			
        	double rando = (int)(Math.random() * 10) + 1;
        	if(rando == 1 || rando == 2 || rando == 3)
        	{
        		BufferedImage image = null;
        		URL file = getClass().getResource("resources/BethNew.bmp");
            	try
            	{
					image = ImageIO.read(file);
					_useButton.setIcon(new ImageIcon(image));
				} 
            	catch (IOException e1)
            	{
					e1.printStackTrace();
				}
        	} 
        	else if(rando == 4 || rando == 5 || rando == 6)
        	{
        		BufferedImage image = null;
        		URL file = getClass().getResource("resources/NoahNew.bmp");
            	try
            	{
					image = ImageIO.read(file);
					_useButton.setIcon(new ImageIcon(image));
				}
            	catch (IOException e1) 
            	{
					e1.printStackTrace();
				}
        	}
        	else if(rando == 7 || rando == 8 || rando == 9)
        	{
        		BufferedImage image = null;
        		URL file = getClass().getResource("resources/AndrewNew.bmp");
            	try 
            	{
					image = ImageIO.read(file);
					_useButton.setIcon(new ImageIcon(image));
				} 
            	catch (IOException e1)
            	{
					e1.printStackTrace();
				}
        	}
        	else
        	{
        		BufferedImage image = null;
        		URL file = getClass().getResource("resources/ZachNew.bmp");
            	try
            	{
					image = ImageIO.read(file);
					_useButton.setIcon(new ImageIcon(image));
				}
            	catch (IOException e1)
            	{
					e1.printStackTrace();
				}
        	}
        }
    }
    
    public static void main(String[] args) throws IOException
    {
        try 
        {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        new GUI();
    }
}
