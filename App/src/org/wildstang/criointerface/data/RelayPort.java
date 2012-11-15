package org.wildstang.criointerface.data;

/**
 * User: Steve
 * Date: 10/30/12
 */
public class RelayPort extends DataPort
{
   private static final long serialVersionUID = 1;

   private boolean m_outputA;
   private boolean m_outputB;

   public RelayPort()
   {

   }

   public RelayPort(int p_port, String p_label, boolean p_inUse)
   {
      super(p_port, p_label, p_inUse);
   }

   public boolean isOutputA()
   {
      return m_outputA;
   }

   public void setOutputA(boolean p_outputA)
   {
      m_outputA = p_outputA;
   }

   public boolean isOutputB()
   {
      return m_outputB;
   }

   public void setOutputB(boolean p_outputB)
   {
      m_outputB = p_outputB;
   }
}
