import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTable;


public class IHM {

	private JFrame frame;
	private JTable table;
	private JTable table_1;

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 978, 653);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JPanel lr_panel = new JPanel();
		GridBagConstraints gbc_lr_panel = new GridBagConstraints();
		gbc_lr_panel.insets = new Insets(0, 0, 5, 0);
		gbc_lr_panel.fill = GridBagConstraints.BOTH;
		gbc_lr_panel.gridx = 0;
		gbc_lr_panel.gridy = 0;
		frame.getContentPane().add(lr_panel, gbc_lr_panel);
		GridBagLayout gbl_lr_panel = new GridBagLayout();
		gbl_lr_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_lr_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_lr_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_lr_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		lr_panel.setLayout(gbl_lr_panel);
		
		JLabel lblDisplayColumns = new JLabel("Display columns :");
		GridBagConstraints gbc_lblDisplayColumns = new GridBagConstraints();
		gbc_lblDisplayColumns.insets = new Insets(0, 0, 5, 5);
		gbc_lblDisplayColumns.gridheight = 2;
		gbc_lblDisplayColumns.gridx = 0;
		gbc_lblDisplayColumns.gridy = 0;
		lr_panel.add(lblDisplayColumns, gbc_lblDisplayColumns);
		
		JCheckBox chckbxMovie = new JCheckBox("Movie");
		GridBagConstraints gbc_chckbxMovie = new GridBagConstraints();
		gbc_chckbxMovie.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxMovie.gridx = 1;
		gbc_chckbxMovie.gridy = 2;
		lr_panel.add(chckbxMovie, gbc_chckbxMovie);
		
		JCheckBox chckbxReleaseDate = new JCheckBox("Release Date");
		GridBagConstraints gbc_chckbxReleaseDate = new GridBagConstraints();
		gbc_chckbxReleaseDate.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxReleaseDate.gridx = 2;
		gbc_chckbxReleaseDate.gridy = 2;
		lr_panel.add(chckbxReleaseDate, gbc_chckbxReleaseDate);
		
		JCheckBox chckbxGenre = new JCheckBox("Genre");
		GridBagConstraints gbc_chckbxGenre = new GridBagConstraints();
		gbc_chckbxGenre.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxGenre.gridx = 3;
		gbc_chckbxGenre.gridy = 2;
		lr_panel.add(chckbxGenre, gbc_chckbxGenre);
		
		JCheckBox chckbxStudio = new JCheckBox("Studio");
		GridBagConstraints gbc_chckbxStudio = new GridBagConstraints();
		gbc_chckbxStudio.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxStudio.gridx = 4;
		gbc_chckbxStudio.gridy = 2;
		lr_panel.add(chckbxStudio, gbc_chckbxStudio);
		
		JCheckBox chckbxProductionBudget = new JCheckBox("Production Budget");
		GridBagConstraints gbc_chckbxProductionBudget = new GridBagConstraints();
		gbc_chckbxProductionBudget.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxProductionBudget.gridx = 5;
		gbc_chckbxProductionBudget.gridy = 2;
		lr_panel.add(chckbxProductionBudget, gbc_chckbxProductionBudget);
		
		JCheckBox chckbxDomesticGross = new JCheckBox("Domestic Gross");
		GridBagConstraints gbc_chckbxDomesticGross = new GridBagConstraints();
		gbc_chckbxDomesticGross.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxDomesticGross.gridx = 6;
		gbc_chckbxDomesticGross.gridy = 2;
		lr_panel.add(chckbxDomesticGross, gbc_chckbxDomesticGross);
		
		JCheckBox chckbxWordwideGross = new JCheckBox("Wordwide Gross");
		GridBagConstraints gbc_chckbxWordwideGross = new GridBagConstraints();
		gbc_chckbxWordwideGross.gridx = 7;
		gbc_chckbxWordwideGross.gridy = 2;
		lr_panel.add(chckbxWordwideGross, gbc_chckbxWordwideGross);
		
		JPanel lc_panel = new JPanel();
		GridBagConstraints gbc_lc_panel = new GridBagConstraints();
		gbc_lc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_lc_panel.fill = GridBagConstraints.BOTH;
		gbc_lc_panel.gridx = 0;
		gbc_lc_panel.gridy = 1;
		frame.getContentPane().add(lc_panel, gbc_lc_panel);
		GridBagLayout gbl_lc_panel = new GridBagLayout();
		gbl_lc_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_lc_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_lc_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_lc_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		lc_panel.setLayout(gbl_lc_panel);
		
		JLabel lblConditions = new JLabel("Conditions :");
		GridBagConstraints gbc_lblConditions = new GridBagConstraints();
		gbc_lblConditions.gridheight = 2;
		gbc_lblConditions.insets = new Insets(0, 0, 5, 5);
		gbc_lblConditions.gridx = 0;
		gbc_lblConditions.gridy = 0;
		lc_panel.add(lblConditions, gbc_lblConditions);
		
		table_1 = new JTable();
		GridBagConstraints gbc_table_1 = new GridBagConstraints();
		gbc_table_1.gridheight = 5;
		gbc_table_1.gridwidth = 19;
		gbc_table_1.insets = new Insets(0, 0, 5, 5);
		gbc_table_1.fill = GridBagConstraints.BOTH;
		gbc_table_1.gridx = 3;
		gbc_table_1.gridy = 0;
		lc_panel.add(table_1, gbc_table_1);
		
		JButton btnNewCondition = new JButton("New condition");
		GridBagConstraints gbc_btnNewCondition = new GridBagConstraints();
		gbc_btnNewCondition.gridwidth = 5;
		gbc_btnNewCondition.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewCondition.gridx = 22;
		gbc_btnNewCondition.gridy = 0;
		lc_panel.add(btnNewCondition, gbc_btnNewCondition);
		
		JButton btnDisplayResult = new JButton("Display result");
		GridBagConstraints gbc_btnDisplayResult = new GridBagConstraints();
		gbc_btnDisplayResult.gridwidth = 5;
		gbc_btnDisplayResult.gridx = 22;
		gbc_btnDisplayResult.gridy = 4;
		lc_panel.add(btnDisplayResult, gbc_btnDisplayResult);
		
		JPanel display_panel = new JPanel();
		GridBagConstraints gbc_display_panel = new GridBagConstraints();
		gbc_display_panel.fill = GridBagConstraints.BOTH;
		gbc_display_panel.gridx = 0;
		gbc_display_panel.gridy = 2;
		frame.getContentPane().add(display_panel, gbc_display_panel);
		
		table = new JTable();
		display_panel.add(table);
	}

}
