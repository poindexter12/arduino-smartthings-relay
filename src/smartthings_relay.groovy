metadata {
	definition (name: "Relay Switches", namespace: "smartthings", author: "poindexter12") {
		capability "Switch"
		capability "Actuator"
		capability "Sensor"
	}

	// simulator metadata
	simulator {
		// status messages
		status "on": "on/off: 1"
		status "off": "on/off: 0"

		// reply messages
		reply "zcl on-off on": "on/off: 1"
		reply "zcl on-off off": "on/off: 0"
	}

	tiles(scale: 2) {
		multiAttributeTile(name:"switch", type: "lighting", width: 6, height: 4, canChangeIcon: true){
			tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
				attributeState "on", label:'${name}', action:"switch.off", icon:"st.switches.switch.on", backgroundColor:"#79b821", nextState:"turningOff"
				attributeState "off", label:'${name}', action:"switch.on", icon:"st.switches.switch.off", backgroundColor:"#ffffff", nextState:"turningOn"
				attributeState "turningOn", label:'${name}', action:"switch.off", icon:"st.switches.switch.on", backgroundColor:"#79b821", nextState:"turningOff"
				attributeState "turningOff", label:'${name}', action:"switch.on", icon:"st.switches.switch.off", backgroundColor:"#ffffff", nextState:"turningOn"
			}
		}
		main "switch"
		details(["switch"])
	}
}

// Parse incoming device messages to generate events
def parse(String description) {
  // TBD, if anything
}

def off() {
	relayCommand("1", "0")
}

def on() {
	relayCommand("1", "1")
}

relayCommand(relayNumber, attribute){
  zigbee.smartShield(text: "${relayNumber},${attribute}").format()
}
