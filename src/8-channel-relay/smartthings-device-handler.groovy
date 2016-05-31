metadata {
	definition (name: "8 Channel Relay", namespace: "poindexter12", author: "Joe Seymour") {
		capability "Actuator"
		capability "Relay Switch"

		attribute "status", "string"
		attribute "reset", "string"
		attribute "relay1", "string"
		attribute "relay2", "string"
		attribute "relay3", "string"
		attribute "relay4", "string"
		attribute "relay5", "string"
		attribute "relay6", "string"
		attribute "relay7", "string"
		attribute "relay8", "string"

		command "reseteverything"
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
		standardTile("reset", "device.reset", decoration: "flat") {
			state "done", label: "reset", action:"reseteverything", icon:"st.switches.switch.off", backgroundColor:"#ffffff", nextState:"resetting"
			state "resetting", label:'resetting...', action:"reseteverything", icon:"st.switches.switch.off", backgroundColor:"#a9a9a9"
		}
		standardTile("relay1", "device.relay1", inactiveLabel: true, decoration: "flat") {
			state "changing", label:'changing...', icon:"st.Outdoor.outdoor12", backgroundColor:"#a9a9a9"
			state "off", label:'relay 1 ${name}', action:"relay1on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff", nextState:"changing"
			state "on", label:'relay 1 ${name}', action:"relay1off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821", nextState:"changing"
		}
		standardTile("relay2", "device.relay2", inactiveLabel: false, decoration: "flat") {
			state "changing", label:'changing...', icon:"st.Outdoor.outdoor12", backgroundColor:"#a9a9a9"
			state "off", label:'relay 2 ${name}', action:"relay2on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff", nextState:"changing"
			state "on", label:'relay 2 ${name}', action:"relay2off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821", nextState:"changing"
		}
		standardTile("relay3", "device.relay3", inactiveLabel: false, decoration: "flat") {
			state "changing", label:'changing...', icon:"st.Outdoor.outdoor12", backgroundColor:"#a9a9a9"
			state "off", label:'relay 3 ${name}', action:"relay3on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff", nextState:"changing"
			state "on", label:'relay 3 ${name}', action:"relay3off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821", nextState:"changing"
		}
		standardTile("relay4", "device.relay4", inactiveLabel: false, decoration: "flat") {
			state "changing", label:'changing...', icon:"st.Outdoor.outdoor12", backgroundColor:"#a9a9a9"
			state "off", label:'relay 4 ${name}', action:"relay4on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff", nextState:"changing"
			state "on", label:'relay 4 ${name}', action:"relay4off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821", nextState:"changing"
		}
		standardTile("relay5", "device.relay5", inactiveLabel: false, decoration: "flat") {
			state "changing", label:'changing...', icon:"st.Outdoor.outdoor12", backgroundColor:"#a9a9a9"
			state "off", label:'relay 5 ${name}', action:"relay5on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff", nextState:"changing"
			state "on", label:'relay 5 ${name}', action:"relay5off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821", nextState:"changing"
		}
		standardTile("relay6", "device.relay6", inactiveLabel: false, decoration: "flat") {
			state "changing", label:'changing...', icon:"st.Outdoor.outdoor12", backgroundColor:"#a9a9a9"
			state "off", label:'relay 6 ${name}', action:"relay6on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff", nextState:"changing"
			state "on", label:'relay 6 ${name}', action:"relay6off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821", nextState:"changing"
		}
		standardTile("relay7", "device.relay7", inactiveLabel: false, decoration: "flat") {
			state "changing", label:'changing...', icon:"st.Outdoor.outdoor12", backgroundColor:"#a9a9a9"
			state "off", label:'relay 7 ${name}', action:"relay7on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff", nextState:"changing"
			state "on", label:'relay 7 ${name}', action:"relay7off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821", nextState:"changing"
		}
		standardTile("relay8", "device.relay8", inactiveLabel: false, decoration: "flat") {
			state "changing", label:'changing...', icon:"st.Outdoor.outdoor12", backgroundColor:"#a9a9a9"
			state "off", label:'relay 8 ${name}', action:"relay8on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff", nextState:"changing"
			state "on", label:'relay 8 ${name}', action:"relay8off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821", nextState:"changing"
		}
		main "status"
		details(["status", "alloff", "relay1", "relay2", "relay3", "relay4", "relay5", "relay6", "relay7", "relay8"])
	}
}

