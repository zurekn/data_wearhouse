import hud.ButtonColumn;
import hud.MyComboBoxEditor;
import hud.MyComboBoxRenderer;
import hud.QueryResultDialog;
import include.Data;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.UIManager;
import javax.swing.JScrollPane;

import core.Mediator;
import core.Triple;

public class IHM {

	private JFrame frame;
	private JTable jtableCondition;
	private Mediator mediator;
	private JCheckBox chckbxMovie;
	private JCheckBox chckbxReleaseDate;
	private JCheckBox chckbxGenre;
	private JCheckBox chckbxStudio;
	private JCheckBox chckbxProductionBudget;
	private JCheckBox chckbxDomesticGross;
	private JCheckBox chckbxWordwideGross;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IHM window = new IHM();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IHM() {
		initialize();
		mediator = new Mediator();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 978, 653);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JPanel lr_panel = new JPanel();
		GridBagConstraints gbc_lr_panel = new GridBagConstraints();
		gbc_lr_panel.insets = new Insets(0, 0, 5, 0);
		gbc_lr_panel.fill = GridBagConstraints.BOTH;
		gbc_lr_panel.gridx = 0;
		gbc_lr_panel.gridy = 0;
		frame.getContentPane().add(lr_panel, gbc_lr_panel);
		GridBagLayout gbl_lr_panel = new GridBagLayout();
		gbl_lr_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_lr_panel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_lr_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_lr_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		lr_panel.setLayout(gbl_lr_panel);

		JLabel lblDisplayColumns = new JLabel("Display columns :");
		GridBagConstraints gbc_lblDisplayColumns = new GridBagConstraints();
		gbc_lblDisplayColumns.insets = new Insets(0, 0, 5, 5);
		gbc_lblDisplayColumns.gridheight = 2;
		gbc_lblDisplayColumns.gridx = 0;
		gbc_lblDisplayColumns.gridy = 0;
		lr_panel.add(lblDisplayColumns, gbc_lblDisplayColumns);

		chckbxMovie = new JCheckBox("Movie");
		chckbxMovie.setSelected(true);
		GridBagConstraints gbc_chckbxMovie = new GridBagConstraints();
		gbc_chckbxMovie.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxMovie.gridx = 1;
		gbc_chckbxMovie.gridy = 2;
		lr_panel.add(chckbxMovie, gbc_chckbxMovie);

		chckbxReleaseDate = new JCheckBox("Release Date");
		GridBagConstraints gbc_chckbxReleaseDate = new GridBagConstraints();
		gbc_chckbxReleaseDate.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxReleaseDate.gridx = 2;
		gbc_chckbxReleaseDate.gridy = 2;
		lr_panel.add(chckbxReleaseDate, gbc_chckbxReleaseDate);

		chckbxGenre = new JCheckBox("Genre");
		GridBagConstraints gbc_chckbxGenre = new GridBagConstraints();
		gbc_chckbxGenre.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxGenre.gridx = 3;
		gbc_chckbxGenre.gridy = 2;
		lr_panel.add(chckbxGenre, gbc_chckbxGenre);

		chckbxStudio = new JCheckBox("Studio");
		GridBagConstraints gbc_chckbxStudio = new GridBagConstraints();
		gbc_chckbxStudio.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxStudio.gridx = 4;
		gbc_chckbxStudio.gridy = 2;
		lr_panel.add(chckbxStudio, gbc_chckbxStudio);

		chckbxProductionBudget = new JCheckBox("Production Budget");
		GridBagConstraints gbc_chckbxProductionBudget = new GridBagConstraints();
		gbc_chckbxProductionBudget.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxProductionBudget.gridx = 5;
		gbc_chckbxProductionBudget.gridy = 2;
		lr_panel.add(chckbxProductionBudget, gbc_chckbxProductionBudget);

		chckbxDomesticGross = new JCheckBox("Domestic Gross");
		GridBagConstraints gbc_chckbxDomesticGross = new GridBagConstraints();
		gbc_chckbxDomesticGross.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxDomesticGross.gridx = 6;
		gbc_chckbxDomesticGross.gridy = 2;
		lr_panel.add(chckbxDomesticGross, gbc_chckbxDomesticGross);

		chckbxWordwideGross = new JCheckBox("Wordwide Gross");
		GridBagConstraints gbc_chckbxWordwideGross = new GridBagConstraints();
		gbc_chckbxWordwideGross.gridx = 7;
		gbc_chckbxWordwideGross.gridy = 2;
		lr_panel.add(chckbxWordwideGross, gbc_chckbxWordwideGross);

