package org.wildstang.criointerface.ui.actions;

import org.wildstang.criointerface.ui.ConfigFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: Steve
 * Date: 11/3/12
 */
public class ConfigCancelAction extends AbstractAction
{
   private ConfigFrame m_config;

   public ConfigCancelAction(ConfigFrame p_parent)
   {
      super("Cancel");
      m_config = p_parent;
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      m_config.setVisible(false);
      m_config.dispose();
   }
}
