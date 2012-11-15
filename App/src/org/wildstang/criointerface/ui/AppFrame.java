package org.wildstang.criointerface.ui;

import org.wildstang.criointerface.comms.DataManager;
import org.wildstang.criointerface.data.PWMPort;
import org.wildstang.criointerface.data.RelayPort;
import org.wildstang.criointerface.ui.actions.NewAction;
import org.wildstang.criointerface.ui.actions.OpenAction;
import org.wildstang.criointerface.ui.actions.SaveAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * User: Steve
 * Date: 11/2/12
 */
public class AppFrame extends JFrame
{
   private MainApp m_app;

   public AppFrame(MainApp p_app, String p_title)
   {
      super(p_title);
      m_app = p_app;
      addWindowListener(new WindowAdapter()
      {
         @Override
         public void windowClosing(WindowEvent e)
         {
            m_app.exit();
         }
      });
   }

   public void init()
   {
      setSize(600, 600);

      // Set the menu bar
      JMenuBar menuBar = new JMenuBar();
      JMenu fileMenu = new JMenu("File");
      JMenuItem newItem = new JMenuItem(new NewAction(this, "New...", null, "Create a new configuration", new Integer(KeyEvent.VK_N)));
      newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
      fileMenu.add(newItem);

      // Open
      JMenuItem openItem = new JMenuItem(new OpenAction(this, "Open...", null, "Open existing configuration", new Integer(KeyEvent.VK_O)));
      openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
      fileMenu.add(openItem);

      // Save
      JMenuItem saveItem = new JMenuItem(new SaveAction(this, "Save", null, "Save current configuration", new Integer(KeyEvent.VK_S)));
      saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
      fileMenu.add(saveItem);

      fileMenu.setMnemonic(KeyEvent.VK_F);
      menuBar.add(fileMenu);

      setJMenuBar(menuBar);

      createModuleInterface();
   }

   public void createModuleInterface()
   {
      JPanel contentPane = new JPanel();
      contentPane.setLayout(new BorderLayout());

      JPanel mainPanel = new JPanel();

      if (m_app.getDataManager().getDigitalModule() != null)
      {
         System.out.println("Digital module is NOT null");
         JLabel title = new JLabel("cRIO Interface");
         contentPane.add(title, BorderLayout.NORTH);

         JTable pwmTable = new JTable(new PWMPortTableModel(m_app.getDataManager().getPWMs()));
         m_app.getDataManager().setPWMModel((PWMPortTableModel)pwmTable.getModel());
         pwmTable.setDefaultRenderer(PWMPort.class, new PWMPortCellRenderer());
         pwmTable.setRowHeight(100);

         JTable relayTable = new JTable(new RelayPortTableModel(m_app.getDataManager().getRelays()));
         m_app.getDataManager().setRelayModel((RelayPortTableModel)relayTable.getModel());
         relayTable.setDefaultRenderer(RelayPort.class, new RelayPortCellRenderer());
         relayTable.setRowHeight(100);

         mainPanel.add(pwmTable);
         mainPanel.add(relayTable);
      }
      else
      {
         System.out.println("Digital module is null");
         JPanel panel = new JPanel(new BorderLayout());
         panel.add(new JLabel("Create new project"));
         mainPanel.add(panel);
      }

      contentPane.add(mainPanel, BorderLayout.CENTER);
      setContentPane(contentPane);
      contentPane.revalidate();
   }

   public MainApp getApp()
   {
      return m_app;
   }
}
