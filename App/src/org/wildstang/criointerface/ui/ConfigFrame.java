package org.wildstang.criointerface.ui;

import org.wildstang.criointerface.data.DigitalModule;
import org.wildstang.criointerface.data.PWMPort;
import org.wildstang.criointerface.ui.actions.ConfigCancelAction;
import org.wildstang.criointerface.ui.actions.ConfigOKAction;
import org.wildstang.criointerface.ui.panels.PWMPanel;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * User: Steve
 * Date: 11/3/12
 */
public class ConfigFrame extends JFrame
{
   private DigitalModule m_digitalModule;

   // This is a temp working copy - make changes to this one.  After config, swap with current module
   private DigitalModule m_copyDigitalModule;

   private PWMPanel[] m_pwmPanels;

   private AppFrame m_appFrame;

   public ConfigFrame(AppFrame p_appFrame, DigitalModule p_module)
   {
      m_appFrame = p_appFrame;
      m_digitalModule = p_module;
      m_copyDigitalModule = copyDigitalModule();

      // TODO Make constant
      m_pwmPanels = new PWMPanel[10];
   }

   public void init()
   {
      Container container = getRootPane();
      container.setLayout(new BorderLayout());

      JTabbedPane tabs = new JTabbedPane();

      JPanel pwmTabContent = createPWMTab();
      JPanel pwmTab = new JPanel(new BorderLayout());
      pwmTab.add(new JLabel("PWM Ports"), BorderLayout.NORTH);
      pwmTab.add(pwmTabContent, BorderLayout.CENTER);
      tabs.addTab("PWMPort", pwmTabContent);

      JPanel relayTab = new JPanel(); //createRealayTab();
      tabs.addTab("Relay", relayTab);

      // TODO
//      tabs.addTab(digitalIOTab);
//      tabs.addTab(pneumaticsTab);


      JPanel buttonPanel = new JPanel();
      buttonPanel.add(new JButton(new ConfigOKAction(this)));
      buttonPanel.add(new JButton(new ConfigCancelAction(this)));



      container.add(tabs, BorderLayout.CENTER);
      container.add(buttonPanel, BorderLayout.SOUTH);

      setSize(800, 800);
   }


   private JPanel createPWMTab()
   {
      JPanel panel = new JPanel(new GridLayout(10, 1));
      PWMPort[] pwms = m_copyDigitalModule.getPwm();
      JPanel portPanel = null;
      PWMPanel pwmPanel;

      for (int i = 0; i < pwms.length; i++)
      {
         // Create panel
         pwmPanel = new PWMPanel(pwms[i]);
         portPanel = pwmPanel.createConfigPanel();
         m_pwmPanels[i] = pwmPanel;

         // Add port panel
         panel.add(portPanel);
      }

      return panel;
   }


   private DigitalModule copyDigitalModule()
   {
      DigitalModule copy = null;
      // Clone module
      try
      {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         ObjectOutputStream oos = new ObjectOutputStream(baos);
         oos.writeObject(m_digitalModule);

         ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
         copy = (DigitalModule)ois.readObject();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      catch (ClassNotFoundException e)
      {
         e.printStackTrace();
      }

      return copy;
   }

   public void updateAppFrameTable()
   {
      m_appFrame.createModuleInterface();
   }

   public PWMPanel[] getPwmPanels()
   {
      return m_pwmPanels;
   }

   public DigitalModule getCopyDigitalModule()
   {
      return m_copyDigitalModule;
   }

   public DigitalModule getDigitalModule()
   {
      return m_digitalModule;
   }

   public AppFrame getAppFrame()
   {
      return m_appFrame;
   }
}
