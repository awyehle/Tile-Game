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
    private char[][] saveStates = new char[8][8];
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
    	URL fileBeth = getClass().getResource("resources/BethNew.bmp");
    	URL fileNoah = getClass().getResource("resources/NoahNew.bmp");
    	URL fileAndrew = getClass().getResource("resources/AndrewNew.bmp");
    	URL fileZach = getClass().getResource("resources/ZachNew.bmp");
    	BufferedImage bethImage = ImageIO.read(fileBeth);
    	BufferedImage noahImage = ImageIO.read(fileNoah);
    	BufferedImage andrewImage = ImageIO.read(fileAndrew);
    	BufferedImage zachImage = ImageIO.read(fileZach);
        createMenu();

        panel1.setLayout(new GridLayout(8, 8));
        add(panel1);
        
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                JButton button = new JButton();
                button.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
                button.addActionListener(new ButtonListener(x, y));
                int rando = (int)(Math.random() * 10) + 1;

                switch(rando)
                {
                case 1:saveStates[x][y] = 'B'; break;
                case 2:saveStates[x][y] = 'B'; break;
                case 3:saveStates[x][y] = 'B'; break;
                case 4:saveStates[x][y] = 'N'; break;
                case 5:saveStates[x][y] = 'N'; break;
                case 6:saveStates[x][y] = 'N'; break;
                case 7:saveStates[x][y] = 'A'; break;
                case 8:saveStates[x][y] = 'A'; break;
                case 9:saveStates[x][y] = 'A'; break;
                default:saveStates[x][y] = 'Z'; break;
                }
                panel1.add(button);
                buttons[x][y] = button;
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
    	private int x;
    	private int y;
    	
        public ButtonListener(int x, int y)
        {
			this.x = x;
			this.y = y;
		}

		@Override
        public void actionPerformed(ActionEvent e) 
		{
        	if(saveStates[x][y] == 'B')
        	{
        		BufferedImage image = null;
        		URL file = getClass().getResource("resources/BethNew.bmp");
            	try
            	{
					image = ImageIO.read(file);
					buttons[x][y].setIcon(new ImageIcon(image));
				} 
            	catch (IOException e1)
            	{
					e1.printStackTrace();
				}
        	} 
        	else if(saveStates[x][y] == 'N')
        	{
        		BufferedImage image = null;
        		URL file = getClass().getResource("resources/NoahNew.bmp");
            	try
            	{
					image = ImageIO.read(file);
					buttons[x][y].setIcon(new ImageIcon(image));
				}
            	catch (IOException e1) 
            	{
					e1.printStackTrace();
				}
        	}
        	else if(saveStates[x][y] == 'A')
        	{
        		BufferedImage image = null;
        		URL file = getClass().getResource("resources/AndrewNew.bmp");
            	try 
            	{
					image = ImageIO.read(file);
					buttons[x][y].setIcon(new ImageIcon(image));
				} 
            	catch (IOException e1)
            	{
					e1.printStackTrace();
				}
        	}
        	else if (saveStates[x][x] == 'Z')
        	{
        		BufferedImage image = null;
        		URL file = getClass().getResource("resources/ZachNew.bmp");
            	try
            	{
					image = ImageIO.read(file);
					buttons[x][y].setIcon(new ImageIcon(image));
				}
            	catch (IOException e1)
            	{
					e1.printStackTrace();
				}
        	}
        	else if (saveStates[x][x] == 'E')
        	{
					buttons[x][y].setIcon(null);
				
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
        new GUI();//poop
    }
}
