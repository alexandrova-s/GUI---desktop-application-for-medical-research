package model;

import java.util.ArrayList;
import java.util.List;

public class PatientsBase {

	private ArrayList<Patient> patients;

	public PatientsBase() {
		patients = new ArrayList<>();
	}

	// funkcja dodaj pacjeta do listy
	public boolean addPatient(Patient patient) {
		return patients.add(patient);
	}

	// funkcja sprawdza czy istnieje pacjent o takim peseu
	public boolean existsByPesel(String pesel) {
		return patients.stream().anyMatch(patient -> patient.getPesel().equals(pesel));
	}

	// funkcja usuwajaca pacjenta
	public void removePatient(int selectedPatient) {
		patients.remove(selectedPatient);
	}

	// pobieranie pacjentów z listy
	public List<Patient> getPatient() {
		return patients;
	}

	// pobór wybranego pacjenta
	public Patient getOnePatient(int selectedPatient) {
		return patients.get(selectedPatient);
	}

}
