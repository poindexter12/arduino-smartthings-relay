int ledPin = 13;
int stateLED;           // state to track last set value of LED

void setup() {

  // setup debug serial port
  Serial.begin(9600);

  // setup hardware pins
  pinMode(ledPin, OUTPUT);     // define PIN_LED as an output
  digitalWrite(ledPin, LOW);   // set value to LOW (off) to match stateLED=0
}

void split(String message) {
  char buf[100];
  strncpy(buf, message.c_str(), sizeof(buf));

  int size = 10;
  int i = 0;
  char *p = strtok (buf, "|");
  char *array[size];

  while (p != NULL)
  {
    array[i++] = p;
    p = strtok (NULL, "|");
  }

  for (i = 0; i < size; ++i) {
    if (array[i] == NULL) {
      break;
    }
    printDebug("*** token ***");
    printDebug(array[i]);
  }
}

void printDebug(String text) {
  Serial.println(text);
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

  split(serialText);
}

