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
}

void processMessage(String message) {
  // copy message to char buffer
  char buf[100];
  strncpy(buf, message.c_str(), sizeof(buf));

  // get all the tokens
  int i = 0;
  char *relayMessage = strtok (buf, "|");

  printDebug("*** begin tokens ***");
  while (relayMessage != NULL)
  {
    processRelayMessage(relayMessage);
    relayMessage = strtok (NULL, "|");
  }
  printDebug("*** end tokens ***");
}

void processRelayMessage(char* relayMessage){
    printDebug(relayMessage);

    char *stationNumberToken = strtok(relayMessage, ",");
    char *stationStateToken = strtok(NULL, ",");

    printDebug(stationNumberToken);
    printDebug(stationStateToken);
}

void SetStationState(int station, int state){
  if (state == 1){
    digitalWrite(station, relayOn);
  }
  digitalWrite(station, relayOff);
}

void printDebug(String text) {
  if (isDebug) {
    Serial.println(text);
  }
}

void loop() {
  processSerial();
}

void processSerial() {
  if (Serial.available() == 0) {
    return;
  }

  String serialText = Serial.readString();

  printDebug(serialText);

  processMessage(serialText);
}

