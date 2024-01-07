import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class AddPage {
    private JFrame frame;
    private JTextField nameHolder, addressHolder, phoneNbHolder, salaryHolder, ageHolder;
    private JButton submitButton, backButton;
    private JLabel nameField, addressField, phoneNbField, salaryField, ageField;

    public AddPage() {
        frame = new JFrame("Add Employee");

        // Instantiate text fields
        nameHolder = new JTextField();
        addressHolder = new JTextField();
        phoneNbHolder = new JTextField();
        salaryHolder = new JTextField();
        ageHolder = new JTextField();

        // Key listeners to restrict input types
        setupInputRestrictions();

        // Instantiate buttons
        submitButton = new JButton("Submit");
        backButton = new JButton("Back");

        // Set labels
        setupLabels();

        // Setup components
        setupComponents();

        // Set action listener for the back button
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LaunchPage(); // Assuming you have this class defined elsewhere
            }
        });

        // Set action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int age = Integer.parseInt(ageHolder.getText());
                    if (age <= 18 || age > 130) {
                        throw new AgeException("Age must be between 18 and 130.");
                    }

                    int salary = Integer.parseInt(salaryHolder.getText());
                    if (salary <= 0) {
                        throw new SalaryException("Salary must be greater than 0.");
                    }

                    JOptionPane.showMessageDialog(frame, "Submission successful!");
                    // TODO: Handle the actual submission

                } catch (AgeException | SalaryException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers for age and salary.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setupInputRestrictions() {
        nameHolder.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && c != KeyEvent.VK_SPACE) {
                    e.consume();
                }
            }
        });
        phoneNbHolder.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        ageHolder.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        salaryHolder.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
    }

    private void setupLabels() {
        nameField = new JLabel("Full Name:");
        addressField = new JLabel("Address:");
        phoneNbField = new JLabel("Phone Number:");
        salaryField = new JLabel("Salary:");
        ageField = new JLabel("Age: ");
    }

    private void setupComponents() {
        // Set bounds and add components to the frame
        nameField.setBounds(20, 20, 100, 25);
        nameHolder.setBounds(140, 20, 200, 25);

        ageField.setBounds(20, 60, 100, 25);
        ageHolder.setBounds(140, 60, 200, 25);

        addressField.setBounds(20, 100, 100, 25);
        addressHolder.setBounds(140, 100, 200, 25);

        phoneNbField.setBounds(20, 140, 120, 25);
        phoneNbHolder.setBounds(140, 140, 200, 25);

        salaryField.setBounds(20, 180, 100, 25);
        salaryHolder.setBounds(140, 180, 200, 25);

        backButton.setBounds(140, 300, 100, 25);
        submitButton.setBounds(240, 300, 100, 25);

        frame.add(nameField);
        frame.add(nameHolder);
        frame.add(ageField);
        frame.add(ageHolder);
        frame.add(addressField);
        frame.add(addressHolder);
        frame.add(phoneNbField);
        frame.add(phoneNbHolder);
        frame.add(salaryField);
        frame.add(salaryHolder);
        frame.add(backButton);
        frame.add(submitButton);
    }

    public static void main(String[] args) {
        new AddPage(); // For testing purposes
    }
}


