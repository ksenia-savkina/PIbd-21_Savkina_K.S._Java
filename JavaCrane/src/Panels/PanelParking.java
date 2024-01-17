package Panels;

import java.awt.Graphics;

import javax.swing.JPanel;

import HoistingCranePckg.ParkingCollection;

import Interfaces.ICrane;

public class PanelParking extends JPanel {

	private boolean parkingCollectionIsSet;
	private String selectedItem = null;
	private ParkingCollection parkingCollection;

	public PanelParking(boolean parkingCollectionIsSet) {
		this.parkingCollectionIsSet = parkingCollectionIsSet;
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
		if (selectedItem != null) {
			if (parkingCollectionIsSet) {
				parkingCollection.get(selectedItem).draw(g);
			}
		}
	}
}
