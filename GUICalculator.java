import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
* Program Name: GUICalculator.java
* Purpose: To create GUICalclator objects in memory
* Coder: Joshua Wright, 0777530
* Date: July, 2016
*/
public class GUICalculator extends JFrame
{
	
	// class scope variable decoration
	private JMenuBar menueBar;
	private JMenu fileMenu,convertMenu,helpMenu;
	private JMenuItem exitItem,hexItem, octItem, binItem,howToUseItem, aboutItem;
	private JPanel topPanel,bottomPanel;
	private JButton[] btArray;
	private JButton tempBt;
	private JTextField screenTx;
	private Font font;
	private Calculator cal;
	static public boolean negative;
	
	//no arg constructor 
	public GUICalculator()
	{
		
		//setting the JFrame title using super class constructor and other specifications 
		super("Calculator");
		setSize(300, 365);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		// set look and feel 
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException |
        		InstantiationException |
        		IllegalAccessException |
        		UnsupportedLookAndFeelException ex) {}
		
		
		//variable initializing
		negative  = false;
		cal = new Calculator();
		font = new Font(Font.SANS_SERIF, Font.PLAIN, 22);
		
		
		// setting the menu bar on the Frame and calling the buildMenueBar method
		buildMenueBar();
		setJMenuBar(menueBar);
		

