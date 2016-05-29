metadata {
	definition (name: "8 Channel Relay", namespace: "poindexter12", author: "Joe Seymour") {
		capability "Actuator"
		attribute "zone1", "string"
    command "alloff"
		command "zone1on"
		command "zone1off"
		command "zone2on"
		command "zone2off"
		command "zone3on"
		command "zone3off"
		command "zone4on"
		command "zone4off"
		command "zone5on"
		command "zone5off"
		command "zone6on"
		command "zone6off"
		command "zone7on"
		command "zone7off"
		command "zone8on"
		command "zone8off"
	}

	// simulator metadata
	simulator {
		status "on":  "command: 2003, payload: FF"
		status "off": "command: 2003, payload: 00"

		reply "2001FF,delay 100,2502": "command: 2503, payload: FF"
		reply "200100,delay 100,2502": "command: 2503, payload: 00"
	}

	tiles(scale: 1) {
		standardTile("status", "device.status", width: 2, height: 1) {
			state "inactive", label:' ${name} ', icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff"
			state "active", label:' ${name} ', icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821"
		}
        standardTile("alloff", "device.alloff", decoration: "flat") {
			state "none", label: "all off", action:"alloff", icon:"st.switches.switch.off", backgroundColor:"#ffffff"
        }
		standardTile("zone1", "device.zone1", inactiveLabel: true, decoration: "flat") {
			state "off", label:'Zone 1 ${name}', action:"zone1on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff"
			state "on", label:'Zone 1 ${name}', action:"zone1off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821"
		}
		standardTile("zone2", "device.zone2", inactiveLabel: false, decoration: "flat") {
			state "off", label:'Zone 2 ${name}', action:"zone2on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff"
			state "on", label:'Zone 2 ${name}', action:"zone2off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821"
		}
		standardTile("zone3", "device.zone3", inactiveLabel: false, decoration: "flat") {
			state "off", label:'Zone 3 ${name}', action:"zone3on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff"
			state "on", label:'Zone 3 ${name}', action:"zone3off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821"
		}
		standardTile("zone4", "device.zone4", inactiveLabel: false, decoration: "flat") {
			state "off", label:'Zone 4 ${name}', action:"zone4on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff"
			state "on", label:'Zone 4 ${name}', action:"zone4off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821"
		}
		standardTile("zone5", "device.zone5", inactiveLabel: false, decoration: "flat") {
			state "off", label:'Zone 5 ${name}', action:"zone5on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff"
			state "on", label:'Zone 5 ${name}', action:"zone5off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821"
		}
		standardTile("zone6", "device.zone6", inactiveLabel: false, decoration: "flat") {
			state "off", label:'Zone 6 ${name}', action:"zone6on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff"
			state "on", label:'Zone 6 ${name}', action:"zone6off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821"
		}
		standardTile("zone7", "device.zone7", inactiveLabel: false, decoration: "flat") {
			state "off", label:'Zone 7 ${name}', action:"zone7on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff"
			state "on", label:'Zone 7 ${name}', action:"zone7off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821"
		}
		standardTile("zone8", "device.zone8", inactiveLabel: false, decoration: "flat") {
			state "off", label:'Zone 8 ${name}', action:"zone8on", icon:"st.Outdoor.outdoor12", backgroundColor:"#ffffff"
			state "on", label:'Zone 8 ${name}', action:"zone8off", icon:"st.Outdoor.outdoor12", backgroundColor:"#79b821"
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
  sendEvent(name: "zone1", value: "on", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "1,1").format()
}

def zone1off(){
  sendEvent(name: "zone1", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "1,0").format()
}

def zone2on(){
  sendEvent(name: "zone2", value: "on", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "2,1").format()
}

def zone2off(){
  sendEvent(name: "zone2", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "2,0").format()
}

def zone3on(){
  sendEvent(name: "zone3", value: "on", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "3,1").format()
}

def zone3off(){
  sendEvent(name: "zone3", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "3,0").format()
}

def zone4on(){
  sendEvent(name: "zone4", value: "on", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "4,1").format()
}

def zone4off(){
  sendEvent(name: "zone4", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "4,0").format()
}

def zone5on(){
  sendEvent(name: "zone5", value: "on", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "5,1").format()
}

def zone5off(){
  sendEvent(name: "zone5", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "5,0").format()
}

def zone6on(){
  sendEvent(name: "zone6", value: "on", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "6,1").format()
}

def zone6off(){
  sendEvent(name: "zone6", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "6,0").format()
}

def zone7on(){
  sendEvent(name: "zone7", value: "on", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "7,1").format()
}

def zone7off(){
  sendEvent(name: "zone7", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "7,0").format()
}

def zone8on(){
  sendEvent(name: "zone8", value: "on", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "8,1").format()
}

def zone8off(){
  sendEvent(name: "zone8", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "8,0").format()
}

def alloff(){
  sendEvent(name: "zone1", value: "off", displayed: true, isStateChange: true, isPhysical: true);
  sendEvent(name: "zone2", value: "off", displayed: true, isStateChange: true, isPhysical: true);
	setActivityState();
	zigbee.smartShield(text: "1,0|2,0|3,0|4,0|5,0|6,0|7,0|8,0").format()
}

def setActivityState(){
  def zone1state = device.currentValue("zone1");
  if (zone1state == "on"){
  	sendEvent(name: "status", value: "active", displayed: true, isStateChange: true, isPhysical: false);
    return;
  }
  def zone2state = device.currentValue("zone2");
  if (zone2state == "on"){
  	sendEvent(name: "status", value: "active", displayed: true, isStateChange: true, isPhysical: false);
    return;
  }
  def zone3state = device.currentValue("zone3");
  if (zone3state == "on"){
  	sendEvent(name: "status", value: "active", displayed: true, isStateChange: true, isPhysical: false);
    return;
  }
  def zone4state = device.currentValue("zone4");
  if (zone4state == "on"){
  	sendEvent(name: "status", value: "active", displayed: true, isStateChange: true, isPhysical: false);
    return;
  }
  def zone5state = device.currentValue("zone5");
  if (zone5state == "on"){
  	sendEvent(name: "status", value: "active", displayed: true, isStateChange: true, isPhysical: false);
    return;
  }
  def zone6state = device.currentValue("zone6");
  if (zone6state == "on"){
  	sendEvent(name: "status", value: "active", displayed: true, isStateChange: true, isPhysical: false);
    return;
  }
  def zone7state = device.currentValue("zone7");
  if (zone7state == "on"){
  	sendEvent(name: "status", value: "active", displayed: true, isStateChange: true, isPhysical: false);
    return;
  }
  def zone8state = device.currentValue("zone8");
  if (zone8state == "on"){
  	sendEvent(name: "status", value: "active", displayed: true, isStateChange: true, isPhysical: false);
    return;
  }
  sendEvent(name: "status", value: "inactive", displayed: true, isStateChange: true, isPhysical: false);
}
