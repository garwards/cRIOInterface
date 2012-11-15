package org.wildstang.criointerface.ui;

import org.wildstang.criointerface.data.PWMPort;

import javax.swing.table.AbstractTableModel;

/**
 * User: Steve
 * Date: 11/2/12
 */
public class PWMPortTableModel extends AbstractTableModel
{
   private PWMPort[] m_ports;

   private static final int NUM_ROWS = 2;
   public static final int NUM_COLS = 5;

   public PWMPortTableModel(PWMPort[] p_ports)
   {
      m_ports = p_ports;
   }

   @Override
   public int getRowCount()
   {
      return NUM_ROWS;
   }

   @Override
   public int getColumnCount()
   {
      return NUM_COLS;
   }

   @Override
   public Object getValueAt(int rowIndex, int columnIndex)
   {
      return m_ports[(rowIndex * NUM_COLS) + columnIndex];
   }

   @Override
   public Class<?> getColumnClass(int columnIndex)
   {
      return PWMPort.class;
   }

   @Override
   public boolean isCellEditable(int rowIndex, int columnIndex)
   {
      return false;
   }


}
