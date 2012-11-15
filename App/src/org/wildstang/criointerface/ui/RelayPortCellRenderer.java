package org.wildstang.criointerface.ui;

import org.wildstang.criointerface.data.PWMPort;
import org.wildstang.criointerface.data.RelayPort;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * User: Steve
 * Date: 11/2/12
 */
public class RelayPortCellRenderer implements TableCellRenderer
{
   @Override
   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
   {
      RelayPort port = (RelayPort)value;

      JPanel panel = new JPanel();
      panel.setLayout(new FlowLayout());

      // Port number
      JLabel portLabel = new JLabel(String.valueOf(port.getPort() + 1));
      portLabel.setBorder(BorderFactory.createLineBorder(Color.black));
      panel.add(portLabel);

      // Port A
      JPanel portA = new JPanel();
      portA.add(new JLabel("Port A"));
      panel.add(portA);
      if (port.isOutputA())
      {
         portA.setBackground(Color.green);
      }
      else
      {
         portA.setBackground(Color.red);
      }

      // Port B
      JPanel portB = new JPanel();
      portB.add(new JLabel("Port B"));
      panel.add(portB);
      if (port.isOutputB())
      {
         portB.setBackground(Color.green);
      }
      else
      {
         portB.setBackground(Color.red);
      }

      // Label
      JLabel label = new JLabel(port.getLabel());
      panel.add(label);

      panel.setSize(100, 100);

      return panel;
   }
}
