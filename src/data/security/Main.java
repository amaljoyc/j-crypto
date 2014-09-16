package data.security;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Main {
	private static Encryption encryptionService = new Encryption();
	private static Decryption decryptionService = new Decryption();

	public static Encryption getEncryptionService() {
		return encryptionService;
	}

	public static Decryption getDecryptionService() {
		return decryptionService;
	}

	public static void main(String[] args) {
		JFrame window = new ClassFrame("j-crypto");
		window.setVisible(true);
		window.setTitle("j-crypto");
		window.setSize(600, 700);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class ClassFrame extends JFrame {

	private static final long serialVersionUID = 915159725526441709L;

	public ClassFrame(String title) {
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		createFields(layout);
	}

	private String plainText;
	private String key;
	private JTextField keyTextField;
	private JTextArea textArea;
	private JTextArea encryptedTextArea;
	private JTextArea decryptedTextArea;
	private JButton encryptButton = new JButton("Encrypt");
	private JButton decryptButton = new JButton("Decrypt");

	private void createFields(SpringLayout layout) {
		Container c = getContentPane();
		JLabel keyLabel = new JLabel("Enter Pass phrase", JLabel.TRAILING);
		keyTextField = new JTextField(18);
		keyLabel.setLabelFor(keyTextField);

		JLabel textLabel = new JLabel("Enter plain text", JLabel.TRAILING);
		textArea = new JTextArea(10, 40);
		textArea.setLineWrap(true);
		keyLabel.setLabelFor(textArea);

		JLabel encryptedTextLabel = new JLabel("Encrypted Text", JLabel.TRAILING);
		encryptedTextArea = new JTextArea(10, 40);
		encryptedTextArea.setEditable(false);
		encryptedTextArea.setLineWrap(true);
		JLabel decryptedTextLabel = new JLabel("Decrypted Text", JLabel.TRAILING);
		decryptedTextArea = new JTextArea(10, 40);
		decryptedTextArea.setEditable(false);
		decryptedTextArea.setLineWrap(true);

		layout.putConstraint(SpringLayout.WEST, keyTextField, 5, SpringLayout.EAST, keyLabel);
		layout.putConstraint(SpringLayout.NORTH, textLabel, 8, SpringLayout.SOUTH, keyLabel);
		layout.putConstraint(SpringLayout.WEST, textArea, 5, SpringLayout.EAST, textLabel);
		layout.putConstraint(SpringLayout.NORTH, textArea, 8, SpringLayout.SOUTH, textLabel);
		layout.putConstraint(SpringLayout.WEST, encryptButton, -95, SpringLayout.EAST, textArea);
		layout.putConstraint(SpringLayout.NORTH, encryptButton, 8, SpringLayout.SOUTH, textArea);

		layout.putConstraint(SpringLayout.NORTH, encryptedTextLabel, 8, SpringLayout.SOUTH, encryptButton);
		layout.putConstraint(SpringLayout.WEST, encryptedTextArea, 5, SpringLayout.EAST, encryptedTextLabel);
		layout.putConstraint(SpringLayout.NORTH, encryptedTextArea, 5, SpringLayout.SOUTH, encryptedTextLabel);
		layout.putConstraint(SpringLayout.WEST, decryptButton, -95, SpringLayout.EAST, encryptedTextArea);
		layout.putConstraint(SpringLayout.NORTH, decryptButton, 5, SpringLayout.SOUTH, encryptedTextArea);
		layout.putConstraint(SpringLayout.NORTH, decryptedTextLabel, 8, SpringLayout.SOUTH, decryptButton);
		layout.putConstraint(SpringLayout.WEST, decryptedTextArea, 5, SpringLayout.EAST, decryptedTextLabel);
		layout.putConstraint(SpringLayout.NORTH, decryptedTextArea, 5, SpringLayout.SOUTH, decryptedTextLabel);

		c.add(keyLabel);
		c.add(keyTextField);
		c.add(textLabel);
		c.add(textArea);
		c.add(encryptButton);
		c.add(encryptedTextLabel);
		c.add(encryptedTextArea);
		c.add(decryptedTextLabel);
		c.add(decryptedTextArea);
		c.add(decryptButton);

		decryptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				key = keyTextField.getText();
				String encryptedText = encryptedTextArea.getText();
				if (key.equals("")) {
					showErrorDialog("Enter a valid key");
					return;
				}
				if (encryptedText.equals("")) {
					showErrorDialog("Perform the encryption of plain text first");
					return;
				}
				String decryptedText = Main.getDecryptionService().decrypt(key, encryptedText);
				decryptedTextArea.setText(decryptedText);
			}
		});

		encryptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				key = keyTextField.getText();
				plainText = textArea.getText();
				if (key.equals("")) {
					showErrorDialog("Enter a valid key");
					return;
				}
				if (plainText.equals("")) {
					showErrorDialog("Enter a valid plain text");
					return;
				}
				String encryptedText = Main.getEncryptionService().encrypt(key, plainText);
				encryptedTextArea.setText(encryptedText);
			}
		});
	}

	private void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
	}
}