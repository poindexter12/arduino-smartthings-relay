metadata {
	definition (name: "8 Channel Relay", namespace: "poindexter12", author: "Joe Seymour") {
		capability "Actuator"
		capability "Switch"

		attribute "relay1", "string"
		attribute "relay2", "string"
		attribute "relay3", "string"
		attribute "relay4", "string"
		attribute "relay5", "string"
		attribute "relay6", "string"
		attribute "relay7", "string"
		attribute "relay8", "string"

		command "alloff"
		command "relay1on"
		command "relay1off"
		command "relay2on"
		command "relay2off"
		command "relay3on"
		command "relay3off"
		command "relay4on"
		command "relay4off"
		command "relay5on"
		command "relay5off"
		command "relay6on"
		command "relay6off"
		command "relay7on"
		command "relay7off"
		command "relay8on"
		command "relay8off"
	}

	// simulator metadata
	simulator {
	}

	tiles(scale: 1) {
		standardTile("status", "device.status", width: 2, height: 1) {
			state "inactive", label:' ${name} ', icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff"
			state "active", label:' ${name} ', icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821"
		}
		standardTile("alloff", "device.alloff", decoration: "flat") {
			state "none", label: "all off", action:"alloff", icon:"st.switches.switch.off", backgroundColor:"#ffffff"
		}
		standardTile("relay1", "device.relay1", inactiveLabel: true, decoration: "flat") {
			state "off", label:'relay 1 ${name}', action:"relay1on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff"
			state "on", label:'relay 1 ${name}', action:"relay1off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821"
		}
		standardTile("relay2", "device.relay2", inactiveLabel: false, decoration: "flat") {
			state "off", label:'relay 2 ${name}', action:"relay2on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff"
			state "on", label:'relay 2 ${name}', action:"relay2off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821"
		}
		standardTile("relay3", "device.relay3", inactiveLabel: false, decoration: "flat") {
			state "off", label:'relay 3 ${name}', action:"relay3on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff"
			state "on", label:'relay 3 ${name}', action:"relay3off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821"
		}
		standardTile("relay4", "device.relay4", inactiveLabel: false, decoration: "flat") {
			state "off", label:'relay 4 ${name}', action:"relay4on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff"
			state "on", label:'relay 4 ${name}', action:"relay4off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821"
		}
		standardTile("relay5", "device.relay5", inactiveLabel: false, decoration: "flat") {
			state "off", label:'relay 5 ${name}', action:"relay5on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff"
			state "on", label:'relay 5 ${name}', action:"relay5off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821"
		}
		standardTile("relay6", "device.relay6", inactiveLabel: false, decoration: "flat") {
			state "off", label:'relay 6 ${name}', action:"relay6on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff"
			state "on", label:'relay 6 ${name}', action:"relay6off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821"
		}
		standardTile("relay7", "device.relay7", inactiveLabel: false, decoration: "flat") {
			state "off", label:'relay 7 ${name}', action:"relay7on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff"
			state "on", label:'relay 7 ${name}', action:"relay7off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821"
		}
		standardTile("relay8", "device.relay8", inactiveLabel: false, decoration: "flat") {
			state "off", label:'relay 8 ${name}', action:"relay8on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff"
			state "on", label:'relay 8 ${name}', action:"relay8off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821"
		}
		main "status"
		details(["status", "alloff", "relay1", "relay2", "relay3", "relay4", "relay5", "relay6", "relay7", "relay8"])
	}
}

// Parse incoming device messages to generate events
def parse(String description) {
	def msg = zigbee.parse(description)?.text
	log.debug "Parse got '${msg}'"

	def parts = msg.split(" ")
	def name  = parts.length > 0 ? parts[0].trim() : null
	def value = parts.length > 1 ? parts[1].trim() : null

	name = value != "ping" ? name : null

	def result = createEvent(name: name, value: value, displayed: true, isStateChange: true)
	log.debug result

	return null;
}

def relay1on(){
	sendEvent(name: "relay1", value: "on", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "1 on").format()
}

def relay1off(){
	sendEvent(name: "relay1", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "1 off").format()
}

def relay2on(){
	sendEvent(name: "relay2", value: "on", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "2 on").format()
}

def relay2off(){
	sendEvent(name: "relay2", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "2 off").format()
}

def relay3on(){
	sendEvent(name: "relay3", value: "on", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "3 on").format()
}

def relay3off(){
	sendEvent(name: "relay3", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "3 off").format()
}

def relay4on(){
	sendEvent(name: "relay4", value: "on", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "4 on").format()
}

def relay4off(){
	sendEvent(name: "relay4", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "4 off").format()
}

def relay5on(){
	sendEvent(name: "relay5", value: "on", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "5 on").format()
}

def relay5off(){
	sendEvent(name: "relay5", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "5 off").format()
}

def relay6on(){
	sendEvent(name: "relay6", value: "on", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "6 on").format()
}

def relay6off(){
	sendEvent(name: "relay6", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "6 off").format()
}

def relay7on(){
	sendEvent(name: "relay7", value: "on", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "7 on").format()
}

def relay7off(){
	sendEvent(name: "relay7", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "7 off").format()
}

def relay8on(){
	sendEvent(name: "relay8", value: "on", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "8 on").format()
}

def relay8off(){
	sendEvent(name: "relay8", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "8 off").format()
}

def alloff(){
	sendEvent(name: "relay1", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	sendEvent(name: "relay2", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	sendEvent(name: "relay3", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	sendEvent(name: "relay4", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	sendEvent(name: "relay5", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	sendEvent(name: "relay6", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	sendEvent(name: "relay7", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	sendEvent(name: "relay8", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "alloff").format()
}

def setActivityState(){
	def relay1state = device.currentValue("relay1");
	if (relay1state == "on"){
		sendEvent(name: "status", value: "active", displayed: true, isStateChange: true, isPhysical: false);
		return;
	}
	def relay2state = device.currentValue("relay2");
	if (relay2state == "on"){
		sendEvent(name: "status", value: "active", displayed: true, isStateChange: true, isPhysical: false);
		return;
	}
	def relay3state = device.currentValue("relay3");
	if (relay3state == "on"){
		sendEvent(name: "status", value: "active", displayed: true, isStateChange: true, isPhysical: false);
		return;
	}
	def relay4state = device.currentValue("relay4");
	if (relay4state == "on"){
		sendEvent(name: "status", value: "active", displayed: true, isStateChange: true, isPhysical: false);
		return;
	}
	def relay5state = device.currentValue("relay5");
	if (relay5state == "on"){
		sendEvent(name: "status", value: "active", displayed: true, isStateChange: true, isPhysical: false);
		return;
	}
	def relay6state = device.currentValue("relay6");
	if (relay6state == "on"){
		sendEvent(name: "status", value: "active", displayed: true, isStateChange: true, isPhysical: false);
		return;
	}
	def relay7state = device.currentValue("relay7");
	if (relay7state == "on"){
		sendEvent(name: "status", value: "active", displayed: true, isStateChange: true, isPhysical: false);
		return;
	}
	def relay8state = device.currentValue("relay8");
	if (relay8state == "on"){
		sendEvent(name: "status", value: "active", displayed: true, isStateChange: true, isPhysical: false);
		return;
	}
	sendEvent(name: "status", value: "inactive", displayed: true, isStateChange: true, isPhysical: false);
}
