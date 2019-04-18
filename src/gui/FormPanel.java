package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import model.Examination;
import model.Gender;
import model.InsuranceCategory;
import model.Patient;

// Panel odpowiadaj¹cy za dane pacjenta 

public class FormPanel extends JPanel {

	private JLabel nameLabel;
	private JLabel surnameLabel;
	private JLabel peselLabel;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField peselField;
	private JButton saveBtn;
	private JButton delBtnFP;
	private FormListener formListener;
	private JComboBox insuranceBox;

	private JRadioButton femaleRadio;
	private JRadioButton maleRadio;
	private ButtonGroup genderGroup;

	private PeselValidator peselValidator;

	public FormPanel() {
		//ustawianie rozmiaru
		
		Dimension dim = getPreferredSize();
		dim.width = 400;
		setPreferredSize(dim);

		nameLabel = new JLabel("Imiê: ");
		surnameLabel = new JLabel("Nazwisko: ");
		peselLabel = new JLabel("PESEL: ");
		nameField = new JTextField(15);
		surnameField = new JTextField(15);
		peselField = new JTextField(15);
		insuranceBox = new JComboBox();

		femaleRadio = new JRadioButton("Kobieta");
		maleRadio = new JRadioButton("Mê¿czyzna");
		genderGroup = new ButtonGroup();

		genderGroup.add(femaleRadio);
		genderGroup.add(maleRadio);
		saveBtn = new JButton("Zapisz");

		nameLabel.setLabelFor(nameField);
		
		//ComboBox do wyboru ubezpieczenia
		DefaultComboBoxModel insurModel = new DefaultComboBoxModel();
		insurModel.addElement("NFZ");
		insurModel.addElement("Prywatne");
		insurModel.addElement("Brak");
		insuranceBox.setModel(insurModel);
		insuranceBox.setSelectedIndex(0);
		// insuranceBox.setEditable(true);

		delBtnFP = new JButton("Anuluj");
		
		//Akcja przycisku Zapisz
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				String name = nameField.getText();
				String surname = surnameField.getText();
				String pesel = peselField.getText();
				String insurCat = (String) insuranceBox.getSelectedItem();
				String gender;
				if (femaleRadio.isSelected()) {
					gender = "kobieta";
				} else if (maleRadio.isSelected()) {
					gender = "mê¿czyzna";
				} else {
					gender = "nie_podano";
				}
				Patient patient = new Patient(name, surname, pesel, InsuranceCategory.valueOf(insurCat),
						Gender.valueOf(gender));
				peselValidator = new PeselValidator(patient.getPesel());
				if (peselValidator.isValid()) {
					if (formListener != null) {
						formListener.formEventOccurred(patient);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Wpisz poprawny pesel", "Wa¿ne!",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		
		
		//Akcja przycisku Anuluj
		delBtnFP.addActionListener(e -> {
			clearPanel();
		});
		
		//Ustawienie obramowania Panelu
		Border insideBorder = BorderFactory.createTitledBorder("Dane Pajenta");
		Border outsideBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));

		layoutComponents();
	}

	// funkcja czyszcz¹ca panel
	public void clearPanel() {
		nameField.setText("");
		surnameField.setText("");
		peselField.setText("");
		genderGroup.clearSelection();
	}

	// funkcja wype³niaj¹ca dene w tabeli
	public void setPatient(Patient p) {
		if (p != null) {
			nameField.setText(p.getName());
			surnameField.setText(p.getSurname());
			peselField.setText(p.getPesel());

			if (String.valueOf(p.getGender()) == "kobieta") {
				femaleRadio.setSelected(true);
			} else if (String.valueOf(p.getGender()) == "mê¿czyzna") {
				maleRadio.setSelected(true);
			} else {
				maleRadio.setSelected(true);
			}

			if (String.valueOf(p.getInsurCat()) == "Brak") {
				insuranceBox.setSelectedItem("Brak");
			} else if (String.valueOf(p.getInsurCat()) == "Prywatne") {
				insuranceBox.setSelectedItem("Prywatne");
			} else if (String.valueOf(p.getInsurCat()) == "NFZ") {
				insuranceBox.setSelectedItem("NFZ");
			}

		} else {
			clearPanel();
		}
	}

	// funkcja pobierajaca dane pacjeta z panelu potrzebana do
	public Patient getPatientFormPanel() {
		String name = nameField.getText();
		String surname = surnameField.getText();
		String pesel = peselField.getText();
		String insurCat = (String) insuranceBox.getSelectedItem();
		String gender;
		if (femaleRadio.isSelected()) {
			gender = "kobieta";
		} else if (maleRadio.isSelected()) {
			gender = "mê¿czyzna";
		} else {
			gender = "nie_podano";
		}

		Patient patient = new Patient(name, surname, pesel, InsuranceCategory.valueOf(insurCat),
				Gender.valueOf(gender));
		peselValidator = new PeselValidator(patient.getPesel());
		if (peselValidator.isValid()) {
			return patient;
		} else {
			JOptionPane.showMessageDialog(null, "Podaj poprawny pesel", "Wa¿ne!", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
	}
	
	
	//funkcja ustawiaj¹ca elementy w Panelu
	public void layoutComponents() {

		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		/////////////////////// FIRST ROW ///////

		gc.gridy = 0;

		gc.weightx = 2;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 5);
		add(nameLabel, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(nameField, gc);

		/////////////////////// NEXT ROW ///////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_START;
		add(surnameLabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(surnameField, gc);

		/////////////////////// NEXT ROW ///////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_START;
		add(peselLabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(peselField, gc);

		/////////////////////// NEXT ROW ///////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_START;
		add(new JLabel("P³eæ: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(femaleRadio, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 0);
		add(maleRadio, gc);

		/////////////////////// NEXT ROW ///////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_START;
		add(new JLabel("Ubezpieczenie: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(insuranceBox, gc);

		/////////////////////// NEXT ROW ///////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 2.0;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(saveBtn, gc);

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LAST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 0);
		add(delBtnFP, gc);

	}

	public void setFormListener(FormListener listener) {
		this.formListener = listener;

	}
}
