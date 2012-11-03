package org.wildstang.criointerface.ui;

import org.wildstang.criointerface.comms.DataManager;
import org.wildstang.criointerface.data.PWMPort;
import org.wildstang.criointerface.data.RelayPort;
import org.wildstang.criointerface.ui.actions.NewAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

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
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   }

   public void init()
   {
      setSize(600, 600);
      Container contentPane = getContentPane();

      // Set the menu bar
      JMenuBar menuBar = new JMenuBar();
      JMenu fileMenu = new JMenu("File");
      fileMenu.add(new NewAction("New...", null, "Create a new configuration", new Integer(KeyEvent.VK_N)));
      fileMenu.setMnemonic(KeyEvent.VK_F);
      menuBar.add(fileMenu);

      setJMenuBar(menuBar);

      contentPane.setLayout(new BorderLayout());
      JLabel title = new JLabel("cRIO Interface");
      contentPane.add(title, BorderLayout.NORTH);

      JPanel mainPanel = new JPanel();
      contentPane.add(mainPanel, BorderLayout.CENTER);
      JTable pwmTable = new JTable(new PWMPortTableModel(DataManager.getInstance().getPWMs()));
      pwmTable.setDefaultRenderer(PWMPort.class, new PWMPortCellRenderer());
      pwmTable.setRowHeight(100);

      JTable relayTable = new JTable(new RelayPortTableModel(DataManager.getInstance().getRelays()));
      relayTable.setDefaultRenderer(RelayPort.class, new RelayPortCellRenderer());
      relayTable.setRowHeight(100);


      mainPanel.add(pwmTable);
      mainPanel.add(relayTable);
   }
}
