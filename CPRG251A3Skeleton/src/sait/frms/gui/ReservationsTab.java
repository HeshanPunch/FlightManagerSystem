
package sait.frms.gui;

import java.awt.*;
import javax.swing.*;

import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Reservation;

/**
 * Holds the components for the reservations tab.
 * 
 */
public class ReservationsTab extends TabBase {
	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;
	
	private JList<Reservation> reservationsList;
	
	private JLabel header;

	private JLabel codeLabel;

	private JLabel airlineLabel;

	private JLabel nameLabel;

	private JTextField codeField;

	private JTextField airlineField;

	private JTextField nameField;

	private JButton findReservationsButton;

	
	/**
	 * Creates the components for reservations tab.
	 */
	public ReservationsTab(ReservationManager reservationManager) {
		this.reservationManager = reservationManager;
		panel.setLayout(new BorderLayout());
		
		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);
		
		//JPanel centerPanel = createCenterPanel();
		//panel.add(centerPanel, BorderLayout.CENTER);
		
		JPanel tabPanel = createSouthPanel();
		panel.add(tabPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Creates the north panel.
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() 
	{
		JPanel panel = new JPanel();
		
		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		
		return panel;
	}
	
	private JPanel createSouthPanel() {
		JPanel tabPanel = new JPanel();

		tabPanel.setLayout(new BorderLayout());
		findReservationsButton = new JButton("Find Reservations");

		//findFlightsButton.addActionListener(new TabButtonActionListener());
		// reservationsButton.addActionListener(new TabButtonActionListener());
		header = new JLabel("Search");
		header.setFont(new Font("serif", Font.PLAIN, 20));
		tabPanel.add(header, BorderLayout.NORTH);
		tabPanel.add(createSearchJPanel(), BorderLayout.CENTER);
		tabPanel.add(findReservationsButton, BorderLayout.SOUTH);

		return tabPanel;
	}
	
	private JPanel createSearchJPanel() {
		JPanel searchJPanel = new JPanel();
		
		searchJPanel.setLayout(new GridLayout(3, 2));

		codeLabel = new JLabel("Code");
		airlineLabel = new JLabel("Airline");
		nameLabel = new JLabel("Name");
		codeField = new JTextField();
		airlineField = new JTextField();
		nameField = new JTextField();

		//findReservationsButton = new JButton("Find Reservations");

		// flightsButton.addActionListener(new TabButtonActionListener());
		// reservationsButton.addActionListener(new TabButtonActionListener());

		// tabPanel.add(flightsButton);
		//searchJPanel.add(findReservationsButton);

		searchJPanel.add(codeLabel, BorderLayout.NORTH);
		searchJPanel.add(codeField);
		searchJPanel.add(airlineLabel);
		searchJPanel.add(airlineField);
		searchJPanel.add(nameLabel);
		searchJPanel.add(nameField);

		searchJPanel.setVisible(true);
		return searchJPanel;

	}
	
}
