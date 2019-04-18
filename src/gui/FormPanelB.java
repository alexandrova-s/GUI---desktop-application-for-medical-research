package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import model.Examination;

//Panel odpowiadaj¹cy za wyniki badania
public class FormPanelB extends JPanel {

	private JLabel dateLabel;
	private JTextField leukocytesField;
	private JLabel leukocytesLabel;
	private JTextField eosinophilsField;
	private JLabel eosinophilsLabel;
	private JButton saveBtnFPB;
	private JButton delBtnFPB;
	private FormListenerB formListenerB;
	private JCheckBox IgECheck;
	private JLabel IgELabel;
	private JDatePickerImpl datePicker;

	// pobieranie dzisziejszej daty do czyszczenia Panelu
	LocalDateTime today = LocalDateTime.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String formatDateTime = today.format(formatter);

	public FormPanelB() {
		// ustawienie wymiarów
		Dimension dim = getPreferredSize();
		dim.width = 400;
		setPreferredSize(dim);

		dateLabel = new JLabel("Data: ");
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		leukocytesLabel = new JLabel("Liczba leukocytów [\u00B5l]: ");
		leukocytesField = new JTextField(15);

		eosinophilsLabel = new JLabel("Liczba eozynofili [\u00B5l]: ");
		eosinophilsField = new JTextField(15);

		IgECheck = new JCheckBox();
		IgELabel = new JLabel("Obecnoœæ przeciwcia³ IgE: ");

		saveBtnFPB = new JButton("Zapisz");
		delBtnFPB = new JButton("Anuluj");

		// Akcja przycisku Zapisz
		saveBtnFPB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				Examination ev = new Examination(Integer.parseInt(leukocytesField.getText()),
						Integer.parseInt(eosinophilsField.getText()), IgECheck.isSelected(),
						datePicker.getJFormattedTextField().getText());

				if (formListenerB != null) {
					formListenerB.formEventBOccurred(ev);
				}

			}
		});

		// Akcja przycisku Anuluj
		delBtnFPB.addActionListener(e -> {
			clearPanelB();
		});

		Border insideBorder = BorderFactory.createTitledBorder("Badanie");
		Border outsideBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));

		layoutComponents();
	}

	// funkcja czyszcz¹ca formPanelB
	public void clearPanelB() {
		leukocytesField.setText("");
		eosinophilsField.setText("");
		IgECheck.setSelected(false);
		datePicker.getJFormattedTextField().setText(formatDateTime);
	}

	// funkcja wstawiaj¹ca pacjenta do tabeli po wyborze pacjêta
	public void setExamination(Examination ev) {
		if (ev != null) {
			leukocytesField.setText(String.valueOf(ev.getLeukocytes()));
			eosinophilsField.setText(String.valueOf(ev.getEosinophils()));
			IgECheck.setSelected(ev.getAntibodies());
			datePicker.getJFormattedTextField().setText(ev.getDate());
		} else {
			clearPanelB();
		}
	}

	//Funkcja ustawiaj¹ca komponenty w Panelu
	public void layoutComponents() {

		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		/////////////////////// FIRST ROW ///////

		gc.gridy = 0;

		gc.weightx = 2;
		gc.weighty = 0D;

		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 5);
		add(dateLabel, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(datePicker, gc);

		/////////////////////// NEXT ROW ///////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_START;
		add(leukocytesLabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(leukocytesField, gc);

		/////////////////////// NEXT ROW ///////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_START;
		add(eosinophilsLabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(eosinophilsField, gc);

		/////////////////////// NEXT ROW ///////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_START;
		add(IgELabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(IgECheck, gc);

		/////////////////////// NEXT ROW ///////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 2.0;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(saveBtnFPB, gc);

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LAST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 0);
		add(delBtnFPB, gc);

	}

	public void setFormListenerB(FormListenerB listenerb) {
		this.formListenerB = listenerb;

	}

	//Funkcja odpowiadaj¹ca za formatowanie daty do pobrania do tabeli
	public class DateLabelFormatter extends AbstractFormatter {

		private String datePattern = "yyyy-MM-dd";
		private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		public Object stringToValue(String text) throws ParseException {
			return dateFormatter.parseObject(text);
		}

		public String valueToString(Object value) throws ParseException {
			if (value != null) {
				Calendar cal = (Calendar) value;
				return dateFormatter.format(cal.getTime());
			}

			return "";
		}

	}
}
