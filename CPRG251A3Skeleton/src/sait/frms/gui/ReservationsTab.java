
package sait.frms.gui;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import java.io.IOException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sait.frms.gui.MainWindow.TabButtonActionListener;

import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;

/**
 * Holds the components for the reservations tab.
 * 
 */
public class ReservationsTab extends TabBase implements ActionListener {
	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;

	private JList<Reservation> reservationsList;
	private DefaultListModel<Reservation> reservationsModel;
	private ArrayList<Reservation> reservationsArr;

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
	 * 
	 * @throws IOException
	 */
	public ReservationsTab(ReservationManager reservationManager) throws IOException {
		this.reservationManager = reservationManager;
		panel.setLayout(new BorderLayout());

		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);

		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);

		JPanel tabPanel = createSouthPanel();
		panel.add(tabPanel, BorderLayout.SOUTH);
	}

	private JPanel createCenterPanel() throws IOException {
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());

		reservationsModel = new DefaultListModel<>();
		reservationsList = new JList<>(reservationsModel);

		reservationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane = new JScrollPane(this.reservationsList);

		reservationsList.addListSelectionListener(new MyListSelectionListener());
		panel.add(scrollPane);
		return panel;
	}

	/**
	 * Creates the north panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
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

		findReservationsButton = new JButton("Find Reservations");
		findReservationsButton.addActionListener(this);

		searchJPanel.add(codeLabel, BorderLayout.NORTH);
		searchJPanel.add(codeField);
		searchJPanel.add(airlineLabel);
		searchJPanel.add(airlineField);
		searchJPanel.add(nameLabel);
		searchJPanel.add(nameField);
		searchJPanel.add(findReservationsButton);

		searchJPanel.setVisible(true);
		return searchJPanel;

	}

	/* Used when user selects a Reservation from the scoll pane/JList */
	private class MyListSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			Reservation selectedRes = reservationsList.getSelectedValue();
			try {
				MainWindow.changeCodePanel(selectedRes);
			} catch (NullPointerException npe) {
				// TODO Auto-generated catch block
				// npe.printStackTrace();
				System.out.println("NullPointerException at Line: 159");
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == findReservationsButton) {
			System.out.println("Pushed findReservationsButton");
			reservationsModel.clear();
			Reservation resFound = null;
			System.out.println(codeField.getText().toString());
			try {
				// search by name
				if (nameField.getText() != null) {

					resFound = reservationManager.findByName(nameField.getText().toString());
					System.out.println(resFound);
					reservationsModel.addElement(resFound);
				}
				// search by code
				else if (codeField.getText() != null) {

					reservationManager.findByCode(codeField.getText().toString());

					reservationsModel.addElement(resFound);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
