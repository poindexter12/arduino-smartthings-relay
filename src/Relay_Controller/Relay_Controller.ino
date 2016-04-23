int ledPin = 13;
int stateLED;           // state to track last set value of LED
char *messageTokens[24]; // holds the maximum size of relays
boolean isDebug = true;

void setup() {

  // setup debug serial port
  Serial.begin(9600);

  // setup hardware pins
  pinMode(ledPin, OUTPUT);     // define PIN_LED as an output
  digitalWrite(ledPin, LOW);   // set value to LOW (off) to match stateLED=0
}

void splitMessage(String message) {
  // copy message to char buffer
  char buf[100];
  strncpy(buf, message.c_str(), sizeof(buf));

  // get all the tokens
  int i = 0;
  int tokenCount = 0;
  char *p = strtok (buf, "|");

  while (p != NULL)
  {
    messageTokens[i] = p;
    i++;
    tokenCount = i;
    p = strtok (NULL, "|");
  }

  // dump to serial for debug
  printDebug("*** begin tokens ***");
  for (i = 0; i < tokenCount; ++i) {
    if (messageTokens[i] == NULL) {
      break;
    }
    printDebug(messageTokens[i]);
  }
  printDebug("*** end tokens ***");
}

void printDebug(String text) {
  if (isDebug) {
    Serial.println(text);
  }
}

void loop() {
  processSerial();
  // smartthing.run();
}

void processSerial() {
  if (Serial.available() == 0) {
    return;
  }

  String serialText = Serial.readString();

  printDebug(serialText);

  splitMessage(serialText);
}

