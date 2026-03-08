import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class CourseRegistration {
    String studentName;
    String studentId;
    String courseName;
    String email;

    CourseRegistration(String studentName, String studentId, String courseName, String email) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.courseName = courseName;
        this.email = email;
    }
}

public class CourseRegistrationSystem extends JFrame {
    private JTextField tfName;
    private JTextField tfId;
    private JTextField tfCourse;
    private JTextField tfEmail;
    private JLabel statusLabel;
    private JTable table;
    private DefaultTableModel model;
    private final List<CourseRegistration> registrations = new ArrayList<>();

    public CourseRegistrationSystem() {
        setTitle("Регистрация на курсы");
        setSize(1020, 620);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(14, 14));
        root.setBorder(new EmptyBorder(14, 14, 14, 14));
        root.setBackground(new Color(240, 244, 248));
        setContentPane(root);

        root.add(buildHeader(), BorderLayout.NORTH);
        root.add(buildFormCard(), BorderLayout.WEST);
        root.add(buildTableCard(), BorderLayout.CENTER);
        root.add(buildFooter(), BorderLayout.SOUTH);

        registrations.add(new CourseRegistration("Алиса", "C101", "Java Programming", "alice@mail.com"));
        registrations.add(new CourseRegistration("Боб", "C102", "Web Design", "bob@mail.com"));
        showAllRegistrations();
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(30, 41, 59));
        header.setBorder(new EmptyBorder(16, 20, 16, 20));

        JLabel title = new JLabel("CourseFlow");
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Панель регистрации студентов на онлайн-курсы");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(191, 219, 254));

        JPanel textWrap = new JPanel();
        textWrap.setOpaque(false);
        textWrap.setLayout(new BoxLayout(textWrap, BoxLayout.Y_AXIS));
        textWrap.add(title);
        textWrap.add(Box.createVerticalStrut(4));
        textWrap.add(subtitle);

        header.add(textWrap, BorderLayout.WEST);
        return header;
    }

    private JPanel buildFormCard() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setPreferredSize(new Dimension(340, 0));
        wrapper.setBackground(Color.WHITE);
        wrapper.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219)),
                new EmptyBorder(16, 16, 16, 16)
        ));

        JLabel sectionTitle = new JLabel("Новая регистрация");
        sectionTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        sectionTitle.setForeground(new Color(17, 24, 39));

        JPanel form = new JPanel(new GridLayout(8, 1, 0, 8));
        form.setOpaque(false);

        tfName = new JTextField();
        tfId = new JTextField();
        tfCourse = new JTextField();
        tfEmail = new JTextField();

        form.add(createField("Имя студента", tfName));
        form.add(createField("ID студента", tfId));
        form.add(createField("Курс", tfCourse));
        form.add(createField("Email", tfEmail));

        JPanel buttons = new JPanel(new GridLayout(1, 2, 10, 0));
        buttons.setOpaque(false);
        JButton btnRegister = new JButton("Сохранить");
        JButton btnClear = new JButton("Сбросить");
        styleButton(btnRegister, new Color(37, 99, 235));
        styleButton(btnClear, new Color(75, 85, 99));
        buttons.add(btnRegister);
        buttons.add(btnClear);

        JPanel content = new JPanel(new BorderLayout(0, 12));
        content.setOpaque(false);
        content.add(sectionTitle, BorderLayout.NORTH);
        content.add(form, BorderLayout.CENTER);
        content.add(buttons, BorderLayout.SOUTH);

        btnRegister.addActionListener(e -> registerStudent());
        btnClear.addActionListener(e -> clearForm());

        wrapper.add(content, BorderLayout.NORTH);
        return wrapper;
    }

    private JPanel createField(String labelText, JTextField field) {
        JPanel fieldPanel = new JPanel(new BorderLayout(0, 4));
        fieldPanel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        label.setForeground(new Color(55, 65, 81));

        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225)),
                new EmptyBorder(7, 8, 7, 8)
        ));

        fieldPanel.add(label, BorderLayout.NORTH);
        fieldPanel.add(field, BorderLayout.CENTER);
        return fieldPanel;
    }

    private JPanel buildTableCard() {
        JPanel tableCard = new JPanel(new BorderLayout(0, 10));
        tableCard.setBackground(Color.WHITE);
        tableCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219)),
                new EmptyBorder(16, 16, 16, 16)
        ));

        JLabel tableTitle = new JLabel("Список регистраций");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tableTitle.setForeground(new Color(17, 24, 39));

        model = new DefaultTableModel(new String[]{"Студент", "ID", "Курс", "Email"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(28);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(226, 232, 240));

        JScrollPane pane = new JScrollPane(table);
        pane.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240)));

        tableCard.add(tableTitle, BorderLayout.NORTH);
        tableCard.add(pane, BorderLayout.CENTER);
        return tableCard;
    }

    private JPanel buildFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(new Color(248, 250, 252));
        footer.setBorder(BorderFactory.createEmptyBorder(6, 10, 0, 10));

        statusLabel = new JLabel("Записей: 0");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        statusLabel.setForeground(new Color(71, 85, 105));

        footer.add(statusLabel, BorderLayout.WEST);
        return footer;
    }

    private void registerStudent() {
        String name = tfName.getText().trim();
        String id = tfId.getText().trim();
        String course = tfCourse.getText().trim();
        String email = tfEmail.getText().trim();

        if (name.isEmpty() || id.isEmpty() || course.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Заполните все поля формы.", "Проверка", JOptionPane.WARNING_MESSAGE);
            return;
        }

        registrations.add(new CourseRegistration(name, id, course, email));
        showAllRegistrations();
        clearForm();
        JOptionPane.showMessageDialog(this, "Регистрация успешно сохранена.", "Успех", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showAllRegistrations() {
        model.setRowCount(0);
        for (CourseRegistration registration : registrations) {
            model.addRow(new Object[]{
                    registration.studentName,
                    registration.studentId,
                    registration.courseName,
                    registration.email
            });
        }
        statusLabel.setText("Записей: " + registrations.size());
    }

    private void clearForm() {
        tfName.setText("");
        tfId.setText("");
        tfCourse.setText("");
        tfEmail.setText("");
        tfName.requestFocus();
    }

    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CourseRegistrationSystem().setVisible(true));
    }
}