// Parse incoming device messages to generate events
def parse(String description) {
	def msg = zigbee.parse(description)?.text.trim()
	// bounce empty messages
	if (msg == null || msg == "") {
		return;
	}
	log.debug "Parse got '${msg}'"

	def parts = msg.split(" ")
	def name  = parts.length > 0 ? parts[0].trim() : null
	def value = parts.length > 1 ? parts[1].trim() : null

	name = value != "ping" ? name : null

	// create an event here instead of returning, issues with calling set status if not because of a know double cloud call issue
	def result = createEvent(name: name, value: value, isStateChange: true, displayed: true)
	log.debug result
	sendEvent(result);
	setStatus();

	return null;
}

def relay1on(){
	zigbee.smartShield(text: "1 on").format()
}

def relay1off(){
	zigbee.smartShield(text: "1 off").format()
}

def relay2on(){
	zigbee.smartShield(text: "2 on").format()
}

def relay2off(){
	zigbee.smartShield(text: "2 off").format()
}

def relay3on(){
	zigbee.smartShield(text: "3 on").format()
}

def relay3off(){
	zigbee.smartShield(text: "3 off").format()
}

def relay4on(){
	zigbee.smartShield(text: "4 on").format()
}

def relay4off(){
	zigbee.smartShield(text: "4 off").format()
}

def relay5on(){
	zigbee.smartShield(text: "5 on").format()
}

def relay5off(){
	zigbee.smartShield(text: "5 off").format()
}

def relay6on(){
	zigbee.smartShield(text: "6 on").format()
}

def relay6off(){
	zigbee.smartShield(text: "6 off").format()
}

def relay7on(){
	zigbee.smartShield(text: "7 on").format()
}

def relay7off(){
	zigbee.smartShield(text: "7 off").format()
}

def relay8on(){
	zigbee.smartShield(text: "8 on").format()
}

def relay8off(){
	zigbee.smartShield(text: "8 off").format()
}

def reseteverything(){
	// turn off all the relay states in the app before we reset
	sendEvent(name: "relay1", value: "off", displayed: true, isStateChange: true);
	sendEvent(name: "relay2", value: "off", displayed: true, isStateChange: true);
	sendEvent(name: "relay3", value: "off", displayed: true, isStateChange: true);
	sendEvent(name: "relay4", value: "off", displayed: true, isStateChange: true);
	sendEvent(name: "relay5", value: "off", displayed: true, isStateChange: true);
	sendEvent(name: "relay6", value: "off", displayed: true, isStateChange: true);
	sendEvent(name: "relay7", value: "off", displayed: true, isStateChange: true);
	sendEvent(name: "relay8", value: "off", displayed: true, isStateChange: true);
	zigbee.smartShield(text: "reset").format()
}

def setStatus(){
	sendEvent(name: "status", value: isActive() ? "active" : "inactive", displayed: true, isStateChange: true);
}

def isActive(){
	if (device.currentValue("relay1") == "on") return true;
	if (device.currentValue("relay2") == "on") return true;
	if (device.currentValue("relay3") == "on") return true;
	if (device.currentValue("relay4") == "on") return true;
	if (device.currentValue("relay5") == "on") return true;
	if (device.currentValue("relay6") == "on") return true;
	if (device.currentValue("relay7") == "on") return true;
	if (device.currentValue("relay8") == "on") return true;
	return false;
}
