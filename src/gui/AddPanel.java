package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Patient;

// Panel z przyciskami obs³uguj¹cymi tabelê: Dodaj i Usuñ 

public class AddPanel extends JPanel {

	private JButton addBtn;
	private JButton delBtn;
	PanelAddButtonListener panelAddBtnListener;
	PanelDelButtonListener panelDelBtnListener;

	public AddPanel() {
		addBtn = new JButton("Dodaj");
		delBtn = new JButton("Usuñ");

		addBtn.addActionListener(e -> {
			panelAddBtnListener.panelAddButtonEventOccurred();
		});

		delBtn.addActionListener(e -> {
			panelDelBtnListener.panelDelButtonEventOccurred();
		});

		setLayout(new FlowLayout(FlowLayout.LEFT));

		add(addBtn);
		add(delBtn);

	}

	public void setAddBtnListener(PanelAddButtonListener panelButtonListener) {
		this.panelAddBtnListener = panelButtonListener;
	}

	public void setDelBtnListener(PanelDelButtonListener panelButtonListener) {
		this.panelDelBtnListener = panelButtonListener;
	}

}
