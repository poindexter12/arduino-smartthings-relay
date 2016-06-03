definition(
  name: "Irrigation Timer",
  namespace: "poindexter12",
  author: "http://github.com/poindexter12",
  description: "Irrigation timer for sprinklers",
  version: "1.0",
  iconUrl: "http://cdn.device-icons.smartthings.com/Outdoor/outdoor12-icn.png",
  iconX2Url: "http://cdn.device-icons.smartthings.com/Outdoor/outdoor12-icn@2x.png"
  )
  preferences {
    page(name: "schedulePage", title: "Create An Irrigation Schedule", install: true, uninstall: true, nextPage: "settings") {
      section("Preferences") {
        input name: "name", title: "Schedule Name", required: true, multiple: false
        input name: "relay", type: "capability.relaySwitch", title: "Select a relay switch", require: true, multiple: false
        input name: "zipcode", type: "number", title: "Enter rain delay zip code", required: false
        input name: "starttime", type: "time", title: "Enter a start time"
      }
      section{
        app(name: "relays", appName: "Sprinkler Relay", namespace: "poindexter12", title: "Choose Relays", multiple: true)
      }
    }
  }

  def installed() {
  }

  def updated() {
  }

  def getPrecipitationInches() {
    // rain yesterday
    def yesterdaysWeather = getWeatherFeature("yesterday", zipcode)
    def yesterdaysPrecipitation = safeToFloat(yesterdaysWeather.history.dailysummary.precipi.toArray()[0])
    log.info("Yesterday's precipitation for $zipcode: $yesterdaysPrecipitation inches")

    // rain today
    def todaysWeather = getWeatherFeature("conditions", zipcode)
    def todaysPrecipitation = safeToFloat(todaysWeather.current_observation.precip_today_in)
    log.info("Today's precipitation for $zipcode: $todaysPrecipitation inches")

    // forecast rain for today
    def forecastWeather = getWeatherFeature("forecast", zipcode)
    def forecastPrecipitation = safeToFloat(forecastWeather.forecast.simpleforecast.forecastday.qpf_allday.in.toArray()[0])
    log.info("Forecast precipitation for $zipcode: $forecastPrecipitation inches")

    def totalPrecipitation = yesterdaysPrecipitation + todaysPrecipitation + forecastPrecipitation;
    log.info("Total precipitation for $zipcode: $totalPrecipitation inches")

    return totalPrecipitation;
  }
