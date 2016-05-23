#include <SmartThings.h>
#include <string.h>
#define PIN_THING_RX      3
#define PIN_THING_TX      2
#define MAX_ARDUINO_PTRS 24
#define PIN_OFFSET        3

SmartThingsCallout_t messageCallout;    // call out function forward decalaration
SmartThings smartthing(PIN_THING_RX, PIN_THING_TX, messageCallout);  // constructor

boolean isDebug = true;       // flag for debug messages

boolean isActiveHigh = false; //set to true if using "active high" relay, set to false if using "active low" relay
int relayOn = HIGH;           // holds the on state
int relayOff = LOW;           // holds the off state

void setup() {

  // setup debug serial port
  Serial.begin(9600);

  //set up relays to control irrigation valves
  if (!isActiveHigh) {
    relayOn = LOW;
    relayOff = HIGH;
  }

  smartthing.shieldSetLED(0, 0, 1); // set to blue to start
}

void processMessage(String message) {
  smartthing.shieldSetLED(0, 0, 1);

  printDebug(message);

  // copy message to char buffer (duplicate string)
  char buf[100];
  strncpy(buf, message.c_str(), sizeof(buf));

  // get all the tokens
  int i = 0;
  char *relayMessages[MAX_ARDUINO_PTRS];
  char *relayMessage = strtok (buf, "|");

  while (relayMessage != NULL)
  {
    relayMessages[i++] = relayMessage;
    relayMessage = strtok (NULL, "|");
  }
  relayMessages[i++] = '\0';  // set flag to signal end of info

  printDebug("*** begin tokens ***");
  for (i = 0; i < MAX_ARDUINO_PTRS; i++) {
    if (relayMessages[i] == '\0')
    {
      break;
    }
    printDebug(relayMessages[i]);
    processRelayMessage(relayMessages[i]);
  }
  printDebug("*** end tokens ***");
  smartthing.shieldSetLED(0, 0, 1);

}

void processRelayMessage(char* relayMessage) {
  char buf[100];
  strncpy(buf, relayMessage, MAX_ARDUINO_PTRS);

  char *stationNumberToken = strtok(buf, ",");
  char *stationStateToken = strtok(NULL, ",");

  setStationState(atoi(stationNumberToken), atoi(stationStateToken));
}

void setStationState(int station, int state) {
  if (state == 1) {
    smartthing.shieldSetLED(0, 1, 0);
    digitalWrite(station + PIN_OFFSET, relayOn);
  }
  else {
    smartthing.shieldSetLED(1, 0, 0);
    digitalWrite(station + PIN_OFFSET, relayOff);
  }
  delay(500); // give the relay a chance to kick on
}

void printDebug(String text) {
  if (isDebug) {
    Serial.println(text);
  }
}

void loop() {
  processSerial();
  smartthing.run();
}

void processSerial() {
  if (Serial.available() == 0) {
    return;
  }

  String serialText = Serial.readString();

  processMessage(serialText);
}

//process incoming messages from SmartThings hub
void messageCallout(String message) {
  printDebug("*** begin smarthings message ***");
  processMessage(message);
  printDebug("*** end smarthings message ***");
}