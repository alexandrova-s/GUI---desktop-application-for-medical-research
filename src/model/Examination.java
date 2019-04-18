package model;

import javax.swing.JOptionPane;

public class Examination {

	//atrybuty badania
	private int leukocytes;
	private int eosinophils;
	private boolean antibodies;
	private String date;

	// konstruktory
	public Examination() {
	}

	public Examination(int l, int e, boolean a, String d) // konstruktor
	{
		this.SetExamination(l, e, a, d);
	}

	// ustawienie warto�ci dla obiektu
	public void SetExamination(int l, int e, boolean a, String d) {
		if (Examination.checkExamination(l, e)) {
			this.leukocytes = l;
			this.eosinophils = e;
			this.antibodies = a;
			this.date = d;
			showAnomaly();

		} else {
			JOptionPane.showMessageDialog(null, "�le wpisane badanie", "Wa�ne!", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// getery i setery
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getLeukocytes() {
		return leukocytes;
	}

	public void setLeukocytes(int leukocytes) {
		this.leukocytes = leukocytes;
	}

	public int getEosinophils() {
		return eosinophils;
	}

	public void setEosinophils(int eosinophils) {
		this.eosinophils = eosinophils;
	}

	public boolean getAntibodies() {
		return antibodies;
	}

	public void setAntibodies(boolean antibodies) {
		this.antibodies = antibodies;
	}

	// konstruktor kopjuj�cy
	public Examination(Examination ex) // konstruktor kopjuj�cy
	{
		this(ex.leukocytes, ex.eosinophils, ex.antibodies, ex.date);
	}

	// sprawdzanie poprawnosc wratosci
	public static boolean checkExamination(int l, int e) {
		if (l <= 0 || e <= 0)
			return (false);
		return (true);
	}

	public boolean checkExamin() {
		if (this.leukocytes > 0 || this.eosinophils > 0)
			return (true);
		return (false);
	}

	// wiadomos� o wykroczeniu poza norm� w badaniu
	public void showAnomaly() {
		if (this.leukocytes < 4000) {
			JOptionPane.showMessageDialog(null, "Liczba leukocyt�w jest poni�ej normy (4 000/\u00B5l) !", "Wa�ne!",
					JOptionPane.INFORMATION_MESSAGE);
		}
		if (this.leukocytes > 11000) {
			JOptionPane.showMessageDialog(null, "Liczba leukocyt�w jest powy�ej normy (11 000/\u00B5l)", "Wa�ne!",
					JOptionPane.INFORMATION_MESSAGE);
		}
		if (this.eosinophils < 50) {
			JOptionPane.showMessageDialog(null, "Liczba eozynofili jest poni�ej normy (50/\u00B5l)", "Wa�ne!",
					JOptionPane.INFORMATION_MESSAGE);
		}
		if (this.eosinophils > 400) {
			JOptionPane.showMessageDialog(null, "Liczba eozynofili jest powyzej normy (400/\u00B5 l)", "Wa�ne!",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}