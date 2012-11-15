package org.wildstang.criointerface.ui.actions;

import org.wildstang.criointerface.comms.DataManager;
import org.wildstang.criointerface.ui.ConfigFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: Steve
 * Date: 11/3/12
 */
public class ConfigOKAction extends AbstractAction
{
   private ConfigFrame m_config;

   public ConfigOKAction(ConfigFrame p_parent)
   {
      super("OK");
      m_config = p_parent;
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      System.out.println("Swapping real module for temp");
      m_config.getAppFrame().getApp().getDataManager().setDigitalModule(m_config.getCopyDigitalModule());
      m_config.updateAppFrameTable();
      m_config.setVisible(false);
      m_config.dispose();
   }
}
