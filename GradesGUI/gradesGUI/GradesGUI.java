package gradesGUI;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GradesGUI {

	private JFrame frame;
	public File file;
	int min = 0, max = 100;
	ArrayList<Float> grades = new ArrayList<>();
	ArrayList<Float> gradesInBounds = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GradesGUI window = new GradesGUI();
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
	public GradesGUI() {
		initialize();
	}

	
	public void assignBoundaries()
	{
		gradesInBounds.clear();
		for(float grade : grades)
		{
			if(grade <= max && grade >= min)
				gradesInBounds.add(grade);
		}
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
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		
		
		JPanel panelInfo = new JPanel();
		frame.getContentPane().add(panelInfo, BorderLayout.NORTH);
		panelInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblCurrentBoundaries = new JLabel("Current boundaries: ");
		panelInfo.add(lblCurrentBoundaries);
		
		JLabel lblBoundaries = new JLabel(min + " - " + max);
		panelInfo.add(lblBoundaries);
		
		JSeparator separator = new JSeparator();
		panelInfo.add(separator);
		
		JLabel lblCurrentFile = new JLabel("Current file: ");
		panelInfo.add(lblCurrentFile);
		
		JLabel lblCurrentFileName = new JLabel();
		panelInfo.add(lblCurrentFileName);
		
		JButton btnLoadFile = new JButton("Load File");
		btnLoadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grades.clear();
				final JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt", "*.csv", "csv");
				fc.setFileFilter(filter);
				int returnVal = fc.showOpenDialog(panel);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					lblCurrentFileName.setText(file.getName());
					panelInfo.repaint();
					initializeData();
				}
			}
		});
		panel.add(btnLoadFile);
		
		JButton btnSetBoundaries = new JButton("Set Boundaries");
		btnSetBoundaries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boundariesDialog dialog = new boundariesDialog();
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);

				if(dialog.getMin() != -1 || dialog.getMax() != -1)
				{
					min = dialog.getMin();
					max = dialog.getMax();
				}
				
				
				
				lblBoundaries.setText(min + " - " + max);
				}
		});
		panel.add(btnSetBoundaries);
		
		JButton btnModifyData = new JButton("Modify Data");
		btnModifyData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyDataDialog dialog = new modifyDataDialog(grades);
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				
				if (dialog.getGrades() != null)
				{
					//grades = dialog.getGrades();
				}
			}
		});
		panel.add(btnModifyData);
		
		JButton btnViewGrades = new JButton("View Grade Info");
		btnViewGrades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				assignBoundaries();
				gradeInfoDialog dialog = new gradeInfoDialog(gradesInBounds);
				dialog.setModal(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		panel.add(btnViewGrades);
		
		JButton btnReport= new JButton("Display Report");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnReport);
		
		JButton btnErrorLog = new JButton("Error log");
		btnErrorLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnErrorLog);
		
		
		
		
		
		

	}

}
