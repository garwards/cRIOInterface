package org.wildstang.criointerface.data;

import java.io.Serializable;

/**
 * User: Steve
 * Date: 10/30/12
 */
public class DigitalModule implements Serializable
{

   private static final long serialVersionUID = 1;

   private PWMPort[] m_pwm;
   private RelayPort[] m_relays;

   public DigitalModule()
   {
      m_pwm = new PWMPort[10];
      m_relays = new RelayPort[8];

      for (int i = 0; i < 10; i++)
      {
         m_pwm[i] = new PWMPort();
         m_pwm[i].setPort(i);
      }

      for (int i = 0; i < 8; i++)
      {
         m_relays[i] = new RelayPort();
         m_relays[i].setPort(i);
      }
   }

   public PWMPort[] getPwm()
   {
      return m_pwm;
   }

   public void setPwm(PWMPort[] p_pwm)
   {
      m_pwm = p_pwm;
   }

   public PWMPort getPwm(int p_port)
   {
      return m_pwm[p_port];
   }

   public RelayPort[] getRelays()
   {
      return m_relays;
   }

   public void setRelays(RelayPort[] p_relays)
   {
      m_relays = p_relays;
   }

   public RelayPort getRelays(int p_port)
   {
      return m_relays[p_port];
   }


}


