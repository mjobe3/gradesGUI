package gradesGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class boundariesDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldMin;
	private JTextField textFieldMax;
	private int min;
	private int max;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	}

	/**
	 * Create the dialog.
	 */
	public int getMin() {
		return min;
	}
	
	public int getMax() {
		return max;
	}
	
	public boundariesDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			JLabel lblSetMin = new JLabel("Set min: ");
			contentPanel.add(lblSetMin);
		}
		{
			textFieldMin = new JTextField();
			textFieldMin.setText("0");
			contentPanel.add(textFieldMin);
			textFieldMin.setColumns(10);
		}
		{
			JLabel lblSetMax = new JLabel("Set max: ");
			contentPanel.add(lblSetMax);
		}
		{
			textFieldMax = new JTextField();
			textFieldMax.setText("100");
			contentPanel.add(textFieldMax);
			textFieldMax.setColumns(10);
		}
		
		JLabel lblError= new JLabel("Invalid number");
		lblError.setText("Invalid number");
		lblError.setVisible(false);
		contentPanel.add(lblError);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							min = Integer.parseInt(textFieldMin.getText());
							max = Integer.parseInt(textFieldMax.getText());
							
							dispose();

						} catch(NumberFormatException error) {
							lblError.setVisible(true);
							
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						min = -1;
						max = -1;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
