package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Patient;

public class PatientTableModel extends AbstractTableModel {

	private List<Patient> db;
	
	//Ustawienia tabeli
	private String[] colNames = { "Imiê", "Nazwisko", "PESEL", "Ubezpieczenie", "P³eæ", "Badanie" };

	public PatientTableModel() {

	}

	public String getColumnName(int column) {
		return colNames[column];
	}

	public void setData(List<Patient> db) {
		this.db = db;
	}

	public int getRowCount() {
		return db.size();
	}

	public int getColumnCount() {
		return colNames.length;
	}

	public Patient getPatientByRow(int row) {
		if (row == -1) {
			return new Patient();
		}
		return db.get(row);
	}
	
	//Pobieranie wartoœci do tabeli
	public Object getValueAt(int row, int col) {
		Patient patient = db.get(row);

		switch (col) {
		case 0:
			return patient.getName();
		case 1:
			return patient.getSurname();
		case 2:
			return patient.getPesel();
		case 3:
			return patient.getInsurCat();
		case 4:
			return patient.getGender();
		case 5:
			return patient.examinationExists();
		}

		return null;
	}

	public boolean isCellEditable(int row, int col) {

		switch (col) {
		case 5:
			return true;
		default:
			return false;
		}
	}

	//ustawienie klasy kolumny
	public Class getColumnClass(int col) {
		if (col == 5)
			return Boolean.class;
		return String.class;
	}
}
