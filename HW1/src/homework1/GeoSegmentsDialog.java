package homework1;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * A JDailog GUI for choosing a GeoSegemnt and adding it to the route shown
 * by RoutDirectionGUI.
 * <p>
 * A figure showing this GUI can be found in homework assignment #1.
 */
public class GeoSegmentsDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	// the RouteDirectionsGUI that this JDialog was opened from
	private RouteFormatterGUI parent;
	
	// a control contained in this 
	private JList<GeoSegment> lstSegments;
	
	/**
	 * Creates a new GeoSegmentsDialog JDialog.
	 * @effects Creates a new GeoSegmentsDialog JDialog with owner-frame
	 * 			owner and parent pnlParent
	 */
	public GeoSegmentsDialog(Frame owner, RouteFormatterGUI pnlParent) {
		// create a modal JDialog with the an owner Frame (a modal window
		// in one that doesn't allow other windows to be active at the
		// same time).
		super(owner, "Please choose a GeoSegment", true);
		this.parent = pnlParent;
		this.getContentPane().add(this.getPanelContnet());
		this.pack();
	}
	
	/**
	 * Changes the list of segments in a way that only segments for which p2 == end are available
	 * @require this.end != null
	 * @effects adds only segments with p2 = end
	 * 			else : adds all segments
	 * 			
	 */
	private void filterSegmentList(GeoPoint end) {
		DefaultListModel<GeoSegment> model =
				(DefaultListModel<GeoSegment>)(this.lstSegments.getModel());
		model.removeAllElements();
		for (GeoSegment segment : ExampleGeoSegments.segments) {
			if (end == null || segment.getP1().equals(end)) {
				model.addElement(segment);
			}
		}
	}
	
	/**
	 * 
	 * @return a JComponent that describes the GeoSegmentDialog
	 */
	private JComponent getPanelContnet() {
		JPanel panel = new JPanel();
				
		// Create list component
		lstSegments = new JList<>(new DefaultListModel<GeoSegment>());
		this.filterSegmentList(null);
		lstSegments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrlSegments = new JScrollPane(lstSegments);
		scrlSegments.setPreferredSize(new Dimension(700, 300));
		JLabel lblSegments = new JLabel("Geo Segments:");
		lblSegments.setLabelFor(lstSegments);
		
		
		// Create buttons
		JButton btn_add = new JButton("Add");
		JButton btn_cancel = new JButton("Cancel");
		
		btn_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				GeoSegment segment = lstSegments.getSelectedValue();
				if (segment == null) {
					System.out.println("Add button clicked w\\o selection");
					return;
				}
				GeoPoint end = segment.getP2();
				System.out.println("P2 = " + end.toString());
				filterSegmentList(end);
				System.out.println("list filtered");
				parent.addSegment(segment);
				setVisible(false);
			}
		});
		
		btn_cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		panel.setLayout(gridbag);
		
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 5;
		c.gridheight = 1;
		c.insets = new Insets(0,0,0,0);
		gridbag.setConstraints(lblSegments, c);
		panel.add(lblSegments);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 10;
		c.gridheight = 5;
		c.insets = new Insets(0,0,0,0);
		gridbag.setConstraints(scrlSegments, c);
		panel.add(scrlSegments);
		
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		c.gridheight = 1;
		gridbag.setConstraints(btn_add, c);
		panel.add(btn_add);
		
		c.gridx = 9;
		c.gridy = 6;
		c.gridwidth = 1;
		c.gridheight = 1;
		gridbag.setConstraints(btn_cancel, c);
		panel.add(btn_cancel);
		
		return panel;
	}
}
