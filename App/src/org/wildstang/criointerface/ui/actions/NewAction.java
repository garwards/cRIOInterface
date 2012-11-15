package org.wildstang.criointerface.ui.actions;

import org.wildstang.criointerface.data.DigitalModule;
import org.wildstang.criointerface.ui.AppFrame;
import org.wildstang.criointerface.ui.ConfigFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * User: Steve
 * Date: 11/2/12
 */
public class NewAction extends AbstractAction
{
   private AppFrame m_parent;

   public NewAction(AppFrame p_parent, String p_text, Icon p_icon, String p_desc, Integer p_mnemonic)
   {
      super(p_text, p_icon);
      m_parent = p_parent;
      putValue(SHORT_DESCRIPTION, p_desc);
      putValue(MNEMONIC_KEY, p_mnemonic);
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      System.out.println("New file");
      ConfigFrame config = new ConfigFrame(m_parent, new DigitalModule());
      config.init();
      config.setLocationRelativeTo(m_parent);
      config.setVisible(true);
   }
}
