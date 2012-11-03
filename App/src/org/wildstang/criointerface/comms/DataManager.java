package org.wildstang.criointerface.comms;

import org.wildstang.criointerface.data.DigitalModule;
import org.wildstang.criointerface.data.PWMPort;
import org.wildstang.criointerface.data.RelayPort;

import java.io.Serializable;

/**
 * User: Steve
 * Date: 10/30/12
 */
public class DataManager implements Serializable
{

   private static DataManager S_INSTANCE = new DataManager();

   private DigitalModule m_digitalModule = new DigitalModule();

   private DataManager()
   {

   }

   public static DataManager getInstance()
   {
      return S_INSTANCE;
   }

   public void updatePWM(int p_port, int p_value)
   {
      System.out.println("Updating PWM port " + p_port + " with value " + p_value);
      m_digitalModule.getPwm(p_port).set8BitValue(p_value);
   }

   public void updateRelay(int p_port, int p_valA, int p_valB)
   {
      RelayPort port = m_digitalModule.getRelays(p_port);
      port.setOutputA(p_valA > 0);
      port.setOutputB(p_valB > 0);
   }

   public PWMPort[] getPWMs()
   {
      return m_digitalModule.getPwm();
   }

   public RelayPort[] getRelays()
   {
      return m_digitalModule.getRelays();
   }
}
