package org.wildstang.criointerface.ui;

import org.wildstang.criointerface.data.PWMPort;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * User: Steve
 * Date: 11/2/12
 */
public class PWMPortCellRenderer implements TableCellRenderer
{
   @Override
   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
   {
      PWMPort port = (PWMPort)value;

      JPanel panel = new JPanel();
      panel.setLayout(new FlowLayout());

      // Port number
      JLabel portLabel = new JLabel(String.valueOf(port.getPort() + 1));
      portLabel.setBorder(BorderFactory.createLineBorder(Color.black));
      panel.add(portLabel);

      // Is this a servo?
      JCheckBox servo = new JCheckBox("Servo: ");
      servo.setSelected(port.isServo());
      panel.add(servo);

      // Is this a servo?
      JCheckBox flipped = new JCheckBox("Flipped: ");
      flipped.setSelected(port.isFlipped());
      panel.add(flipped);

      // Output value
      JLabel output = new JLabel();
      output.setText(String.valueOf(port.get8BitValue()));
      panel.add(output);

      // Label
      JLabel label = new JLabel(port.getLabel());
      panel.add(label);

      panel.setSize(100, 100);

      return panel;
   }
}