		JPanel lc_panel = new JPanel();
		GridBagConstraints gbc_lc_panel = new GridBagConstraints();
		gbc_lc_panel.fill = GridBagConstraints.BOTH;
		gbc_lc_panel.gridx = 0;
		gbc_lc_panel.gridy = 1;
		frame.getContentPane().add(lc_panel, gbc_lc_panel);
		GridBagLayout gbl_lc_panel = new GridBagLayout();
		gbl_lc_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_lc_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_lc_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		gbl_lc_panel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		lc_panel.setLayout(gbl_lc_panel);

		JLabel lblConditions = new JLabel("Conditions :");
		GridBagConstraints gbc_lblConditions = new GridBagConstraints();
		gbc_lblConditions.insets = new Insets(0, 0, 5, 5);
		gbc_lblConditions.gridx = 0;
		gbc_lblConditions.gridy = 0;
		lc_panel.add(lblConditions, gbc_lblConditions);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.gridwidth = 20;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 0;
		lc_panel.add(scrollPane, gbc_scrollPane);

		jtableCondition = new JTable();
		scrollPane.setViewportView(jtableCondition);
		jtableCondition.setBorder(UIManager.getBorder("Tree.editorBorder"));
		jtableCondition.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Name", "Operator", "Condition", "" }));

		JButton btnNewCondition = new JButton("New condition");
		btnNewCondition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) jtableCondition.getModel();
				model.addRow(new Object[] { "", "", "", "delete row" });
			}
		});

		Action deleteAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				((DefaultTableModel) table.getModel()).removeRow(modelRow);
			}
		};

		TableColumn col0 = jtableCondition.getColumnModel().getColumn(0);
		col0.setCellEditor(new MyComboBoxEditor(Data.COMBO_BOX_FIELD_VALUES));
		col0.setCellRenderer(new MyComboBoxRenderer(Data.COMBO_BOX_FIELD_VALUES));
		
		TableColumn col1 = jtableCondition.getColumnModel().getColumn(1);
		col1.setCellEditor(new MyComboBoxEditor(Data.COMBO_BOX_OPERATOR_VALUES));
		col1.setCellRenderer(new MyComboBoxRenderer(Data.COMBO_BOX_OPERATOR_VALUES));

		
		
		ButtonColumn buttonColumn = new ButtonColumn(jtableCondition, deleteAction, 3);
		buttonColumn.setMnemonic(KeyEvent.VK_D);

		GridBagConstraints gbc_btnNewCondition = new GridBagConstraints();
		gbc_btnNewCondition.gridwidth = 5;
		gbc_btnNewCondition.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewCondition.gridx = 22;
		gbc_btnNewCondition.gridy = 0;
		lc_panel.add(btnNewCondition, gbc_btnNewCondition);

		JButton btnDisplayResult = new JButton("Display result");
		btnDisplayResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createTripleForMediator();
			}
		});
		GridBagConstraints gbc_btnDisplayResult = new GridBagConstraints();
		gbc_btnDisplayResult.gridwidth = 5;
		gbc_btnDisplayResult.gridx = 22;
		gbc_btnDisplayResult.gridy = 4;
		lc_panel.add(btnDisplayResult, gbc_btnDisplayResult);
	}

	protected void createTripleForMediator() {
		ArrayList<Triple> triples = new ArrayList<>();
		ArrayList<String> display = new ArrayList<>();
		
		if(chckbxMovie.isSelected()){
			display.add("Movie");
		}
		if(chckbxGenre.isSelected()){
			display.add("Genre");
		}
		if(chckbxDomesticGross.isSelected()){
			display.add("Domestic_gross");
		}
		if(chckbxProductionBudget.isSelected()){
			display.add("Production_budget");
		}
		if(chckbxReleaseDate.isSelected()){
			display.add("Release_date");
		}
		if(chckbxStudio.isSelected()){
			display.add("Studio");
		}
		if(chckbxWordwideGross.isSelected()){
			display.add("Worldwide_gross");
		}
		for(int row = 0; row < jtableCondition.getRowCount(); row++){
			Triple t = new Triple();
			String[] tvalue = new String[3];
			for(int col = 0; col < jtableCondition.getColumnCount()-1; col++){
				Object value = jtableCondition.getValueAt(row, col);
				tvalue[col] = value.toString();
			}
			t.setAll(tvalue);
			System.out.println("Add condition "+t.toString());
			triples.add(t);
		}
		if(!display.isEmpty()){
			String[][] datas = mediator.decodeTriple(triples, display);
			String[] colNames = new String[display.size()];
			for(int i = 0; i < display.size(); i++)
				colNames[i] = display.get(i);
			new QueryResultDialog(datas, colNames);
		}
	}

}
