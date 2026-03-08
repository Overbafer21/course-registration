import javax.swing.*;
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
    private JTable table;
    private DefaultTableModel model;
    private final List<CourseRegistration> registrations = new ArrayList<>();

    public CourseRegistrationSystem() {
        setTitle("Онлайн-система регистрации на курсы");
        setSize(950, 560);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(12, 12));

        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(root);

        JLabel title = new JLabel("Онлайн-регистрация на курсы", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setOpaque(true);
        title.setBackground(new Color(44, 62, 80));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(14, 0, 14, 0));
        root.add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setPreferredSize(new Dimension(320, 200));
        formPanel.setBorder(BorderFactory.createTitledBorder("Регистрация студента"));
        formPanel.setBackground(new Color(245, 245, 245));

        tfName = new JTextField();
        tfId = new JTextField();
        tfCourse = new JTextField();
        tfEmail = new JTextField();

        formPanel.add(new JLabel("Имя студента:"));
        formPanel.add(tfName);
        formPanel.add(new JLabel("ID студента:"));
        formPanel.add(tfId);
        formPanel.add(new JLabel("Название курса:"));
        formPanel.add(tfCourse);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(tfEmail);

        JButton btnRegister = new JButton("Зарегистрировать");
        JButton btnClear = new JButton("Очистить");
        styleButton(btnRegister, new Color(46, 134, 193));
        styleButton(btnClear, new Color(127, 140, 141));

        formPanel.add(btnRegister);
        formPanel.add(btnClear);
        root.add(formPanel, BorderLayout.WEST);

        model = new DefaultTableModel(new String[]{"Имя", "ID", "Курс", "Email"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(24);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        JScrollPane tablePane = new JScrollPane(table);
        tablePane.setBorder(BorderFactory.createTitledBorder("Список зарегистрированных студентов"));
        root.add(tablePane, BorderLayout.CENTER);

        btnRegister.addActionListener(e -> registerStudent());
        btnClear.addActionListener(e -> clearForm());

        registrations.add(new CourseRegistration("Алиса", "C101", "Java Programming", "alice@mail.com"));
        registrations.add(new CourseRegistration("Боб", "C102", "Web Design", "bob@mail.com"));
        showAllRegistrations();
    }

    private void registerStudent() {
        String name = tfName.getText().trim();
        String id = tfId.getText().trim();
        String course = tfCourse.getText().trim();
        String email = tfEmail.getText().trim();

        if (name.isEmpty() || id.isEmpty() || course.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Пожалуйста, заполните все поля.", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return;
        }

        registrations.add(new CourseRegistration(name, id, course, email));
        showAllRegistrations();
        clearForm();

        JOptionPane.showMessageDialog(this, "Студент успешно зарегистрирован!", "Готово", JOptionPane.INFORMATION_MESSAGE);
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
    }

    private void clearForm() {
        tfName.setText("");
        tfId.setText("");
        tfCourse.setText("");
        tfEmail.setText("");
        tfName.requestFocus();
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CourseRegistrationSystem().setVisible(true));
    }
}
