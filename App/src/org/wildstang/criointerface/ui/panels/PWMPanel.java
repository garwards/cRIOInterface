package org.wildstang.criointerface.ui.panels;

import org.wildstang.criointerface.data.PWMPort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: Steve
 * Date: 11/2/12
 */
public class PWMPanel extends PortPanel
{
   private PWMPort m_port;

   JLabel m_portLabel;
   JCheckBox m_servo;
   JCheckBox m_flipped;
   JLabel m_output;
   JLabel m_label;

   public PWMPanel(PWMPort p_port)
   {
      m_port = p_port;
   }


   public JPanel createConfigPanel()
   {
      JPanel portPanel = new JPanel();

      portPanel.setLayout(new FlowLayout());

      // Port number
      m_portLabel = new JLabel(String.valueOf(m_port.getPort() + 1));
      m_portLabel.setBorder(BorderFactory.createLineBorder(Color.black));
      portPanel.add(m_portLabel);

      // Is this a m_servo?
      m_servo = new JCheckBox("Servo: ");
      m_servo.setSelected(m_port.isServo());
      m_servo.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            JCheckBox cbox = (JCheckBox)e.getSource();
            m_port.setServo(cbox.isSelected());
            System.out.println("PWM port " + m_port.getPort() + " is selected: " + m_port.isServo());
         }
      });
      portPanel.add(m_servo);

      // Is the output flipped?
      m_flipped = new JCheckBox("Flipped: ");
      m_flipped.setSelected(m_port.isFlipped());
      m_flipped.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            JCheckBox cbox = (JCheckBox)e.getSource();
            m_port.setFlipped(cbox.isSelected());
            System.out.println("PWM port " + m_port.getPort() + " is flipped: " + m_port.isFlipped());
         }
      });
      portPanel.add(m_flipped);

      // Label
      m_label = new JLabel(m_port.getLabel());
      portPanel.add(m_label);
      portPanel.setBorder(BorderFactory.createLineBorder(Color.black));
      portPanel.setSize(100, 30);
      return portPanel;
   }

   public JPanel createTableCellPanel()
   {
      JPanel portPanel = new JPanel();

      portPanel.setLayout(new FlowLayout());

      // Port number
      m_portLabel = new JLabel(String.valueOf(m_port.getPort() + 1));
      m_portLabel.setBorder(BorderFactory.createLineBorder(Color.black));
      portPanel.add(m_portLabel);

      // Is this a m_servo?
      m_servo = new JCheckBox("Servo: ");
      m_servo.setSelected(m_port.isServo());
      portPanel.add(m_servo);
      m_servo.setEnabled(false);

      // Is this a m_servo?
      m_flipped = new JCheckBox("Flipped: ");
      m_flipped.setSelected(m_port.isFlipped());
      portPanel.add(m_flipped);
      m_flipped.setEnabled(false);

      // Output value
      m_output = new JLabel();
      m_output.setText(String.valueOf(m_port.get8BitValue()));
      portPanel.add(m_output);

      // Label
      m_label = new JLabel(m_port.getLabel());
      portPanel.add(m_label);

      portPanel.setSize(100, 100);
      return portPanel;
   }

   public PWMPort getPort()
   {
      return m_port;
   }

   public JLabel getPortLabel()
   {
      return m_portLabel;
   }

   public JCheckBox getServo()
   {
      return m_servo;
   }

   public JCheckBox getFlipped()
   {
      return m_flipped;
   }

   public JLabel getOutput()
   {
      return m_output;
   }

   public JLabel getLabel()
   {
      return m_label;
   }
}
