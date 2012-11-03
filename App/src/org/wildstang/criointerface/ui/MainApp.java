package org.wildstang.criointerface.ui;

import org.wildstang.criointerface.comms.DataManager;
import org.wildstang.criointerface.comms.cRIOComms;

/**
 * User: Steve
 * Date: 11/2/12
 */
public class MainApp
{
   private DataManager m_dataManager;
   private cRIOComms m_comms = new cRIOComms();

   public static void main(String[] args)
   {
      MainApp app = new MainApp();
   }

   public MainApp()
   {
      m_dataManager = DataManager.getInstance();


      AppFrame appFrame = new AppFrame(this, "cRIO Interface");
      appFrame.init();
      appFrame.setVisible(true);
   }
}
