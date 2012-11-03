package org.wildstang.criointerface.ui.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * User: Steve
 * Date: 11/2/12
 */
public class NewAction extends AbstractAction
{

   public NewAction(String p_text, Icon p_icon, String p_desc, Integer p_mnemonic)
   {
      super(p_text, p_icon);
      putValue(SHORT_DESCRIPTION, p_desc);
      putValue(MNEMONIC_KEY, p_mnemonic);
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      System.out.println("New file");
   }
}
