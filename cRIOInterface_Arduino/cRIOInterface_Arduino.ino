

byte i = 45;

void setup()
{
   Serial.begin(9600);
}


void loop()
{
   sendPWMUpdate(3, 107);
   //sendMessage(message);
   delay(500);
}



void sendPWMUpdate(byte port, byte value)
{
   byte data[] = {1, port, value};
   Serial.write(data, 3);
   Serial.flush();
}




