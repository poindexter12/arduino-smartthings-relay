#include <SmartThings.h>
#include <string.h>

#define PIN_O_RESERVED               0  //reserved by ThingShield for Serial communications OR USB Serial Monitor
#define PIN_1_RESERVED               1  //reserved by ThingShield for Serial communications OR USB Serial Monitor
#define PIN_6_RESERVED               6  //reserved by ThingShield (possible future use?)

#define PIN_THING_TX      2
#define PIN_THING_RX      3

#define PIN_RELAY1        4
#define PIN_RELAY2        5
#define PIN_RELAY3        7
#define PIN_RELAY4        8
#define PIN_RELAY5        9
#define PIN_RELAY6        10
#define PIN_RELAY7        11
#define PIN_RELAY8        12

#define MAX_RELAYS        8

byte relayPins[MAX_RELAYS] = { PIN_RELAY1, PIN_RELAY2, PIN_RELAY3, PIN_RELAY4, PIN_RELAY5, PIN_RELAY6, PIN_RELAY7, PIN_RELAY8 };

SmartThingsCallout_t messageCallout;    // call out function forward decalaration
SmartThings smartthing(PIN_THING_RX, PIN_THING_TX, messageCallout);  // constructor

int relayOn = LOW;             // holds the on state
int relayOff = HIGH;           // holds the off state

void setup() {
  // setup debug serial port
  Serial.begin(9600);
  // set to blue to start
  smartthing.shieldSetLED(0, 0, 1);
  // loop through relays
  for (int i = 0; i < MAX_RELAYS; i++){
    // set all pins to output
    pinMode(relayPins[i], OUTPUT);
  }
  // turn all the relays off
  allOff();
}

void processMessage(String message) {
  //Ignore the periodic ' ' sent
  message.trim();
  if (message.length() == 0)
  {
    return;
  }
  // dump the message if we're debugging
  Serial.println(message);
  // look for all off first
  if (message == "alloff"){
    allOff();
  }
  // declare variables for message
  String relay;
  String state = "off";
  // find space
  int splitIndex = message.indexOf(' ');
  // get message parts
  relay = message.substring(0, splitIndex);
  state = message.substring(splitIndex + 1);
  // set the state
  setRelayState(relay, state);
}

void allOff(){
  smartthing.shieldSetLED(1, 0, 0);
  // loop through relays
  for (int i = 0; i < MAX_RELAYS; i++) {
    // close relay
    digitalWrite(relayPins[i], relayOff);
  }
  // set to blue to start
  smartthing.shieldSetLED(0, 0, 1);
  // signal the alloff is done to smartthings
  String message = "alloff done";
  smartthing.send(message);
}


void setRelayState(String relay, String state) {
  int relayNumber = relay.toInt();
  if (state == "on") {
    // set shield to green
    smartthing.shieldSetLED(0, 1, 0);
    // opening relay
    digitalWrite(relayPins[relayNumber - 1], relayOn);
  }
  else {
    // set shield to red
    smartthing.shieldSetLED(1, 0, 0);
    // closing relay
    digitalWrite(relayPins[relayNumber - 1], relayOff);
  }
  // back to blue
  smartthing.shieldSetLED(0, 0, 1);
  // build message to send
  String message = "relay" + relay + " " + state;
  Serial.println("Sending message: " + message);
  // send the message
  smartthing.send(message);
}

// arduino loop, stuff going on here
void loop() {
  processSerial();
  // do the smartthing...
  smartthing.run();
}

// messages from serial
void processSerial() {
  if (Serial.available() == 0) {
    return;
  }
  processMessage(Serial.readString());
}

//messages from SmartThings hub
void messageCallout(String message) {
  processMessage(message);
}
