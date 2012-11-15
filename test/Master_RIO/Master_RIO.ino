#include <Wire.h>

byte count = 0;

void setup()
{
   pinMode(8, OUTPUT);
   pinMode(9, OUTPUT);
   pinMode(10, OUTPUT);
   Wire.begin();  // Master
}

void loop()
{
   digitalWrite(8, HIGH);
   delay(200);
   digitalWrite(8, LOW);
   // Send number to slave 0x01
   Wire.beginTransmission(0x37);
   digitalWrite(9, HIGH);
   delay(200);
   digitalWrite(9, LOW);
   Wire.write(count++);
   digitalWrite(10, HIGH);
   delay(200);
   digitalWrite(10, LOW);
   Wire.endTransmission(true);
   digitalWrite(8, HIGH);
   digitalWrite(9, HIGH);
   delay(200);
   digitalWrite(8, LOW);
   digitalWrite(9, LOW);
   if (count == 255)
   {
      count = 0;
   }
   digitalWrite(8, HIGH);
   digitalWrite(10, HIGH);
   delay(200);
   digitalWrite(8, LOW);
   digitalWrite(10, LOW);
   delay(1000);
}



