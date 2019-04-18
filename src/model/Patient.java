package model;

import javax.swing.JOptionPane;

public class Patient {

	//atrybuty pacjenta
	private String name;
	private String surname;
	private String pesel;
	private InsuranceCategory insurCat;
	private Gender gender;
	private Examination examination;

	// konstruktory
	public Patient() {
	}

	public Patient(String name, String surname, String pesel, InsuranceCategory insurance, Gender gender) {

		this.name = name;
		this.surname = surname;
		this.pesel = pesel;
		this.insurCat = insurance;
		this.gender = gender;

	}

	public void setPatient(Patient p) {
		this.name = p.getName();
		this.surname = p.getSurname();
		this.pesel = p.getPesel();
		this.insurCat = p.getInsurCat();
		this.gender = p.getGender();
	}

	//geery i setery
	public Examination getExamination() {
		return examination;
	}

	public void setExamination(Examination examination) {
		this.examination = examination;
	}

	public boolean examinationExists() {
		return this.examination != null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public InsuranceCategory getInsurCat() {
		return insurCat;
	}

	public void setInsurCat(InsuranceCategory insurCat) {
		this.insurCat = insurCat;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	// konstruktor kopjuj¹cy
	public Patient(Patient pat) {
		this(pat.name, pat.surname, pat.pesel, pat.insurCat, pat.gender);
	}

}