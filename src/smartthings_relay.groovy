metadata {
	definition (name: "8 Channel Relay", namespace: "poindexter12", author: "Joe Seymour") {
		capability "Actuator"
		attribute "zone1", "string"
		attribute "zone2", "string"
        command "alloff"
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

	tiles(scale: 1) {
		standardTile("status", "status", width: 2, height: 1) {
			state "off", label:'${name}', icon:"st.switches.switch.off", backgroundColor:"#ffffff"
			state "on", label:'${name}', icon:"st.switches.switch.on", backgroundColor:"#79b821"
		}
        standardTile("alloff", "device.alloff", decoration: "flat") {
			state "none", label: "all off", action:"alloff", icon:"st.switches.switch.off", backgroundColor:"#ffffff"
        }
		standardTile("zone1", "device.zone1", inactiveLabel: false, decoration: "flat") {
			state "off", label:'${name}', action:"zone1on", icon:"st.switches.switch.off", backgroundColor:"#ffffff", nextState: "on"
			state "on", label:'${name}', action:"zone1off", icon:"st.switches.switch.on", backgroundColor:"#79b821", nextState: "off"
		}
		standardTile("zone2", "device.zone2", inactiveLabel: false, decoration: "flat") {
			state "off", label:'${name}', action:"zone2on", icon:"st.switches.switch.off", backgroundColor:"#ffffff", nextState: "on"
			state "on", label:'${name}', action:"zone2off", icon:"st.switches.switch.on", backgroundColor:"#79b821", nextState: "off"
		}
		main "status"
		details(["status", "alloff", "zone1", "zone2"])
	}
}

// Parse incoming device messages to generate events
def parse(String description) {
	// TBD, if anything
}

def zone1on(){
	zigbee.smartShield(text: "1,1").format()
}

def zone1off(){
	zigbee.smartShield(text: "1,0").format()
}

def zone2on(){
	zigbee.smartShield(text: "2,1").format()
}

def zone2off(){
	zigbee.smartShield(text: "2,0").format()
}

def alloff(){
	zigbee.smartShield(text: "1,0|2,0|3,0|4,0|5,0|6,0|7,0|8,0").format()
}
