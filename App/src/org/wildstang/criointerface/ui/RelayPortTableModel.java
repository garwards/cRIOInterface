package org.wildstang.criointerface.ui;

import org.wildstang.criointerface.data.RelayPort;

import javax.swing.table.AbstractTableModel;

/**
 * User: Steve
 * Date: 11/2/12
 */
public class RelayPortTableModel extends AbstractTableModel
{
   private RelayPort[] m_ports;

   private static final int NUM_ROWS = 1;
   public static final int NUM_COLS = 8;

   public RelayPortTableModel(RelayPort[] p_ports)
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
      return RelayPort.class;
   }

   @Override
   public boolean isCellEditable(int rowIndex, int columnIndex)
   {
      return false;
   }
}
