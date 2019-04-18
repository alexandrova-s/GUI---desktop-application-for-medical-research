package gui;

import java.util.EventListener;

import model.Patient;

public interface FormListener extends EventListener {
	void formEventOccurred(Patient p);
}
