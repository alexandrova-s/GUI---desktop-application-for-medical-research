package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;

import controller.Controller;

public class MainFrame extends JFrame {

	private FormPanel formPanel;
	private FormPanelB formPanelB;
	private Controller controller;
	private TablePanel tablePanel;

	private boolean helpChoose = false;

	public MainFrame() {
		super("Rejestracja wyników badañ");

		setLayout(new BorderLayout());

		formPanel = new FormPanel();
		formPanelB = new FormPanelB();
		tablePanel = new TablePanel();

		setJMenuBar(createMenuBar());

		controller = new Controller();

		tablePanel.setData(controller.getPatients());

		// ustawienia przycisku dodaj
		tablePanel.setAddBtnListner(() -> {
			helpChoose = true;
			formPanel.setVisible(true);
			formPanelB.setVisible(true);
			tablePanel.refresh();

			Component[] com = formPanel.getComponents();
			for (int a = 0; a < com.length; a++) {
				com[a].setEnabled(true);
			}

			Component[] comB = formPanelB.getComponents();
			for (int a = 0; a < comB.length; a++) {
				comB[a].setEnabled(false);
			}

		});

		// ustawienia przycisku usuñ
		tablePanel.setDelBtnListner(() -> {
			controller.removePatient(tablePanel.getSelectedIndex());
			tablePanel.refresh();

		});

		// przycisk zapisz dane pacjeta
		formPanel.setFormListener(p -> {
			if (helpChoose == true) {
				// zapis danych nowego pacjenta
				if (formPanel.getPatientFormPanel() != null) {
					controller.addPatient(formPanel.getPatientFormPanel());
				}
			} else {
				// aktualizacja danych pacjenta
				controller.getOnePatientCon(tablePanel.getSelectedIndex()).setPatient(p);
			}

			tablePanel.refresh();
			formPanel.clearPanel();

			if (helpChoose == false) {
				Component[] comB = formPanelB.getComponents();
				for (int a = 0; a < comB.length; a++) {
					comB[a].setEnabled(false);
				}
			}

			Component[] com = formPanel.getComponents();
			for (int a = 0; a < com.length; a++) {
				com[a].setEnabled(false);
			}

		});

		// przycisk zapisz dane badania
		formPanelB.setFormListenerB(e -> {
			controller.getOnePatientCon(tablePanel.getSelectedIndex()).setExamination(e);
			tablePanel.refresh();
			formPanelB.clearPanelB();

			Component[] com = formPanel.getComponents();
			for (int a = 0; a < com.length; a++) {
				com[a].setEnabled(false);
			}

			Component[] comB = formPanelB.getComponents();
			for (int a = 0; a < comB.length; a++) {
				comB[a].setEnabled(false);
			}

		});

		// wybor pacjeta
		tablePanel.setTableSelectionListener(p -> {
			if (p.getExamination() != null) {
			formPanelB.setExamination(p.getExamination());
			}
			formPanel.setPatient(p);
			helpChoose = false;

			Component[] com = formPanel.getComponents();
			for (int a = 0; a < com.length; a++) {
				com[a].setEnabled(true);
			}

			Component[] comB = formPanelB.getComponents();
			for (int a = 0; a < comB.length; a++) {
				comB[a].setEnabled(true);
			}

		});

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerLocation(250);
		splitPane.setEnabled(false);
		splitPane.setLeftComponent(formPanel);
		splitPane.setRightComponent(formPanelB);

		add(splitPane, BorderLayout.WEST);
		add(tablePanel, BorderLayout.CENTER);

		setMinimumSize(new Dimension(900, 600));
		setSize(900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		formPanel.setVisible(false);
		formPanelB.setVisible(false);
	}
	
	
	//pasek Menu
	public JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu appMenu = new JMenu("Aplikacja");
		JMenuItem closeItem = new JMenuItem("Zamknij");

		appMenu.add(closeItem);
		menuBar.add(appMenu);

		closeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JMenuItem z = (JMenuItem) e.getSource();
				if (z == closeItem)
					dispose();

			}
		});

		return menuBar;
	}

	public TablePanel getTablePanel() {
		return tablePanel;
	}

	public void setTablePanel(TablePanel tablePanel) {
		this.tablePanel = tablePanel;
	}

}
