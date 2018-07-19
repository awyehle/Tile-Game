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
	private JPanel _panel1 = new JPanel();
    private JButton[][] _buttons = new JButton[8][8];
    private char[][] _saveStates = new char[8][8];
    private int _lastClickX;
    private int _lastClickY;
    private char _lastClickTile;
    private JLabel _pointsLabel;
    private int _points;
        
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

        _panel1.setLayout(new GridLayout(8, 8));
        add(_panel1);
        
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                JButton button = new JButton();
                button.setFont(new Font("Arial Unicode MS", Font.BOLD, 25));
                button.addActionListener(new ButtonListener(x, y));
                int rando = (int)(Math.random() * 10) + 1;

		/*
		int bCount;
		int aCount;
		int nCount;
		int zCount;

		if( bCount % 2 != 0 && x ==7 && _saveStates[x][y] == '')
		{
		_saveStates[x][y] = 'B';
		continue;
		}
		else if( aCount % 2 != 0 && x ==7 && _saveStates[x][y] == '')
		{
		_saveStates[x][y] = 'A';
		continue;
		}
		else if( nCount % 2 != 0 && x ==7 && _saveStates[x][y] == '')
		{
		_saveStates[x][y] = 'N';
		continue;
		}
		else if( zCount % 2 != 0 && x ==7 && _saveStates[x][y] == '')
		{
		_saveStates[x][y] = 'Z';
		continue;
		}
		*/

                switch(rando)
                {
                case 1:_saveStates[x][y] = 'B'; break;
                case 2:_saveStates[x][y] = 'B'; break;
                case 3:_saveStates[x][y] = 'B'; break;
                case 4:_saveStates[x][y] = 'N'; break;
                case 5:_saveStates[x][y] = 'N'; break;
                case 6:_saveStates[x][y] = 'N'; break;
                case 7:_saveStates[x][y] = 'A'; break;
                case 8:_saveStates[x][y] = 'A'; break;
                case 9:_saveStates[x][y] = 'A'; break;
                default:_saveStates[x][y] = 'Z'; break;
                }
                _panel1.add(button);
                _buttons[x][y] = button;
            }
        }
        //Points panel bottom
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 3));
        add(panel2, BorderLayout.SOUTH);
        
        JPanel panel3 = new JPanel();
        panel2.add(panel3);
        _pointsLabel = new JLabel();
        _points = 10;
        _pointsLabel.setText("Points: " + _points);
        panel3.add(_pointsLabel);
        _lastClickX = -1;
        _lastClickY = -1;
        _lastClickTile = 'E';
    }
    
    private void refreshPoints()
    {
    	_pointsLabel.setText("Points: " + _points);
    }

    private void createMenu() 
    {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu game = new JMenu("Game");
        JMenu difficulty = new JMenu("Difficulty");
        JMenu mode = new JMenu("Mode");
        JMenu help = new JMenu("Help");
        menuBar.add(game);
        menuBar.add(difficulty);
        menuBar.add(mode);
        menuBar.add(help);
        
        // New Game
        JMenuItem newGame = new JMenuItem("New Game");
        game.add(newGame);
        //game.addSeparator();
        
        // Difficulty
        JMenuItem normal = new JMenuItem("Default");
        difficulty.add(normal);
        //difficulty.addSeparator();
        
        // Difficulty
        JMenuItem regular = new JMenuItem("Default");
        JMenuItem andrewsMode = new JMenuItem("Andrew's Mode");
        mode.add(regular);
        mode.add(andrewsMode);
        //difficulty.addSeparator();
        
        // Help
        JMenuItem info = new JMenuItem("Info");
        help.add(info);
        //help.addSeparator();
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
			if((_lastClickY != -1 && _lastClickX != -1) && (x == _lastClickX && y == _lastClickY))
			{
				return;
			}
			BufferedImage bethImage = null;
			BufferedImage noahImage = null;
			BufferedImage andrewImage = null;
			BufferedImage zachImage = null;
			try {
				bethImage = ImageIO.read(getClass().getResource("resources/BethNew.bmp"));
				noahImage = ImageIO.read(getClass().getResource("resources/NoahNew.bmp"));
				andrewImage = ImageIO.read(getClass().getResource("resources/AndrewNew.bmp"));
				zachImage = ImageIO.read(getClass().getResource("resources/ZachNew.bmp"));
			} catch (IOException error) {}
				switch(_saveStates[x][y])
				{
				case 'B':
					if(_lastClickTile == 'B')
					{
						_buttons[x][y].setIcon(new ImageIcon(bethImage));
						_saveStates[_lastClickX][_lastClickY] = 'F';
						_saveStates[x][y] = 'F';
						_lastClickX = -1;
				        _lastClickY = -1;
					    _lastClickTile = 'E';
					    _points = _points + 10;
					    refreshPoints();
						break;
					}
					else if(_lastClickTile =='E')
					{
						_buttons[x][y].setIcon(new ImageIcon(bethImage));
		            	_lastClickX = x;
		                _lastClickY = y;
		            	_lastClickTile = 'B';
		            	break;
					}
					else
					{
						_buttons[x][y].setIcon(new ImageIcon(bethImage));
						try {Thread.sleep(1000);} catch (InterruptedException e2) {}
						_buttons[_lastClickX][_lastClickY].setIcon(null);
						_buttons[x][y].setIcon(null);
						_lastClickX = -1;
				        _lastClickY = -1;
					    _lastClickTile = 'E';
					    _points = _points - 3;
					    refreshPoints();
						break;
					}
				case 'N':
					if(_lastClickTile == 'N')
					{
						_buttons[x][y].setIcon(new ImageIcon(noahImage));
						_saveStates[_lastClickX][_lastClickY] = 'F';
						_saveStates[x][y] = 'F';
						_lastClickX = -1;
				        _lastClickY = -1;
					    _lastClickTile = 'E';
					    _points = _points + 10;
					    refreshPoints();
						break;
					}
					else if(_lastClickTile =='E')
					{
		            	_buttons[x][y].setIcon(new ImageIcon(noahImage));
		            	_lastClickX = x;
		                _lastClickY = y;
		            	_lastClickTile = 'N';
		            	break;
					}
					else
					{
						_buttons[x][y].setIcon(new ImageIcon(noahImage));
						try {Thread.sleep(1000);} catch (InterruptedException e2) {}
						_buttons[_lastClickX][_lastClickY].setIcon(null);
						_buttons[x][y].setIcon(null);
						_lastClickX = -1;
				        _lastClickY = -1;
					    _lastClickTile = 'E';
					    _points = _points - 3;
					    refreshPoints();
						break;
					}
				case 'A':
					if(_lastClickTile == 'A')
					{
						_buttons[x][y].setIcon(new ImageIcon(andrewImage));
						_saveStates[_lastClickX][_lastClickY] = 'F';
						_saveStates[x][y] = 'F';
						_lastClickX = -1;
				        _lastClickY = -1;
					    _lastClickTile = 'E';
					    _points = _points + 10;
					    refreshPoints();
						break;
					}
					else if(_lastClickTile =='E')
					{
		            	_buttons[x][y].setIcon(new ImageIcon(andrewImage));
		            	_lastClickX = x;
		                _lastClickY = y;
		            	_lastClickTile = 'A';
		            	break;
					}
					else
					{
						_buttons[x][y].setIcon(new ImageIcon(andrewImage));
						try {Thread.sleep(1000);} catch (InterruptedException e2) {}
						_buttons[_lastClickX][_lastClickY].setIcon(null);
						_buttons[x][y].setIcon(null);
						_lastClickX = -1;
				        _lastClickY = -1;
					    _lastClickTile = 'E';
					    _points = _points - 3;
					    refreshPoints();
						break;
					}
				case 'Z':
					if(_lastClickTile == 'Z')
					{
						_buttons[x][y].setIcon(new ImageIcon(zachImage));
						_saveStates[_lastClickX][_lastClickY] = 'F';
						_saveStates[x][y] = 'F';
						_lastClickX = -1;
				        _lastClickY = -1;
					    _lastClickTile = 'E';
					    _points = _points + 25;
					    refreshPoints();
						break;
					}
					else if(_lastClickTile =='E')
					{
		            	_buttons[x][y].setIcon(new ImageIcon(zachImage));
		            	_lastClickX = x;
		                _lastClickY = y;
		            	_lastClickTile = 'Z';
		            	break;
					}
					else
					{
						_buttons[x][y].setIcon(new ImageIcon(zachImage));
						try {Thread.sleep(1000);} catch (InterruptedException e2) {}
						_buttons[_lastClickX][_lastClickY].setIcon(null);
						_buttons[x][y].setIcon(null);
						_lastClickX = -1;
				        _lastClickY = -1;
					    _lastClickTile = 'E';
					    _points = _points - 3;
					    refreshPoints();
						break;
					}
				case 'F':
					break;
				case 'E':
					break;
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
