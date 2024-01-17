package HoistingCranePckg;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DragMouseAdapter extends MouseAdapter {
	public void mousePressed(MouseEvent event) {
		var c = (JComponent) event.getSource();
		var handler = c.getTransferHandler();
		handler.exportAsDrag(c, event, TransferHandler.COPY);
	}
}