		// adding the top panel to the JFrame and setting its position and calling the buildTopPanel method
		buildTopPanel();
		add(topPanel,BorderLayout.NORTH);
		
		
		// adding the bottom panel to the JFrame and calling the buildBottomPanel method
		buildBottomPanel();
		add(bottomPanel,BorderLayout.CENTER);
		
		
		//calling the pack method and setting the JFrame visible to true
		pack();
		setVisible(true);
		
	}// end constructor 

	
	/**
	* Program Name: NumActionListener.java
	* Purpose: inner class To handle the events of the numeric buttons
	* Coder: Joshua Wright, 0777530
	* Date: July, 2016
	*/
	private class NumActionListener implements ActionListener
	{
		/**
		* Method Name: actionPerformed
		* Purpose: performs actions for numeric buttons
		* Parameters: ActionEvent 
		* Returns: void
		*/
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				//initializing a temporary button
				tempBt = (JButton)e.getSource();
				
				// test so that the user cannot add more than one decimal point
				if(e.getSource() != btArray[10] && cal.isDecimalPressed() == true ||  cal.isDecimalPressed() == false )
				{
					screenTx.setText(cal.buildOperand(tempBt.getText()));
				}
				
				//test to see if the decimal point button is pressed
				if(e.getSource() == btArray[10])
				{
					cal.setDecimalPressed(true);
				}
			} 
			catch (LongOperandException e1)
			{
				// calling the clear method and showing error message to the user
				cal.Clear();
				screenTx.setText(e1.getMessage());
			}	
			
		}// end method
	}// end class 
	
	
	/**
	* Program Name: NumActionListener.java
	* Purpose: inner class To handle the events of the non numeric buttons
	* Coder: Joshua Wright, 0777530
	* Date: July, 2016
	*/
	private class OtherActionListener implements ActionListener
	{	
		/**
		* Method Name: actionPerformed
		* Purpose: performs actions for non numeric buttons
		* Parameters: ActionEvent 
		* Returns: void
		*/
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				// if-else block testing for specific operator buttons 
				// calling the buildExperssion method for each button 
				// resetting negative
				if(e.getSource() == btArray[21])
				{	
						cal.buildExpression("+");
						negative  = false;
				}
				else if(e.getSource() == btArray[20])
				{	
						cal.buildExpression("-");
						negative  = false;
				}	
				else if(e.getSource() == btArray[19])
				{
						cal.buildExpression("x");
						negative  = false;
				}	
				else if(e.getSource() == btArray[18])
				{
						cal.buildExpression("/");
						negative  = false;
				}
			}
			catch (EmptyOperandExeption e1)
			{
				// calling the clear method and showing error message to the user
				screenTx.setText(e1.getMessage());
				cal.Clear();
			}
			
			
			// calculate button
			if(e.getSource() == btArray[22])
			{
				try
				{
					//calling the calculate method and setting the screen to its result 
					screenTx.setText(cal.calculate()+"");
					cal.setOperand(screenTx.getText());
				} 
				catch (EmptyOperandExeption e2)
				{
					screenTx.setText(e2.getMessage());	
				}
				catch (InvalidOperandExeption e2)
				{
					screenTx.setText("Cannot divide by zero.");	
					//calling the clear method
					cal.Clear();
				}
				catch (LongOperandException e1)
				{
					// calling the clear method and showing error message to the user
					cal.Clear();
					screenTx.setText(e1.getMessage());
				}
			}
			
			//Backspace button
			else if(e.getSource() == btArray[12])
			{	
				try
				{
					//calling the backspace method and setting its result to the screen
					screenTx.setText(cal.backspace(screenTx.getText()));
					//calling the setOperand Method to the current screenTx
					cal.setOperand(screenTx.getText());
				} 
				catch (EmptyOperandExeption | LongOperandException e1)
				{
					// calling the clear method and showing error message to the user
					cal.Clear();
					screenTx.setText(e1.getMessage());
				}	
			}
			
			// clear button
			else if(e.getSource() == btArray[11])
			{
				// calling the clear method 
				cal.Clear();
				// reseting the screenTx's text
				screenTx.setText("0.0");
				// reseting the toggleCounter to 0
				negative  = false;
			}
			
			// fund Squared button
			else if(e.getSource() == btArray[15])
			{
				try
				{
					// calling the findSquared method and 
					//setting its result to operand using the setOperand method
					cal.setOperand(cal.findSquared(cal.getOperand()));
					//setting the screenTx's text to current operand using the get operand method
					screenTx.setText(cal.getOperand());
				} 
				catch (LongOperandException e1)
				{
					// calling the clear method and showing error message to the user
					cal.Clear();
					screenTx.setText(e1.getMessage());
				}	
			}
				
			// find Square root button
			else if(e.getSource() == btArray[16])
			{
				try
				{
					// calling the findSquareRoot method and 
					//setting its result to operand using the setOperand method
					cal.setOperand(cal.findSquareRoot(cal.getOperand()));
					//setting the screenTx's text to current operand using the get operand method
					screenTx.setText(cal.getOperand());
				} 
				catch (LongOperandException | InvalidOperandExeption e1)
				{
					// calling the clear method and showing error message to the user
					cal.Clear();
					screenTx.setText(e1.getMessage());
				}	
			}
			
			// toggle plusMinue button
			else if(e.getSource() == btArray[14])
			{
				
				// test to only add a minus sign  if there is none
				if(negative  == false)
				{
					try
					{
						screenTx.setText(cal.togglePlusMinus(true));
						cal.setOperand(screenTx.getText());
						// setting negative
						negative = true;
					} 
					catch (LongOperandException | EmptyOperandExeption e1)
					{
						// calling the clear method and showing error message to the user
						cal.Clear();
						screenTx.setText(e1.getMessage());
					}
				}
				else
				{
					try
					{
						// calling the togglePlusMinue method passing it false
						cal.setOperand(cal.togglePlusMinus(false));
						//setting the screenTx's text to current operand using the get operand method
						screenTx.setText(cal.getOperand());
						// setting negative
						negative  = false;
					}
					catch (LongOperandException | EmptyOperandExeption e1)
					{
						// calling the clear method and showing error message to the user
						cal.Clear();
						screenTx.setText(e1.getMessage());
					}	
				}	
			}
			

			// find percentage button
			else if(e.getSource() == btArray[13])
			{
				try 
				{
					// calling method to find percentage and set the screenTx's text to its result
					screenTx.setText(cal.findPercentage(screenTx.getText()));
					cal.setOperand(screenTx.getText());
				}
				catch (LongOperandException | EmptyOperandExeption e1)
				{
					// calling the clear method and showing error message to the user
					cal.Clear();
					screenTx.setText(e1.getMessage());
				}	
			}// end if-else block
			
		}// end method	
	}// end class
	
	
	/**
	* Method Name: buildMenueBar
	* Purpose: builds menu bar
	* Parameters: none 
	* Returns: void
	*/
	private void buildMenueBar()
	{
		//variable initializing
		menueBar = new JMenuBar();
		fileMenu = new JMenu("Flie");
		convertMenu = new JMenu("Convert");
		helpMenu = new JMenu("Help");
		exitItem = new JMenuItem("Exit");
		hexItem = new JMenuItem("Hex");
		octItem = new JMenuItem("Oct");
		binItem = new JMenuItem("Bin");
		howToUseItem = new JMenuItem("How To Use");
		aboutItem = new JMenuItem("About");
		
		// adding Action Listener to exitItem 
		exitItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(1);
			}//end method		
		}// end inner class
		);

		// adding Action Listener to aboutItem
		aboutItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(rootPane,
						"This program was created by Joshua Wright\nDate : July,20,2016\nContact : J_Wright5@fanshaweonline.ca");
			}// end method		
		}// end inner class
		);

		// adding Action Listener to howToUseItem
		howToUseItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(rootPane,
						"This program is a calculator which applies the rules of BEDMAS to its operand's. "
								+ "Use as a reguler Calculator");
			}// end method			
		}// end inner class
		);

		// adding Action Listener to binItem
		binItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					// calling the convert to binary method and setting its result to screenTx
					screenTx.setText(cal.convertBin(screenTx.getText()));
				} 
				catch (EmptyOperandExeption | InvalidOperandExeption e1)
				{
					screenTx.setText(e1.getMessage());
				}
				finally
				{
					// calling the clear method
					cal.Clear();
				}
			}// end method		
		}// end inner class
		);

		// adding Action Listener to hexItem
		hexItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					// calling the convert to Hex method and setting its result to screenTx
					screenTx.setText(cal.convertHex(screenTx.getText()));
				} 
				catch (EmptyOperandExeption e1)
				{
					screenTx.setText(e1.getMessage());
				}
				finally
				{
					// calling the clear method
					cal.Clear();
				}
			}// end method			
		}// end inner class
		);

		// adding Action Listener to octItem 
		octItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					// calling the convert to oct method and setting its result to screenTx
					screenTx.setText(cal.convertOct(screenTx.getText()));
				} 
				catch (EmptyOperandExeption | InvalidOperandExeption e1)
				{
					screenTx.setText(e1.getMessage());
				} 
				finally
				{
					// calling the clear method
					cal.Clear();
				}
			}// end method			
		}// end inner class
		);


		// adding exitItem to fileMenu
		fileMenu.add(exitItem);

		// adding various items to the convert menu 
		convertMenu.add(hexItem);
		convertMenu.add(octItem);
		convertMenu.add(binItem);

		// adding various items to the help menu
		helpMenu.add(howToUseItem);
		helpMenu.add(aboutItem);

		// adding the various menu's to the menu bar
		menueBar.add(fileMenu);
		menueBar.add(convertMenu);
		menueBar.add(helpMenu);
				
	}// end method
	
	/**
	* Method Name: buildTopPanel
	* Purpose: builds top panel
	* Parameters: none 
	* Returns: void
	*/
	private void buildTopPanel()
	{
		//variable initializing
		topPanel = new JPanel();
		screenTx = new JTextField("0.0",15);
		
		// Setting various specifications for the JTextFeild 
		screenTx.setDisabledTextColor(Color.BLACK);
		screenTx.setEditable(false);
		screenTx.setBackground(Color.WHITE);
		screenTx.setFont(font);
		screenTx.setHorizontalAlignment(screenTx.RIGHT);
		screenTx.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			
		// setting the size of the top panel and adding the JTextFeild to it
		topPanel.setPreferredSize(new Dimension(300,40));
		topPanel.add(screenTx);
		
	}// end method
	
	/**
	* Method Name: buildBottomPanel
	* Purpose: builds bottom panel
	* Parameters: none
	* Returns: void
	*/
	private void buildBottomPanel()
	{
		//variable initializing
		bottomPanel = new JPanel();
		btArray =  new JButton[24];
		tempBt = new JButton();

		// initializing  the all the numeric buttons 
		for(int i = 0;i < 10 ; i++)
			btArray[i] = new JButton(""+i);

		//Manual initializing the other buttons 
		btArray[10] =  new JButton(".");
		btArray[11] =  new JButton("C");
		btArray[12] =  new JButton();
		btArray[13] =  new JButton("%");
		btArray[14] =  new JButton("+/-");
		btArray[15] =  new JButton();
		btArray[16] =  new JButton();
		btArray[17] =  new JButton("");
		btArray[18] =  new JButton("/");
		btArray[19] =  new JButton("x");
		btArray[20] =  new JButton("-");
		btArray[21] =  new JButton("+");
		btArray[22] =  new JButton("=");
		btArray[23] =  new JButton("");

		// setting the color and font of all buttons
		for(int i = 0 ; i < 24; i++)
		{
			btArray[i].setBackground(Color.WHITE);
			btArray[i].setFont(font);
		}
				
		// adding action Listeners to buttons 
		for(int i = 0; i < 11; i++)
		btArray[i].addActionListener(new NumActionListener());		
		btArray[23].addActionListener(new NumActionListener());		
		for(int i = 11 ; i < 24 ;i++)
			btArray[i].addActionListener(new OtherActionListener());
					
		//setting the image icons for some buttons
		btArray[12].setIcon(new ImageIcon("Icons/backspace.png"));
		btArray[15].setIcon(new ImageIcon("Icons\\sqr.png"));
		btArray[16].setIcon(new ImageIcon("Icons\\sqrt.png"));
				
		// adding the JButtons to the bottom panel in a specific order
		for(int i = 11 ; i < 19; i++)
			bottomPanel.add(btArray[i]);
				
		for(int i = 7 ; i < 10; i++)
			bottomPanel.add(btArray[i]);
				
		bottomPanel.add(btArray[19]);
				
		for(int i = 4 ; i < 7; i++)
			bottomPanel.add(btArray[i]);
				
		bottomPanel.add(btArray[20]);
				
		for(int i = 1 ; i < 4; i++)
			bottomPanel.add(btArray[i]);
				
		bottomPanel.add(btArray[21]);
		bottomPanel.add(btArray[23]);
		bottomPanel.add(btArray[0]);
		bottomPanel.add(btArray[10]);
		bottomPanel.add(btArray[22]);
				
				
		//setting the layout of the bottom panel to GridLayout
		bottomPanel.setLayout(new GridLayout(6,4));
				
		// setting the size of the bottom panel
		bottomPanel.setPreferredSize(new Dimension(287,267));
				
	}// end method
	
	/**
	* Method Name: main
	* Purpose: to launch GUICalculator
	* Parameters: String 
	* Returns: void
	*/
	public static void main(String[] args)
	{
		// create a GUICalculator object in memory
		GUICalculator c = new GUICalculator();
	}// end method
	
		
}// end of class
