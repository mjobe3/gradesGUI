package gradesGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class modifyDataDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private File file;
	ArrayList<Float> grades = new ArrayList<>();
	private JTextField textFieldDelete;
	private JTextField textFieldAdd;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Float> getGrades(){
		return grades;
	}
	
	public void initializeData()
	{
		if(file.getName().endsWith(".txt"))
		{
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
				String line = reader.readLine();
				while(line != null) {
					
					grades.add(Float.parseFloat(line));
					line = reader.readLine();
			
				}
				reader.close();
			} catch (IOException e) {
				System.out.print("Invalid file");
			}
		}
		
		else if(file.getName().endsWith(".csv"))
		{
			BufferedReader reader;
			String[] dataStr = null;
			
			try {
				reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
				String line = reader.readLine();
				dataStr = line.replaceAll("\\s+","").split(",");	
				line = reader.readLine();
				reader.close();
				for(int i = 0; i < dataStr.length; i++)
				{
					grades.add(Float.parseFloat(dataStr[i]));
				}
				
			} catch (IOException e) {
				System.out.print("Invalid file");
			}
		}
		
		else {
			System.out.print("Invalid file");
		}
	
		Collections.sort(grades);
	}
	
	/**
	 * Create the dialog.
	 */
	public modifyDataDialog(ArrayList<Float> inputGrades) {
		grades = inputGrades;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.EAST);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panelDelete = new JPanel();
				panel.add(panelDelete, BorderLayout.SOUTH);
				{
					JLabel lblDeleteValueFrom = new JLabel("Delete value from data: ");
					panelDelete.add(lblDeleteValueFrom);
				}
				{
					textFieldDelete = new JTextField();
					panelDelete.add(textFieldDelete);
					textFieldDelete.setColumns(10);
				}
			}
			{
				JPanel panelAdd = new JPanel();
				panel.add(panelAdd, BorderLayout.CENTER);
				{
					JLabel lblAddValueTo = new JLabel("Add value to data: ");
					panelAdd.add(lblAddValueTo);
				}
				{
					textFieldAdd = new JTextField();
					panelAdd.add(textFieldAdd);
					textFieldAdd.setColumns(10);
				}
			}
		}
		{
			JButton btnLoadFile = new JButton("Load File to Append");
			btnLoadFile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					final JFileChooser fc = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt", "*.csv", "csv");
					fc.setFileFilter(filter);
					int returnVal = fc.showOpenDialog(getContentPane());
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						file = fc.getSelectedFile();
						initializeData();
					}
				}
			});
			contentPanel.add(btnLoadFile, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!textFieldAdd.getText().equals(""))
							grades.add(Float.parseFloat(textFieldAdd.getText()));
						
						if(!textFieldDelete.getText().equals(""))
							grades.remove(Float.parseFloat(textFieldDelete.getText()));
						
						dispose();
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
						grades = null;
						
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
