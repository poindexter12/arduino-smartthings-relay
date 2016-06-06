metadata {
	definition (name: "4 Channel Relay", namespace: "poindexter12", author: "Joe Seymour") {
		capability "Actuator"
		capability "Relay Switch"

		attribute "status", "string"
		attribute "reset", "string"
		attribute "relay1", "string"
		attribute "relay2", "string"
		attribute "relay3", "string"
		attribute "relay4", "string"

		command "reseteverything"
		command "relay1on"
		command "relay1off"
		command "relay2on"
		command "relay2off"
		command "relay3on"
		command "relay3off"
		command "relay4on"
		command "relay4off"
		command "relayon"
		command "relayoff"
	}

	// simulator metadata
	simulator {
	}

	tiles(scale: 1) {
		standardTile("status", "device.status", width: 2, height: 1) {
			state "inactive", label:' ${name} ', icon:"st.shields.shields.arduino", backgroundColor:"#ffffff"
			state "active", label:' ${name} ', icon:"st.shields.shields.arduino", backgroundColor:"#79b821"
		}
		standardTile("reset", "device.reset", decoration: "flat") {
			state "done", label: "reset", action:"reseteverything", icon:"st.secondary.refresh", backgroundColor:"#ffffff", nextState:"resetting"
			state "resetting", label:'resetting...', action:"reseteverything", icon:"st.secondary.refresh", backgroundColor:"#a9a9a9"
		}
		standardTile("relay1", "device.relay1", inactiveLabel: true, decoration: "flat") {
			state "off", label:'relay 1 ${name}', action:"relay1on", icon:"st.switches.switch.off", backgroundColor:"#ffffff", nextState:"changing"
			state "changing", label:'changing...', icon:"st.Health & Wellness.health7", backgroundColor:"#a9a9a9"
			state "on", label:'relay 1 ${name}', action:"relay1off", icon:"st.switches.switch.on", backgroundColor:"#79b821", nextState:"changing"
		}
		standardTile("relay2", "device.relay2", inactiveLabel: false, decoration: "flat") {
			state "off", label:'relay 2 ${name}', action:"relay2on", icon:"st.switches.switch.off", backgroundColor:"#ffffff", nextState:"changing"
			state "changing", label:'changing...', icon:"st.Health & Wellness.health7", backgroundColor:"#a9a9a9"
			state "on", label:'relay 2 ${name}', action:"relay2off", icon:"st.switches.switch.on", backgroundColor:"#79b821", nextState:"changing"
		}
		standardTile("relay3", "device.relay3", inactiveLabel: false, decoration: "flat") {
			state "off", label:'relay 3 ${name}', action:"relay3on", icon:"st.switches.switch.off", backgroundColor:"#ffffff", nextState:"changing"
			state "changing", label:'changing...', icon:"st.Health & Wellness.health7", backgroundColor:"#a9a9a9"
			state "on", label:'relay 3 ${name}', action:"relay3off", icon:"st.switches.switch.on", backgroundColor:"#79b821", nextState:"changing"
		}
		standardTile("relay4", "device.relay4", inactiveLabel: false, decoration: "flat") {
			state "off", label:'relay 4 ${name}', action:"relay4on", icon:"st.switches.switch.off", backgroundColor:"#ffffff", nextState:"changing"
			state "changing", label:'changing...', icon:"st.Health & Wellness.health7", backgroundColor:"#a9a9a9"
			state "on", label:'relay 4 ${name}', action:"relay4off", icon:"st.switches.switch.on", backgroundColor:"#79b821", nextState:"changing"
		}
		main "status"
		details(["status", "relay1", "relay2", "relay3", "relay4", "reset"])
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

def relayon(relay){
	zigbee.smartShield(text: "${relay} on").format()
}

def relayoff(relay){
	zigbee.smartShield(text: "${relay} off").format()
}

def relay1on(){
	relayon("1")
}

def relay1off(){
	relayoff("1")
}

def relay2on(){
	relayon("2")
}

def relay2off(){
	relayoff("2")
}

def relay3on(){
	relayon("3")
}

def relay3off(){
	relayoff("3")
}

def relay4on(){
	relayon("4")
}

def relay4off(){
	relayoff("4")
}

def relayon(relay){
	zigbee.smartShield(text: "${relay} on").format()
}

def relayoff(relay){
	zigbee.smartShield(text: "${relay} off").format()
}

def reseteverything(){
	// turn off all the relay states in the app before we reset
	sendEvent(name: "relay1", value: "off", displayed: true, isStateChange: true);
	sendEvent(name: "relay2", value: "off", displayed: true, isStateChange: true);
	sendEvent(name: "relay3", value: "off", displayed: true, isStateChange: true);
	sendEvent(name: "relay4", value: "off", displayed: true, isStateChange: true);
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
	return false;
}
