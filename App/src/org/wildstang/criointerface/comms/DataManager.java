package org.wildstang.criointerface.comms;

import org.wildstang.criointerface.data.DigitalModule;
import org.wildstang.criointerface.data.PWMPort;
import org.wildstang.criointerface.data.RelayPort;
import org.wildstang.criointerface.ui.PWMPortTableModel;
import org.wildstang.criointerface.ui.RelayPortTableModel;

import java.io.Serializable;

/**
 * User: Steve
 * Date: 10/30/12
 */
public class DataManager implements Serializable
{
   private static final long serialVersionUID = 1;

   private DigitalModule m_digitalModule;
   private PWMPortTableModel m_pwmModel;
   private RelayPortTableModel m_relayModel;

   public DataManager()
   {

   }

   public void setPWMModel(PWMPortTableModel p_model)
   {
      m_pwmModel = p_model;
   }

   public void setRelayModel(RelayPortTableModel p_model)
   {
      m_relayModel = p_model;
   }

   public void updatePWM(int p_port, int p_value)
   {
      System.out.println("Updating PWM port " + p_port + " with value " + p_value);
      m_digitalModule.getPwm(p_port).set8BitValue(p_value);
      m_pwmModel.fireTableDataChanged();
   }

   public void updateRelay(int p_port, int p_valA, int p_valB)
   {
      RelayPort port = m_digitalModule.getRelays(p_port);
      port.setOutputA(p_valA > 0);
      port.setOutputB(p_valB > 0);
      m_relayModel.fireTableDataChanged();
   }

   public DigitalModule getDigitalModule()
   {
      return m_digitalModule;
   }

   public void setDigitalModule(DigitalModule p_digitalModule)
   {
      m_digitalModule = p_digitalModule;
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
