package gradesGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class gradeInfoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	ArrayList<Float> grades = new ArrayList<>();
	private JTable table;
	private JTable myTable;


	/**
	 * Launch the application.
	 */
	
	
	
	public DefaultTableModel toTable()
	{
		DefaultTableModel tableModel = new DefaultTableModel();

		int numRows = (grades.size() + 3) / 4;
		
		Object[][] table = new Object[numRows][4];
		
		int gradeIndex = 0;
		int rowIndex = 0;
		int columnIndex = 0;
		
		while(gradeIndex < grades.size())
		{
			while(columnIndex < 4)
			{
				while(rowIndex < numRows)
				{
					table[rowIndex][columnIndex] = grades.get(gradeIndex);
				
					rowIndex++;
					gradeIndex++;
				}
				
				columnIndex++;
				rowIndex = 0;
			}
			columnIndex = 0;
		}
		
		
		for(int i = 0; i < numRows; i++)
			tableModel.addRow(table[i]);
		
		return tableModel;
	}

	
	public static void main(String[] args) {
	}
	
	public double getMean() {
		  float sum = 0;
		  if(!grades.isEmpty()) {
		    for (float grade : grades) {
		        sum += grade;
		    }
		    return (double) sum / grades.size();
		  }
		  return sum;
		}

	public double getMedian() {
	    int middle = grades.size()/2;
	    if (grades.size() % 2 == 1) {
	        return grades.get(middle);
	    } else {
	        return (grades.get(middle - 1) + grades.get(middle)) / 2.0;
	    }
	}
	
	public float getMode() {
	    float maxValue = 0, maxCount = 0;

	    for (int i = 0; i < grades.size(); ++i) {
	        int count = 0;
	        for (int j = 0; j < grades.size(); ++j) {
	            if (grades.get(j) == grades.get(i)) ++count;
	        }
	        if (count > maxCount) {
	            maxCount = count;
	            maxValue = grades.get(i);
	        }
	    }

	    return maxValue;
	}
	
	public float getMax()
	{
		float max = -999;
		
		for(float grade : grades)
		{
			if(grade > max)
				max = grade;
		}
		
		return max;
	}
	
	public float getMin() {
		float min = 999;
		
		for(float grade : grades)
		{
			if(grade < min)
				min = grade;
		}
		
		return min;
	}
	
	/**
	 * Create the dialog.
	 */
	public gradeInfoDialog(ArrayList<Float> inputGrades) {
		grades = inputGrades;
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		{
			JSplitPane splitPane = new JSplitPane();
			contentPanel.add(splitPane);
			{

				JScrollPane scrollPane = new JScrollPane();
				splitPane.setRightComponent(scrollPane);
				{
					int numRows = (grades.size() + 3) / 4;

					Object [] columnTitles = {"", "", "", ""};
					DefaultTableModel tableModel = new DefaultTableModel(columnTitles, 0);
					
						
						
						Object[][] table = new Object[numRows][4];
						
						int gradeIndex = 0;
						int rowIndex = 0;
						int columnIndex = 0;
						
						while(gradeIndex < grades.size())
						{
							while(columnIndex < 4)
							{
								while(rowIndex < numRows && gradeIndex < grades.size())
								{
									table[rowIndex][columnIndex] = grades.get(gradeIndex);
			
									rowIndex++;
									gradeIndex++;
								}
								
								columnIndex++;
								rowIndex = 0;
							}
							columnIndex = 0;
						}
						
					for(int i = 0; i < numRows; i++)
					{
						tableModel.addRow(table[i]);
					}
				            
					myTable = new JTable(tableModel);
					scrollPane.setViewportView(myTable);
				}

			{
				JPanel panel = new JPanel();
				splitPane.setLeftComponent(panel);
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				{
					JPanel panelEntries = new JPanel();
					FlowLayout fl_panelEntries = (FlowLayout) panelEntries.getLayout();
					panel.add(panelEntries);
					{
						JLabel lblNumEntries = new JLabel("# of Entries: ");
						panelEntries.add(lblNumEntries);
					}
					{
						JLabel lblEntriesCount = new JLabel(Integer.toString(grades.size()));
						panelEntries.add(lblEntriesCount);
					}
				}
				{
					JPanel panelMean = new JPanel();
					panel.add(panelMean);
					{
						JLabel lblMeanLabel = new JLabel("Mean: ");
						panelMean.add(lblMeanLabel);
					}
					{
						JLabel lblMean = new JLabel(Double.toString(getMean()));
						panelMean.add(lblMean);
					}
				}
				{
					JPanel panelMedian = new JPanel();
					panel.add(panelMedian);
					{
						JLabel lblMedianLabel = new JLabel("Median: ");
						panelMedian.add(lblMedianLabel);
					}
					{
						JLabel lblMedian = new JLabel(Double.toString(getMedian()));
						panelMedian.add(lblMedian);
					}
				}
				{
					JPanel panelMode = new JPanel();
					panel.add(panelMode);
					{
						JLabel lblModeLabel = new JLabel("Mode: ");
						panelMode.add(lblModeLabel);
					}
					{
						JLabel lblMode = new JLabel(Float.toString(getMode()));
						panelMode.add(lblMode);
					}
				}
				{
					JPanel panelMax = new JPanel();
					panel.add(panelMax);
					{
						JLabel lblMaxLabel = new JLabel("Max:");
						panelMax.add(lblMaxLabel);
					}
					{
						JLabel lblMax = new JLabel(Float.toString(getMax()));
						panelMax.add(lblMax);
					}
				}
				{
					JPanel panelMin = new JPanel();
					panel.add(panelMin);
					{
						JLabel lblMinLabel = new JLabel("Min: ");
						panelMin.add(lblMinLabel);
					}
					{
						JLabel lblMin = new JLabel(Float.toString(getMin()));
						panelMin.add(lblMin);
					}
				}
				{
					JPanel panelGraph = new JPanel();
					panel.add(panelGraph);
					panelGraph.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
					{
						JButton btnShowGraph = new JButton("Show Graph");
						btnShowGraph.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							}
						});
						btnShowGraph.setHorizontalAlignment(SwingConstants.LEFT);
						panelGraph.add(btnShowGraph);
					}
					{
						JButton btnShowDistribution = new JButton("Show Distribution");
						btnShowDistribution.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							}
						});
						btnShowDistribution.setHorizontalAlignment(SwingConstants.LEFT);
						panelGraph.add(btnShowDistribution);
					}
				}
			}
			
			}}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
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
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
