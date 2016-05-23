metadata {
	definition (name: "8 Channel Relay", namespace: "poindexter12", author: "Joe Seymour") {
		capability "Switch"
		capability "Actuator"
		capability "Sensor"
		attribute "zone1", "string"
		attribute "zone2", "string"
        command "zone1on"
        command "zone1off"
        command "zone2on"
        command "zone2off"
	}

	// simulator metadata
	simulator {
		status "on":  "command: 2003, payload: FF"
		status "off": "command: 2003, payload: 00"

		reply "2001FF,delay 100,2502": "command: 2503, payload: FF"
		reply "200100,delay 100,2502": "command: 2503, payload: 00"
	}

	tiles(scale: 2) {
		multiAttributeTile(name:"switch", type: "lighting", width: 6, height: 4, canChangeIcon: true){
			tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
				attributeState "on", label:'${name}', action:"switch.off", icon:"st.switches.switch.on", backgroundColor:"#79b821"
				attributeState "off", label:'${name}', action:"switch.on", icon:"st.switches.switch.off", backgroundColor:"#ffffff"
			}
		}
    standardTile("zone1", "device.zone1", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
      state "on", label:'${name}', action:"zone1off", icon:"st.switches.switch.on", backgroundColor:"#79b821"
      state "off", label:'${name}', action:"zone1on", icon:"st.switches.switch.off", backgroundColor:"#ffffff"
		}
    standardTile("zone2", "device.zone2", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
      state "on", label:'${name}', action:"zone2off", icon:"st.switches.switch.on", backgroundColor:"#79b821"
      state "off", label:'${name}', action:"zone2off", icon:"st.switches.switch.off", backgroundColor:"#ffffff"
		}
		main "switch"
		details(["switch", "zone1", "zone2"])
	}
}

// Parse incoming device messages to generate events
def parse(String description) {
  // TBD, if anything
}

def off() {
	def textCommand = "1,0|2,0"
	log.debug "sending command ${textCommand}"
	zigbee.smartShield(text: textCommand)
	sendEvent(name: "switch", value: "off")
}

def on() {
	def textCommand = "1,1|2,1"
	log.debug "sending command ${textCommand}"
	zigbee.smartShield(text: textCommand)
	sendEvent(name: "switch", value: "on")
}

def zone1on(){
  relayCommand(1, 1)
}

def zone1off(){
  relayCommand(1, 0)
}

def zone2on(){
  relayCommand(2, 1)
}

def zone2off(){
  relayCommand(2, 0)
}

def relayCommand(relayNumber, attribute){
	def textCommand = "${relayNumber},${attribute}"
	log.debug "sending command ${textCommand}"
	zigbee.smartShield(text: "${textCommand}")
	sendEvent(name: "zone${relayNumber}", value: attribute == 1 ? "on" : "off")
}
