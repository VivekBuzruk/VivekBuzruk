package coin.sarvatech.dAddressBookIndia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.Insets;

import javax.swing.JButton;

import javax.swing.JDialog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



/** This is a utility class for displaying a dialog that asks for multiple values.
 * Uses javax.swing.JOptionPane
 */
public class MultiInputPane extends JOptionPane
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField [] fields;
    private boolean ok = false;
    private static final int FIELD_COLUMNS = 30;
    // private static final int LABEL_COLUMN = 15;
    private static int NEW_LAYOUT = 1;
    
    /** Pop up a dialog asking for multiple items of input
     *
     *  @param parentComponent the parent Component of the dialog that is shown
     *  @param prompts the prompts to display
     *  @param initialValues the initial values to display for each item -
     *         this parameter can be null, in which case no initial values
     *         are specified; or individual elements can be null,
     *         indicating that no initial value is specified for a particular
     *         field
     *  @param title the title for this dialog
     *  @return an array of values corresponding to the various prompts,
     *          or null if the user cancelled
     */
    public static String [] showMultiInputDialog(Component parentComponent,
                                                 String [] prompts,
                                                 String [] initialValues,
                                                 String title)
    {
        MultiInputPane pane = new MultiInputPane(prompts, initialValues);
        JDialog dialog = pane.createDialog(parentComponent, 
                                           title != null ? title : "MultiInputPane");       
        dialog.pack();
       
        dialog.setVisible(true);
        
        if (! pane.ok)
            return null;
            
        String [] results = new String [prompts.length];
        for (int i = 0; i < prompts.length; i ++)
            results[i] = pane.fields[i].getText();
            
        return results;
    }

    /** Pop up a dialog asking for multiple items of input
     *
     *  @param parentComponent the parent Component of the dialog that is shown
     *  @param prompts the prompts to display
     *  @param title the title for this dialog
     *  @return an array of values corresponding to the various prompts,
     *          or null if the user cancelled
     */
    public static String [] showMultiInputDialog(Component parentComponent,
                                                 String [] prompts,
                                                 String title)
    {
        return showMultiInputDialog(parentComponent, prompts, null, title);
    }
    
    /** Pop up a dialog asking for multiple items of input
     *
     *  @param parentComponent the parent Component of the dialog that is shown
     *  @param prompts the prompts to display
     *  @param initialValues the initial values to display for each item -
     *         this parameter can be null, in which case no initial values
     *         are specified; or individual elements can be null,
     *         indicating that no initial value is specified for a particular
     *         field
     *  @return an array of values corresponding to the various prompts,
     *          or null if the user cancelled
     */
    public static String [] showMultiInputDialog(Component parentComponent,
                                                 String [] prompts,
                                                 String [] initialValues)
    {
        return showMultiInputDialog(parentComponent, prompts, initialValues, null);
    }
    
    /** Pop up a dialog asking for multiple items of input
     *
     *  @param parentComponent the parent Component of the dialog that is shown
     *  @param prompts the prompts to display
     *  @return an array of values corresponding to the various prompts,
     *          or null if the user cancelled
     */
    public static String [] showMultiInputDialog(Component parentComponent,
                                                 String [] prompts)
    {
        return showMultiInputDialog(parentComponent, prompts, null, null);
    }
    
    /** Constructor used by the above
     *
     *  @param prompts the prompts to display
     *  @param initialValues the initial values to display for each item -
     *         this parameter can be null, in which case no initial values
     *         are specified; or individual elements can be null,
     *         indicating that no initial value is specified for a particular
     *         field
     */
    private MultiInputPane(String [] prompts, String [] initialValues)
    {
        super();
        removeAll();
        
		if (NEW_LAYOUT == 1) {
			MultiInputPane1(prompts, initialValues);
			return;
		}    
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
        // setLayout(new GridLayout(prompts.length + 1, 2, 5, 5));
		
        fields = new JTextField[prompts.length];
        
        for (int i = 0; i < prompts.length; i ++)
        {
        	JLabel thisLabel = new JLabel(prompts[i]);
        	Dimension labelDimension = thisLabel.getSize();
        	//System.out.println("Width of this label " + prompts[i] + " = " + labelDimension.toString());
        	c.fill = GridBagConstraints.HORIZONTAL;
        	c.weightx = 0.2;
        	// c.ipadx = 0;
        	// c.ipady = 0;
        	// c.gridwidth = 2;
        	// c.anchor = GridBagConstraints.PAGE_END;
        	c.insets = new Insets(2,2,5,0);  //top padding, (int top, int left, int bottom, int right)

        	// c.gridwidth = 2;   //2 columns wide
        	c.gridx = 0;
        	c.gridy = i;
        	
        	add(thisLabel, c);
            // add(thisLabel);
            fields[i] = new JTextField(FIELD_COLUMNS);
        	Dimension fieldDimension = thisLabel.getSize();
        	// System.out.println("Width of this field " + prompts[i] + " = " + fieldDimension.toString());
        	c.fill = GridBagConstraints.HORIZONTAL;
        	c.weightx = 0.7;
        	// c.ipadx = 0;
        	// c.ipady = 0;
        	// c.gridwidth = 2;
        	// c.anchor = GridBagConstraints.PAGE_END;
        	c.insets = new Insets(2,2,5,0);  //top padding, (int top, int left, int bottom, int right)

        	// c.gridwidth = 2;   //2 columns wide
        	c.gridx = 1;
        	c.gridy = i;
            add(fields[i], c);
            if (initialValues != null && initialValues[i] != null) {
                fields[i].setText(initialValues[i]);
            } else {
            	;
            }
        }
        addButtons(this);
    }
    
	private void addButtons(Container parentPanel) {
		JPanel okPanel = new JPanel();
		JButton okButton = new JButton("OK");
		okPanel.add(okButton); // May appear in
		parentPanel.add(okPanel);

		JPanel cancelPanel = new JPanel();
		JButton cancelButton = new JButton("Cancel");
		cancelPanel.add(cancelButton);
		parentPanel.add(cancelPanel);

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ok = true;
				getTopLevelAncestor().setVisible(false);
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ok = false;
				getTopLevelAncestor().setVisible(false);
			}
		});
	}

	/**
	 * Alternate IMplementation
	 *
	 */

	private void MultiInputPane1(String[] prompts, String[] initialValues) {
		GridBagLayout gridbag = new GridBagLayout();

		setLayout(gridbag);
		GridBagConstraints c = new GridBagConstraints();

		fields = new JTextField[prompts.length];
		JLabel labels[] = new JLabel[prompts.length];

		for (int i = 0; i < prompts.length; i++) {
			labels[i] = new JLabel(prompts[i]);
			fields[i] = new JTextField(FIELD_COLUMNS);
			if (initialValues != null && initialValues[i] != null) {
				fields[i].setText(initialValues[i]);
			} else {
				;
			}
		}
		addLabelTextRows(labels, fields, this);

		addButtons(this);
	}

	private void addLabelTextRows(JLabel[] labels, JTextField[] textFields, Container container) {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		int numLabels = labels.length;

		for (int i = 0; i < numLabels; i++) {
			c.gridwidth = GridBagConstraints.RELATIVE; // next-to-last
			c.fill = GridBagConstraints.NONE; // reset to default
			c.weightx = 0.0; // reset to default
			container.add(labels[i], c);

			c.gridwidth = GridBagConstraints.REMAINDER; // end row
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 1.0;
			container.add(textFields[i], c);
		}
	}
}
