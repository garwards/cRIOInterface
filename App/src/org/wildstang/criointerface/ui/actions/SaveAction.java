package org.wildstang.criointerface.ui.actions;

import org.wildstang.criointerface.ui.AppFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * User: Steve
 * Date: 11/3/12
 */
public class SaveAction extends AbstractAction
{
   private AppFrame m_parent;

   public SaveAction(AppFrame p_parent, String p_text, Icon p_icon, String p_desc, Integer p_mnemonic)
   {
      super(p_text, p_icon);
      m_parent = p_parent;
      putValue(SHORT_DESCRIPTION, p_desc);
      putValue(MNEMONIC_KEY, p_mnemonic);
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      File currentFile = m_parent.getApp().getCurrentFile();

      // If this is new and there is no current file, open file chooser
      if (currentFile == null)
      {
         JFileChooser fc = new JFileChooser(m_parent.getApp().getCurrentFilePath());
         int returnVal = fc.showDialog(m_parent, "Save");

         if (returnVal == JFileChooser.APPROVE_OPTION)
         {
            currentFile = fc.getSelectedFile();
            m_parent.getApp().setCurrentFile(currentFile);
            m_parent.getApp().setCurrentFilePath(currentFile.getParentFile());
            System.out.println("File chosen: " + currentFile.getAbsolutePath());
         }
         else
         {
            System.out.println("File selection cancelled");
         }
      }

      // Now that we have a file, save to disk
      // We may not, since tey may have cancelled selection
      if (currentFile != null)
      {
         try
         {
            System.out.println("Saving file...");
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(currentFile)));
            oos.writeObject(m_parent.getApp().getDataManager());
            oos.flush();
            oos.close();
            System.out.println("File saved");
         }
         catch (IOException e1)
         {
            e1.printStackTrace();
         }
      }

   }
}
