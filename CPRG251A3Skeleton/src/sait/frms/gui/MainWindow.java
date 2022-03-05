package sait.frms.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.http.HttpHeaders;

import javax.management.loading.PrivateClassLoader;
import javax.swing.*;

import sait.frms.manager.FlightManager;
import sait.frms.manager.ReservationManager;

/**
 * The main window (JFrame).
 * 
 */
public class MainWindow extends JFrame {
	private static final String TAB_FLIGHTS = "flights";
	private static final String TAB_RESERVATIONS = "reservations";

	/**
	 * Holds the flight manager.
	 */
	private FlightManager flightManager;

	/**
	 * Holds the reservation manager.
	 */
	private ReservationManager reservationManager;

	/**
	 * Card layout to display tab content.
	 */
	private CardLayout cardLayout;

	/**
	 * North panel.
	 */
	private JPanel northPanel;

	/**
	 * Center panel.
	 */
	private JPanel centerPanel;

	/**
	 * South panel.
	 */
	private JPanel southPanel;

	/**
	 * Flights tab button.
	 */
	private JButton flightsButton;

	/**
	 * Reservations tab button.
	 */
	private JButton reservationsButton;

	/**
	 * Flights tab panel.
	 */
	private TabBase flightsTab;

	/**
	 * Reservations tab panel.
	 */

	private JPanel eastPanel;

	private JPanel codePanel;

	private TabBase reservationsTab;

	private JLabel header;

	private JLabel fromJLabel;

	private JLabel tolJLabel;

	private JLabel dayJLabel;

	private JComboBox fromBox;

	private JComboBox toBox;

	private JComboBox dayBox;

	private JButton findFlightsButton;

	private JButton findReservationsButton;

	private JButton updateButton;

	private JLabel code;

	private JTextField codeTextField;

	private JLabel flight;

	private JTextField flighTextField;

	private JLabel airline;

	private JTextField airlineTextField;

	private JLabel cost;

	private JTextField costTextField;

	private JLabel name;

	private JTextField nameTextField;

	private JLabel citizenship;

	private JTextField citizenshipTextField;

	private JLabel status;

	private JComboBox statusJComboBox;

	/**
	 * Creates the Main Window and any components inside it.
	 */

	public MainWindow() {
		this.flightManager = new FlightManager();
		this.reservationManager = new ReservationManager();

		setTitle("Flight Reservation Management System");

		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		northPanel = createNorthPanel();
		add(northPanel, BorderLayout.PAGE_START);

		centerPanel = createCenterPanel();
		add(centerPanel, BorderLayout.CENTER);

		eastPanel = createEastPanel();
		add(eastPanel, BorderLayout.LINE_END);

		southPanel = createSouthPanel();
		add(southPanel, BorderLayout.PAGE_END);
	}

	/**
	 * Creates the north panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());

		JPanel tabPanel = createTabPanel();
		panel.add(tabPanel, BorderLayout.SOUTH);

		return panel;
	}

	/**
	 * Creates the south panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());

		// JPanel tabPanel = createSearchPanel();
		// panel.add(tabPanel, BorderLayout.SOUTH);

		return panel;
	}

	/**
	 * Creates the center panel.
	 * 
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();

		cardLayout = new CardLayout();

		this.flightsTab = new FlightsTab(this.flightManager, this.reservationManager);
		this.reservationsTab = new ReservationsTab(this.reservationManager);

		panel.setLayout(cardLayout);

		panel.add(flightsTab.getPanel(), TAB_FLIGHTS);
		panel.add(reservationsTab.getPanel(), TAB_RESERVATIONS);

		cardLayout.first(panel);

		return panel;
	}

	/**
	 * Creates the east panel.
	 * 
	 * @return JPanel that goes east.
	 */
	private JPanel createEastPanel() {

		JPanel eastPanel = new JPanel();

		eastPanel.setLayout(new BorderLayout());
		eastPanel.setPreferredSize(new Dimension(250,100));
		header = new JLabel("Reserve");

		updateButton = new JButton("Update");

		eastPanel.add(header, BorderLayout.NORTH);
		eastPanel.add(updateButton, BorderLayout.PAGE_END);
		codePanel = createCodePanel();
		eastPanel.add(codePanel, BorderLayout.LINE_END);

		return eastPanel;
	}

