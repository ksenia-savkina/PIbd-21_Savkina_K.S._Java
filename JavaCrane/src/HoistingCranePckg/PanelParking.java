package HoistingCranePckg;

import java.awt.Graphics;
import javax.swing.JPanel;
import Interfaces.ICrane;

public class PanelParking extends JPanel {

	private ICrane crane;
	private Parking parking;
	private boolean craneIsSet;
	private boolean parkingCollectionIsSet;
	private String selectedItem = null;
	private ParkingCollection parkingCollection;

	public PanelParking(boolean craneIsSet, boolean parkingCollectionIsSet) {
		this.craneIsSet = craneIsSet;
		this.parkingCollectionIsSet = parkingCollectionIsSet;
	}

	public void setCrane(ICrane crane) {
		this.crane = crane;
		craneIsSet = true;
	}

	public void setParkingCollection(ParkingCollection parkingCollection) {
		this.parkingCollection = parkingCollection;
		parkingCollectionIsSet = true;
	}

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (craneIsSet) {
			crane.drawCrane(g);
		}
		if (selectedItem != null) {
			if (parkingCollectionIsSet) {
				parkingCollection.get(selectedItem).draw(g);
			}
		}
	}
}
