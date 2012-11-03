package org.wildstang.criointerface.comms;

import gnu.io.*;
import java.io.*;
import java.util.*;

/**
 * User: Steve
 * Date: 10/30/12
 */
public class cRIOComms implements SerialPortEventListener
{
   private String m_commPort;

   /** Buffered input stream from the port */
   private InputStream input;
   /** The output stream to the port */
   private OutputStream output;
   /** Milliseconds to block while waiting for port open */
   private static final int TIME_OUT = 2000;
   /** Default bits per second for COM port. */
   private static final int DATA_RATE = 9600;

   SerialPort serialPort;

   private static DataManager manager = DataManager.getInstance();

   public cRIOComms()
   {

   }

   public ArrayList<String> listCommPorts()
   {
      ArrayList ports = new ArrayList<String>();

      Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

      // iterate through, looking for the port
      while (portEnum.hasMoreElements())
      {
         CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
         ports.add(currPortId.getName());
      }

      return ports;
   }

   public synchronized void serialEvent(SerialPortEvent p_event)
   {
      try
      {
         Thread.sleep(5);
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      }
      if (p_event.getEventType() == SerialPortEvent.DATA_AVAILABLE)
      {
         try
         {
            // Handle incoming data
            // if (data is available)
            int available = input.available();
            System.out.println(available);
            byte message[] = new byte[available];
            int portNum;

            // Read the data
            input.read(message, 0, available);

            // read 1st byte
            int type = message[0];
            portNum = message[1];
            switch (type)
            {
               case CommsConstants.PWM:
                  int value = message[2];
                  manager.updatePWM(portNum, value);
                  break;
               case CommsConstants.RELAY:
                  int valA = message[2];
                  int valB = message[3];
                  manager.updateRelay(portNum, valA, valB);
                  break;
               case CommsConstants.PWM_POWER:
                  int hasPower = message[2];
                  // TODO: update data
                  break;
               default:
                  break;
            }

         } catch (Exception e) {
            System.err.println(e.toString());
         }
      }
      // Ignore all the other eventTypes, but you should consider the other ones.
   }



   /**
    * This should be called when you stop using the port.
    * This will prevent port locking on platforms like Linux.
    */
   public synchronized void close() {
      if (serialPort != null) {
         serialPort.removeEventListener();
         serialPort.close();
      }
   }

   public void init() {
      CommPortIdentifier portId = null;
      Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

      // iterate through, looking for the port
      while (portEnum.hasMoreElements()) {
         CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
         if (currPortId.getName().equals(m_commPort)) {
            portId = currPortId;
            System.out.println("Found COM port: " + m_commPort);
            break;
         }
      }

      if (portId == null) {
         System.out.println("Could not find COM port.");
         return;
      }

      try {
         // open serial port, and use class name for the appName.
         serialPort = (SerialPort) portId.open(this.getClass().getName(),
            TIME_OUT);

         // set port parameters
         serialPort.setSerialPortParams(DATA_RATE,
            SerialPort.DATABITS_8,
            SerialPort.STOPBITS_1,
            SerialPort.PARITY_NONE);

         // open the streams
         input = serialPort.getInputStream();
         output = serialPort.getOutputStream();

         // add event listeners
         serialPort.addEventListener(this);
         serialPort.notifyOnDataAvailable(true);
      } catch (Exception e) {
         System.err.println(e.toString());
      }
   }


   public String getCommPort()
   {
      return m_commPort;
   }

   public void setCommPort(String p_commPort)
   {
      m_commPort = p_commPort;
   }
}