	private JPanel createCodePanel() {
		
		String[] statusStrings = { "Active" };
		JPanel codePanel = new JPanel();
		codePanel.setLayout(new GridLayout(7, 2));
		code = new JLabel("Code");
		codeTextField = new JTextField(5);
		codeTextField.setEnabled(false);
		flight = new JLabel("Flight");
		flighTextField = new JTextField(10);
		flighTextField.setEnabled(false);
		airline = new JLabel("Airline");
		airlineTextField = new JTextField(10);
		airlineTextField.setEnabled(false);
		cost = new JLabel("Cost");
		costTextField = new JTextField(10);
		costTextField.setEnabled(false);
		name = new JLabel("Name");
		nameTextField = new JTextField(10);
		nameTextField.setEnabled(true);
		citizenship = new JLabel("Citizenship");
		citizenshipTextField = new JTextField(10);
		citizenship.setEnabled(true);
		status = new JLabel("Status");
		statusJComboBox = new JComboBox(statusStrings);
				
		codePanel.add(code);
		codePanel.add(codeTextField);
		codePanel.add(flight);
		codePanel.add(flighTextField);
		codePanel.add(airline);
		codePanel.add(airlineTextField);
		codePanel.add(cost);
		codePanel.add(costTextField);
		codePanel.add(name);
		codePanel.add(nameTextField);
		codePanel.add(citizenship);
		codePanel.add(citizenshipTextField);
		codePanel.add(status);
		codePanel.add(statusJComboBox);
		
		return codePanel;
	}

	/**
	 * Creates the tab buttons.
	 * 
	 * @return JPanel containing tab buttons.
	 */
	private JPanel createTabPanel() {
		JPanel tabPanel = new JPanel();

		tabPanel.setLayout(new GridLayout(1, 2));

		flightsButton = new JButton("Flights");
		reservationsButton = new JButton("Reservations");

		flightsButton.addActionListener(new TabButtonActionListener());
		reservationsButton.addActionListener(new TabButtonActionListener());

		tabPanel.add(flightsButton);
		tabPanel.add(reservationsButton);

		return tabPanel;
	}

	/**
	 * Creates the tab buttons.
	 * 
	 * @return JPanel containing tab buttons.
	 */
	/*
	 * private JPanel createSearchPanel() { JPanel tabPanel = new JPanel(); String[]
	 * fromCity = {"YYC"}; String[] dayStrings = {"Any"};
	 * 
	 * tabPanel.setLayout(new GridLayout(5,2));
	 * 
	 * flightsButton.addActionListener(new TabButtonActionListener());
	 * reservationsButton.addActionListener(new TabButtonActionListener()); header =
	 * new JLabel("Flight Finder", SwingConstants.CENTER); header.setFont(new
	 * Font("serif", Font.PLAIN, 20)); fromJLabel = new JLabel("From",
	 * SwingConstants.LEFT); tolJLabel = new JLabel("To", SwingConstants.LEFT);
	 * dayJLabel = new JLabel("Day", SwingConstants.LEFT); fromBox = new
	 * JComboBox(fromCity); toBox = new JComboBox(fromCity); dayBox = new
	 * JComboBox(dayStrings);
	 * 
	 * flightsButton.addActionListener(new TabButtonActionListener());
	 * reservationsButton.addActionListener(new TabButtonActionListener());
	 * 
	 * tabPanel.add(flightsButton); tabPanel.add(reservationsButton);
	 * tabPanel.add(header); tabPanel.add(fromJLabel); tabPanel.add(fromBox);
	 * tabPanel.add(tolJLabel); tabPanel.add(toBox); tabPanel.add(dayJLabel);
	 * tabPanel.add(dayBox);
	 * 
	 * 
	 * return tabPanel; }
	 */

	/**
	 * Displays the JFrame window.
	 */
	public void display() {
		pack();
		setVisible(true);
	}

	/**
	 * Inner action listener class that listens for a tab button to be clicked.
	 * 
	 * @author Nick Hamnett, Mohamed
	 * @version January 2, 2020
	 */
	public class TabButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == flightsButton) {
				cardLayout.show(centerPanel, TAB_FLIGHTS);
			} else if (e.getSource() == reservationsButton) {
				cardLayout.show(centerPanel, TAB_RESERVATIONS);
			}
		}

	}
}
