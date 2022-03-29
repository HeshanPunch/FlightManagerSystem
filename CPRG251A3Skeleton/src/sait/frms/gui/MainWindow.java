package sait.frms.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.http.HttpHeaders;

import javax.management.loading.PrivateClassLoader;
import javax.swing.*;

import sait.frms.manager.FlightManager;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

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
	private static FlightManager flightManager;

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

	// private JButton updateButton;
	private JButton reserveButton;

	private JLabel code;

	private static JTextField codeTextField;

	private JLabel flight;

	private static JTextField flighTextField;

	private static JTextField dayTextField;
	private JLabel day;

	private JLabel airline;

	private static JTextField airlineTextField;

	private JLabel cost;

	private static JTextField costTextField;

	private JLabel name;

	private static JTextField nameTextField;

	private JLabel citizenship;

	private static JTextField citizenshipTextField;

	private JLabel status;

	private JComboBox statusJComboBox;

	/**
	 * Creates the Main Window and any components inside it.
	 * 
	 * @throws IOException
	 */

	public MainWindow() throws IOException {
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
	 * @throws IOException
	 */
	private JPanel createCenterPanel() throws IOException {
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
		eastPanel.setPreferredSize(new Dimension(250, 100));
		header = new JLabel("Reserve");

		reserveButton = new JButton("Reserve");

		eastPanel.add(header, BorderLayout.NORTH);
		eastPanel.add(reserveButton, BorderLayout.PAGE_END);
		codePanel = createCodePanel();
		eastPanel.add(codePanel, BorderLayout.LINE_END);
		reserveButton.addActionListener(new TabButtonActionListener());

		return eastPanel;
	}

	private JPanel createCodePanel() {

		String[] statusStrings = { "Active" };
		JPanel codePanel = new JPanel();
		codePanel.setLayout(new GridLayout(9, 2));
		code = new JLabel("Code");
		codeTextField = new JTextField(5);
		codeTextField.setEnabled(false);
		flight = new JLabel("Flight");
		flighTextField = new JTextField(10);
		flighTextField.setEnabled(false);
		airline = new JLabel("Airline");

		dayTextField = new JTextField(10);
		dayTextField.setEnabled(false);
		day = new JLabel("Day");

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
		codePanel.add(day);
		codePanel.add(dayTextField);
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
			} else if (e.getSource() == reserveButton) {
				try {
					reservationManager.newReservationCode(flighTextField.getText(), nameTextField.getText(),
							citizenshipTextField.getText());

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}

	}

	// used to set the results on the East pane
	public static void changeCodePanel(Flight selectedFlight) {

		codeTextField.setText("");
		flighTextField.setText("");
		airlineTextField.setText("");
		dayTextField.setText("");
		costTextField.setText("");
		nameTextField.setText("");
		citizenshipTextField.setText("");

		if (selectedFlight != null) {

			flighTextField.setText(selectedFlight.getCode());
			flighTextField.setEnabled(true);

			airlineTextField.setText(selectedFlight.getAirlineNameString());
			airlineTextField.setEnabled(true);

			dayTextField.setText(selectedFlight.getWeekday());
			dayTextField.setEnabled(true);

			costTextField.setText(Double.toString(selectedFlight.getCostPerSeat()));
			costTextField.setEnabled(true);
		}

	}

	public static void changeCodePanel(Reservation selectedRes) {

		codeTextField.setText(selectedRes.getCode());
		codeTextField.setEnabled(true);

		flighTextField.setText(selectedRes.getFlightCode());
		flighTextField.setEnabled(true);

		airlineTextField.setText(selectedRes.getAirline());
		airlineTextField.setEnabled(true);

		costTextField.setText(Double.toString(selectedRes.getCost()));
		costTextField.setEnabled(true);

		nameTextField.setText(selectedRes.getName());
		nameTextField.setEnabled(true);

		nameTextField.setText(selectedRes.getName());
		nameTextField.setEnabled(true);

		citizenshipTextField.setText(selectedRes.getCitizenship());
		citizenshipTextField.setEnabled(true);

		// add at the end, needs flight

		String weekday = "";

		Flight foundFlight = new Flight();
		try {
			foundFlight = flightManager.findFlightByCode(selectedRes.getFlightCode());

	
			weekday = foundFlight.getWeekday();
		} catch (NullPointerException n) {
			System.out.println("Can't find weekday for Flight");
			return;

		}

		dayTextField.setText(weekday);
		dayTextField.setEnabled(true);

	}

}
