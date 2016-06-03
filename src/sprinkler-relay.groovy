definition(
  name: "Sprinkler Relay",
  namespace: "poindexter12",
  author: "http://github.com/poindexter12",
  description: "Child application to select Sprinkler Relay",
  version: "1.0",
  iconUrl: "http://cdn.device-icons.smartthings.com/Outdoor/outdoor12-icn.png",
  iconX2Url: "http://cdn.device-icons.smartthings.com/Outdoor/outdoor12-icn@2x.png",
  parent: "poindexter12:Irrigation Timer"
  )
  preferences {
    page(name: "mainPage", title: "Set Relay Information", install: true, uninstall: true) {
      section("Zone Information") {
        input name: "relayname", title: "Relay Name", required: true
        input name: "relay", title: "Zone Relay", description: "Enter the zone relay number to activate", type: "number", range: "1...8", required: true
        input name: "time", title: "Run Time", description: "Enter the number of minutes to run the relay", type: "number", required: true
      }
    }
  }
