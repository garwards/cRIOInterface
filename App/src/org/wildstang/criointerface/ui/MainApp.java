package org.wildstang.criointerface.ui;

import org.wildstang.criointerface.comms.DataManager;
import org.wildstang.criointerface.comms.cRIOComms;

import java.io.File;

/**
 * User: Steve
 * Date: 11/2/12
 */
public class MainApp
{
   private DataManager m_dataManager;
   private cRIOComms m_comms;
   private File m_currentFile = null;
   private File currentFilePath = null;
   AppFrame m_appFrame = null;

   public static void main(String[] args)
   {
      MainApp app = new MainApp();
   }

   public MainApp()
   {
      m_dataManager = new DataManager();

      m_appFrame = new AppFrame(this, "cRIO Interface");
      m_appFrame.init();
      m_appFrame.setLocationRelativeTo(null);
      m_appFrame.setVisible(true);
   }

   public void startComms()
   {
      m_comms = new cRIOComms(this);
      m_comms.setCommPort("COM3");
      m_comms.init();
   }

   public void exit()
   {
      System.out.println("MainApp.exit()");
      System.out.println("Closing comms...");
      if (getComms() != null)
      {
         getComms().close();
      }
      System.out.println("Comms closed");
      System.out.println("Hiding frame");
      m_appFrame.setVisible(false);
      System.out.println("Frame hidden");
      System.out.println("Disposing frame");
      m_appFrame.dispose();
      System.out.println("Frame disposed");
      System.out.println("System.exit(0)");
      System.exit(0);
   }

   public DataManager getDataManager()
   {
      return m_dataManager;
   }

   public void setDataManager(DataManager p_dataManager)
   {
      m_dataManager = p_dataManager;
   }

   public cRIOComms getComms()
   {
      return m_comms;
   }

   public File getCurrentFile()
   {
      return m_currentFile;
   }

   public void setCurrentFile(File p_currentFile)
   {
      m_currentFile = p_currentFile;
   }

   public File getCurrentFilePath()
   {
      return currentFilePath;
   }

   public void setCurrentFilePath(File p_currentFilePath)
   {
      currentFilePath = p_currentFilePath;
   }
}
