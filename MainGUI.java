import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainGUI extends JFrame {

    Library lib = new Library();
    DefaultTableModel model;

    JTextField idField = new JTextField();
    JTextField titleField = new JTextField();
    JTextField authorField = new JTextField();

    JTable table;

    public MainGUI() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Library Dashboard");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // TOP PANEL
        JPanel top = new JPanel(new GridLayout(2, 3, 10, 10));
        top.setBorder(BorderFactory.createTitledBorder("Book Entry"));

        top.add(new JLabel("ID"));
        top.add(new JLabel("Title"));
        top.add(new JLabel("Author"));

        top.add(idField);
        top.add(titleField);
        top.add(authorField);

        add(top, BorderLayout.NORTH);

        // TABLE
        model = new DefaultTableModel(new String[]{"ID", "Title", "Author", "Status"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // BUTTON PANEL
        JPanel btn = new JPanel(new GridLayout(2, 3, 10, 10));

        JButton add = new JButton("Add");
        JButton del = new JButton("Delete");
        JButton issue = new JButton("Issue");
        JButton ret = new JButton("Return");
        JButton refresh = new JButton("Refresh");

        btn.add(add);
        btn.add(del);
        btn.add(issue);
        btn.add(ret);
        btn.add(refresh);

        add(btn, BorderLayout.SOUTH);

        // ACTIONS

        add.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            Book b = new Book(id, titleField.getText(), authorField.getText());

            lib.addBook(b);
            refreshTable();
        });

        del.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());

            if (lib.deleteBook(id)) {
                JOptionPane.showMessageDialog(this, "Deleted");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Not Found");
            }
        });

        issue.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            JOptionPane.showMessageDialog(this, lib.issueBook(id));
            refreshTable();
        });

        ret.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            JOptionPane.showMessageDialog(this, lib.returnBook(id));
            refreshTable();
        });

        refresh.addActionListener(e -> refreshTable());

        setVisible(true);
    }

    void refreshTable() {

        model.setRowCount(0);

        for (Book b : lib.getBooks()) {
            model.addRow(new Object[]{
                    b.id,
                    b.title,
                    b.author,
                    b.issued ? "Issued" : "Available"
            });
        }
    }
}