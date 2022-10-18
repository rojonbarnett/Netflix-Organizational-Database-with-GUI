package netflixGui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.color.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextField;

import project1.ShowWeek;
import project1.Shows;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
public class netflixPanel extends JPanel{
	private int count;
	private JButton push;
	private JTextField movieTitle;
	private Shows allData;
	private ShowWeek editShowWeek;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnWeek;
	private JRadioButton rdbtnChangePurge;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private ImageIcon image;
	

	public netflixPanel() {
		allData = new Shows("allData","./project1/netflixAllWeeksGlobalProcessed.txt");
		//System.out.println(allData);
		setLayout(null);
		count = 0;
		setBackground(new Color(255, 0, 0));
		setPreferredSize(new Dimension(678, 433));
		
		//Movie title text field
		movieTitle = new JTextField("2021-07-04");
		movieTitle.setBounds(30, 121, 107, 20);
		add(movieTitle);
		movieTitle.setColumns(10);
		
		//Week text box label
		JLabel lblNewLabel = new JLabel("Enter a Week: ");
		lblNewLabel.setBounds(30, 104, 97, 23);
		add(lblNewLabel);
		
		//Create ScrollPane for Text Area
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(228, 102, 170, 207);
		add(scrollPane);
		
		//Creates Text Area
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setText(allData.toString());
		
		//Movie Combo Box 
		JComboBox Movies = new JComboBox();
		ArrayList<ShowWeek> moviesInWeek = allData.getOneWeek("2021-07-04");
		
		//Creates an array called data. Will hold all of the movie titles
		String [] data = new String[moviesInWeek.size()];
		int index = 0;
		
		//Loop through all of the movie titles. Stores movie titles in data array
		for (ShowWeek sw : moviesInWeek)
		{
			data[index] = sw.getShowTitle();
			index++;
		}
		//Populates the drop down menu with all of the titles with the provided date
		Movies.setModel(new DefaultComboBoxModel(data));
		Movies.setBounds(30, 198, 107, 22);
		add(Movies);
		
		//Shows label
		JLabel lblNewLabel_1 = new JLabel("Shows");
		lblNewLabel_1.setBounds(30, 184, 46, 14);
		add(lblNewLabel_1);
		
		//Get Shows Button
		JButton btnNewButton_2 = new JButton("Get Shows");
		//Action Listener for Get Shows Button
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Creates an ArrayList of all the movies with the user-inputed date
				ArrayList<ShowWeek> moviesInWeek = allData.getOneWeek(movieTitle.getText());
				if(moviesInWeek != null && moviesInWeek.size() > 0)
				{
					String [] data = new String[moviesInWeek.size()];
					int index = 0;
					//Loop through all of the movie titles
					for (ShowWeek sw : moviesInWeek)
					{
						data[index] = sw.getShowTitle();
						index++;
					}
					Movies.setModel(new DefaultComboBoxModel(data));
				}
				else 
				//Displays dialogue box with error message if incorrect data is entered
				{
					JOptionPane.showMessageDialog(btnNewButton_2, "ERROR: Please Enter a valid week");
				}
			}
		});
		btnNewButton_2.setBounds(30, 152, 107, 23);
		add(btnNewButton_2);
		
		//"Show Finder" title label
		JLabel lblNewLabel_2 = new JLabel("Show Finder");
		lblNewLabel_2.setFont(new Font("Showcard Gothic", Font.BOLD, 20));
		lblNewLabel_2.setBounds(30, 62, 153, 31);
		add(lblNewLabel_2);
		
		//Show list label
		JLabel lblNewLabel_3 = new JLabel("Show List");
		lblNewLabel_3.setFont(new Font("Showcard Gothic", Font.BOLD, 20));
		lblNewLabel_3.setBounds(250, 58, 130, 39);
		add(lblNewLabel_3);
		
		//Title radio button
		rdbtnNewRadioButton = new JRadioButton("Title");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(30, 284, 109, 23);
		add(rdbtnNewRadioButton);
		
		//Week Radio Button
		rdbtnWeek = new JRadioButton("Week");
		buttonGroup.add(rdbtnWeek);
		rdbtnWeek.setBounds(30, 325, 109, 23);
		add(rdbtnWeek);
		
		//Change Purge radio button
		rdbtnChangePurge = new JRadioButton("Change Purge");
		buttonGroup.add(rdbtnChangePurge);
		rdbtnChangePurge.setBounds(30, 368, 109, 23);
		add(rdbtnChangePurge);
		
		//Do edit button
		JButton btnNewButton = new JButton("Do Edit");
		//Do edit button action listener
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNewRadioButton.isSelected())
				{
					//Displays dialogue box that allows user to edit the title
					String newTitle = JOptionPane.showInputDialog("What would you like to change the title to?");
					//Gets the original movie title from the drop down box
					String title = String.valueOf(Movies.getSelectedItem());
					//Creates a new variable that contains the selected movie data
					editShowWeek = allData.find(title, movieTitle.getText());
					//Changes the showTitle to what the user wants it to be
					editShowWeek.setShowTitle(newTitle);
				}
				else if(rdbtnWeek.isSelected())
				{
					//Displays dialogue box that allows user to edit the week
					String newWeek = JOptionPane.showInputDialog("What would you like to change the week to?");
					//Gets the original movie title from the drop down box
					String title = String.valueOf(Movies.getSelectedItem());
					//Creates a new variable that contains the selected movie data
					editShowWeek = allData.find(title, movieTitle.getText());
					//Changes the show week to what the user wants it to be
					editShowWeek.setWeek(newWeek);
				}
				else if (rdbtnChangePurge.isSelected())
				{
					//Gets the original movie title from the drop down box
					String title = String.valueOf(Movies.getSelectedItem());
					//Creates a new variable that contains the selected movie data
					editShowWeek = allData.find(title, movieTitle.getText());
					//Checks purge status
					if(editShowWeek.isPurged() == true)
					{
						allData.unPurgeShow(title);
					}
					else
					{
						allData.purgeShow(title);
					}
					JOptionPane.showMessageDialog(btnNewButton, "Purge has been changed");
					
				}
				else
				{
					JOptionPane.showMessageDialog(btnNewButton, "ERROR: Must select one of the radio buttons to use 'Do edit' button function");
				}
			}
			
		});
		btnNewButton.setBounds(170, 325, 89, 23);
		add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon(netflixPanel.class.getResource("/netflixGui/netflixLogo.jpg")));
		lblNewLabel_4.setBounds(431, 84, 203, 252);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Show Editor");
		lblNewLabel_5.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(30, 244, 153, 33);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Netflix Gui Editor");
		lblNewLabel_6.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));
		lblNewLabel_6.setBounds(30, 11, 452, 50);
		add(lblNewLabel_6);
		
		
	}
	
	
	
	
	
	//Writes the changed data to the file
	public void doClose() {
		allData.doWrite("./textwrite.csv");
	}
}
