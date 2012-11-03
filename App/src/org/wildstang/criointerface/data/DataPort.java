package org.wildstang.criointerface.data;

import java.io.Serializable;

/**
 * User: Steve
 * Date: 10/30/12
 */
public abstract class DataPort implements Serializable
{

   private static final long serialVersionUID = 1;

   // Port number
   private int m_port = -1;

   // The name/use for this port
   private String m_label;

   // Indicates this port is in use
   private boolean m_inUse = false;

   public DataPort()
   {

   }

   public DataPort(int p_port, String p_label, boolean p_inUse)
   {
      m_port = p_port;
      m_label = p_label;
      m_inUse = p_inUse;
   }

   public String getLabel()
   {
      return m_label;
   }

   public void setLabel(String p_label)
   {
      m_label = p_label;
   }

   public boolean isInUse()
   {
      return m_inUse;
   }

   public void setInUse(boolean p_inUse)
   {
      m_inUse = p_inUse;
   }

   public int getPort()
   {

      return m_port;
   }

   public void setPort(int p_port)
   {
      m_port = p_port;
   }
}
