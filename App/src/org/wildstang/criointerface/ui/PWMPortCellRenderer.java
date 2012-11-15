package org.wildstang.criointerface.ui;

import org.wildstang.criointerface.data.PWMPort;
import org.wildstang.criointerface.ui.panels.PWMPanel;

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
      PWMPanel pwmPanel = new PWMPanel(port);
      JPanel panel = pwmPanel.createTableCellPanel();

      return panel;
   }
}
