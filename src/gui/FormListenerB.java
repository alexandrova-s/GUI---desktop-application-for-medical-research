package gui;

import java.util.EventListener;

import model.Examination;

public interface FormListenerB extends EventListener {
	public void formEventBOccurred(Examination ev);
}
