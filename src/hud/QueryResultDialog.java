package hud;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class QueryResultDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTable vhsTable;

    public QueryResultDialog(String[][] data, String[] columnNames) {
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        vhsTable = new JTable(data, columnNames);
        vhsTable.setAutoCreateRowSorter(true);
        setTitle(data.length+" Result");
        add(new JScrollPane(vhsTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
                BorderLayout.CENTER); 
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.MODELESS);
        pack();
        setVisible(true);
    }
}