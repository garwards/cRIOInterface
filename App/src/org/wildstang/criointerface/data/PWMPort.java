package org.wildstang.criointerface.data;

/**
 * User: Steve
 * Date: 10/30/12
 */
public class PWMPort extends DataPort
{
   private static final long serialVersionUID = 1;

   private boolean m_isServo = false;

   private boolean m_flipped = false;

   public int get8BitValue()
   {
      return m_8BitValue;
   }

   public void set8BitValue(int p_8BitValue)
   {
      m_8BitValue = p_8BitValue;
   }

   private int m_8BitValue;

   public PWMPort()
   {

   }

   public PWMPort(int p_port, String p_label, boolean p_inUse)
   {
      super(p_port, p_label, p_inUse);
   }

   public PWMPort(int p_port, String p_label, boolean p_inUse, boolean p_isServo, boolean p_flipped)
   {
      super(p_port, p_label, p_inUse);
      setServo(p_isServo);
      setFlipped(p_flipped);
   }


   public boolean isServo()
   {
      return m_isServo;
   }

   public void setServo(boolean p_servo)
   {
      m_isServo = p_servo;
   }

   public boolean isFlipped()
   {
      return m_flipped;
   }

   public void setFlipped(boolean p_flipped)
   {
      m_flipped = p_flipped;
   }

   @Override
   public String toString()
   {
      return String.valueOf(getPort());
   }
}
