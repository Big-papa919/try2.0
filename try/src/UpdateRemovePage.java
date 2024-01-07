import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.awt.event.ActionEvent;

public class UpdateRemovePage extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private JButton backButton;

    public UpdateRemovePage() {
        // Column Names
        String[] columnNames = {"Full Name", "Age", "Address", "Phone Number", "Salary", "Update", "Delete"};

        // Example Data
        Object[][] data = {
                {"John Smith", 25, "Timisoara", 242423224, 2000 + "$", "Update", "Delete"},
                // More rows would go here
        };

        model = new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                // Only the "Update" and "Delete" columns are editable for buttons
                return column >= 5;
            }
        };

        table = new JTable(model);
        TableColumnModel columnModel = table.getColumnModel();

        columnModel.getColumn(5).setCellRenderer(new ButtonRenderer());
        columnModel.getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox(), "Update"));

        columnModel.getColumn(6).setCellRenderer(new ButtonRenderer());
        columnModel.getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox(), "Delete"));

        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            dispose(); // Close the current window
            new LaunchPage(); // Open the LaunchPage window
        });

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    private class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox, String label) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
            this.label = label;
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                JOptionPane.showMessageDialog(button, label + ": Ouch!");
                // Perform action here based on `label`
            }
            isPushed = false;
            return label;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UpdateRemovePage());
    }
}
