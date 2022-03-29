package sait.frms.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.*;

import sait.frms.gui.MainWindow.TabButtonActionListener;
import sait.frms.manager.FlightManager;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;
import sait.frms.gui.MainWindow.*;

/**
 * Holds the components for the flights tab.
 * 
 */
public class FlightsTab extends TabBase implements ActionListener {
	/**
	 * Instance of flight manager.
	 */
	private FlightManager flightManager;

	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;

	/**
	 * List of flights.
	 */
	private JList<Flight> flightsList;

	private DefaultListModel<Flight> flightsModel;

	private JLabel header;

	private JLabel fromJLabel;

	private JLabel tolJLabel;

	private JLabel dayJLabel;

	private JComboBox fromBox;

	private JComboBox toBox;

	private JComboBox dayBox;

	private JButton findFlightsButton;

	private JButton findReservationsButton;

	/**
	 * Creates the components for flights tab.
	 */
	/**
	 * Creates the components for flights tab.
	 * 
	 * @param flightManager      Instance of FlightManager.
	 * @param reservationManager Instance of ReservationManager
	 */
	public FlightsTab(FlightManager flightManager, ReservationManager reservationManager) {
		this.flightManager = flightManager;
		this.reservationManager = reservationManager;

		panel.setLayout(new BorderLayout());

		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);

		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);

		JPanel tabPanel = createSouthPanel();
		panel.add(tabPanel, BorderLayout.SOUTH);
	}

	/**
	 * Creates the north panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();

		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);

		return panel;
	}

	/**
	 * Creates the center panel.
	 * 
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());

		flightsModel = new DefaultListModel<>();
		flightsList = new JList<>(flightsModel);


		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(this.flightsList);

		flightsList.addListSelectionListener(new MyListSelectionListener());

		panel.add(scrollPane);

		return panel;
	}

	/**
	 * Creates the tab buttons.
	 * 
	 * @return JPanel containing tab buttons.
	 */
	private JPanel createSouthPanel() {
		JPanel tabPanel = new JPanel();

		tabPanel.setLayout(new BorderLayout());
		findFlightsButton = new JButton("Find Flights");

		header = new JLabel("Flight Finder");
		header.setFont(new Font("serif", Font.PLAIN, 20));
		tabPanel.add(header, BorderLayout.NORTH);
		tabPanel.add(createSearchJPanel(), BorderLayout.CENTER);
		tabPanel.add(findFlightsButton, BorderLayout.SOUTH);

		return tabPanel;
	}

	private JPanel createSearchJPanel() {
		JPanel searchJPanel = new JPanel();
		String[] fromCity = flightManager.getAirportCodes();
		String[] dayStrings = { flightManager.WEEKDAY_ANY, flightManager.WEEKDAY_SUNDAY, flightManager.WEEKDAY_MONDAY,
				flightManager.WEEKDAY_TUESDAY, flightManager.WEEKDAY_WEDNESDAY, flightManager.WEEKDAY_THURSDAY,
				flightManager.WEEKDAY_FRIDAY, flightManager.WEEKDAY_SATURDAY };

		searchJPanel.setLayout(new GridLayout(3, 2));

		fromJLabel = new JLabel("From");
		tolJLabel = new JLabel("To");
		dayJLabel = new JLabel("Day");
		fromBox = new JComboBox(fromCity);
		toBox = new JComboBox(fromCity);
		dayBox = new JComboBox(dayStrings);

		findFlightsButton = new JButton("Find Flights");

		findFlightsButton.addActionListener(this);


		searchJPanel.add(fromJLabel, BorderLayout.NORTH);
		searchJPanel.add(fromBox);
		searchJPanel.add(tolJLabel);
		searchJPanel.add(toBox);
		searchJPanel.add(dayJLabel);
		searchJPanel.add(dayBox);

		searchJPanel.setVisible(true);
		return searchJPanel;

	}

	private class MyListSelectionListener implements ListSelectionListener {
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (flightsList == null) {
				return;
			}

			Flight selectedFlight = flightsList.getSelectedValue();
			MainWindow.changeCodePanel(selectedFlight);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == findFlightsButton) {
			flightsModel.clear();
			ArrayList<Flight> flightList = flightManager.findFlights(fromBox.getSelectedItem().toString(),
					toBox.getSelectedItem().toString(), dayBox.getSelectedItem().toString());
			
			
			for (Flight f : flightList) {
				flightsModel.addElement(f);
			}

		}
	}

}