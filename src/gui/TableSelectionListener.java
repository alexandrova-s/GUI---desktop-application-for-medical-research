package gui;

import java.util.EventListener;

import model.Patient;

public interface TableSelectionListener extends EventListener {
	public void formListSelectionOccurred(Patient p);
}
