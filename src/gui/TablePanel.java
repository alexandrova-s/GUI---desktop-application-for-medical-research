package gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

import model.Patient;

//Funkcja odpowiadaj¹ca tabelê z listy Pacjentów
public class TablePanel extends JPanel {

	private JTable table;
	private PatientTableModel tableModel;
	private AddPanel addPanel;
	private TableSelectionListener tableSelectionListener;

	public TablePanel() {

		tableModel = new PatientTableModel();
		table = new JTable(tableModel);
		addPanel = new AddPanel();

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.getSelectionModel().addListSelectionListener(e -> {
			DefaultListSelectionModel dlsm = (DefaultListSelectionModel) e.getSource();
			tableSelectionListener.formListSelectionOccurred(tableModel.getPatientByRow(dlsm.getMinSelectionIndex()));
		});

		setLayout(new BorderLayout());

		add(new JScrollPane(table), BorderLayout.CENTER);
		add(addPanel, BorderLayout.SOUTH);

		//ustawienia obramowania
		Border insideBorder = BorderFactory.createTitledBorder("Lista Pacjentów");
		Border outsideBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));

	}

	public int getSelectedIndex() {
		return table.getSelectedRow();
	}

	public void setData(List<Patient> db) {
		tableModel.setData(db);
	}

	public void refresh() {
		tableModel.fireTableDataChanged();
	}

	// funkcje dla s³uchaczy
	public void setAddBtnListner(PanelAddButtonListener panelButtonListener) {
		this.addPanel.setAddBtnListener(panelButtonListener);
	}

	public void setDelBtnListner(PanelDelButtonListener panelButtonListener) {
		this.addPanel.setDelBtnListener(panelButtonListener);
	}

	public void setTableSelectionListener(TableSelectionListener tableSelectionListener) {
		this.tableSelectionListener = tableSelectionListener;
	}

}
