#include <Wire.h>
#include <Adafruit_MCP23017.h>

byte i = 45;
byte currentByte = 0;

Adafruit_MCP23017 mcp;

// ---------- PWM -------------
#define THROTTLE_SIGNAL_IN 0 // INTERRUPT 0 = DIGITAL PIN 2 - use the interrupt number in attachInterrupt
#define THROTTLE_SIGNAL_IN_PIN 2 // INTERRUPT 0 = DIGITAL PIN 2 - use the PIN number in digitalRead

#define NEUTRAL_THROTTLE 1500 // this is the duration in microseconds of neutral throttle on an electric RC Car

volatile int nThrottleIn = NEUTRAL_THROTTLE; // volatile, we set this in the Interrupt and read it in loop so it must be declared volatile
volatile unsigned long ulStartPeriod = 0; // set in the interrupt
volatile boolean bNewThrottleSignal = false; // set in the interrupt and read in the loop
// we could use nThrottleIn = 0 in loop instead of a separate variable, but using bNewThrottleSignal to indicate we have a new signal 
// is clearer for this first example
// ---------- PWM -------------

void setup()
{
   Wire.begin(0x37);
   Wire.onReceive(readFromI2CMaster);

   pinMode(13, OUTPUT);
   // Expander setup
   mcp.begin(1);      // use default address 0
   mcp.pinMode(0, INPUT);
   mcp.pullUp(0, LOW);  // turn on a 100K pullup internally
   pinMode(13, OUTPUT);  // use the p13 LED as debugging
   
   // ---------- PWM -------------
   attachInterrupt(THROTTLE_SIGNAL_IN,calcInput,CHANGE);
   // ---------- PWM -------------

   Serial.begin(9600);
}


void loop()
{
   if (bNewThrottleSignal)
   {
      int pwmVal = map(nThrottleIn, 0, 2500, 0, 255);
      sendPWMUpdate(3, pwmVal);
      // set this back to false when we have finished
      // with nThrottleIn, while true, calcInput will not update
      // nThrottleIn
      bNewThrottleSignal = false;
   }
   sendRelayUpdate(1, mcp.digitalRead(0), 0);
   sendI2CUpdate(currentByte);
   delay(50);
}


void calcInput()
{
  // if the pin is high, its the start of an interrupt
  if(digitalRead(THROTTLE_SIGNAL_IN_PIN) == HIGH)
  { 
    // get the time using micros - when our code gets really busy this will become inaccurate, but for the current application its 
    // easy to understand and works very well
    ulStartPeriod = micros();
  }
  else
  {
    // if the pin is low, its the falling edge of the pulse so now we can calculate the pulse duration by subtracting the 
    // start time ulStartPeriod from the current time returned by micros()
    if(ulStartPeriod && (bNewThrottleSignal == false))
    {
      nThrottleIn = (int)(micros() - ulStartPeriod);
      ulStartPeriod = 0;

      // tell loop we have a new signal on the throttle channel
      // we will not update nThrottleIn until loop sets
      // bNewThrottleSignal back to false
      bNewThrottleSignal = true;
    }
  }
}

void readFromI2CMaster(int numBytes)
{
   int byteCount = Wire.available();
   while (byteCount > 0)
   {
      currentByte = Wire.read();
   }
   digitalWrite(13, 1);
   delay(100);
   digitalWrite(13, 0);
}

void sendPWMUpdate(byte port, byte value)
{
   byte data[] = {1, port, value};
   Serial.write(data, 3);
   Serial.flush();
}

void sendRelayUpdate(byte port, byte valA, byte valB)
{
   byte data[] = {2, port, valA, valB};
   digitalWrite(13, valA);
   Serial.write(data, 4);
   Serial.flush();
}

void sendI2CUpdate(byte currentByte)
{
   byte data[] = {4, currentByte};
   Serial.write(data, 2);
   Serial.flush();
}


