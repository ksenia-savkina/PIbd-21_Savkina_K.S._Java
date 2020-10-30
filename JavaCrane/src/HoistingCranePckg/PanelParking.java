package HoistingCranePckg;

import java.awt.Graphics;
import javax.swing.JPanel;
import Interfaces.ICrane;

public class PanelParking extends JPanel {
	private ICrane crane;
	private boolean craneIsSet;
	private boolean parkingIsSet;
	private Parking<TrackedVehicle, Rink> parking;

	public PanelParking(boolean craneIsSet, boolean parkingIsSet) {
		this.craneIsSet = craneIsSet;
		this.parkingIsSet = parkingIsSet;
	}

	public void setCrane(ICrane crane) {
		this.crane = crane;
		craneIsSet = true;
	}

	public void setParking(Parking<TrackedVehicle, Rink> parking) {
		this.parking = parking;
		parkingIsSet = true;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (craneIsSet) {
			crane.drawCrane(g);
		}
		if (parkingIsSet) {
			parking.draw(g);
		}
	}
}
