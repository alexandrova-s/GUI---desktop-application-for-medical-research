package controller;

import java.util.List;

import javax.swing.JOptionPane;

import model.Patient;
import model.PatientsBase;

public class Controller {

	PatientsBase db = new PatientsBase();

	// funkcja pobieraj¹ca listê pacjêtów
	public List<Patient> getPatients() {
		return db.getPatient();
	}

	// funkcja dodaj¹ca pacjêta do listy
	public void addPatient(Patient patient) {
		if (db.existsByPesel(patient.getPesel())) {
			JOptionPane.showMessageDialog(null, "Pacjent o takim peselu ju¿ istnieje", "Wa¿ne!",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			db.addPatient(patient);
		}

	}

	// funkcja usuwaj¹ca pacjêta z listy
	public void removePatient(int selectedPatient) {
		db.removePatient(selectedPatient);
	}

	// funkcja potrzebna do wyborku pacjent
	public Patient getOnePatientCon(int selectedPatient) {

		return db.getOnePatient(selectedPatient);
	}

}
