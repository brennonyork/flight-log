(ns flight-log.handler
  (:require [flight-log.routes :refer [home-routes]]
            [flight-log.middleware :as middleware]
            [noir.util.middleware :refer [app-handler]]
            [taoensso.timbre :as timbre]
            [taoensso.timbre.appenders.rotor :as rotor]
            [environ.core :refer [env]]
            [flight-log.routes.cljsexample :refer [cljs-routes]]))

(defn init
  "init will be called once when app is deployed as a servlet on an app server such as Tomcat
   * put any initialization code here"
  []
  (timbre/set-config! [:appenders :rotor] {:min-level :info
                                           :enabled? true
                                           :async? false
                                           :max-message-per-msecs nil
                                           :fn rotor/appender-fn})
  (timbre/set-config! [:shared-appender-config :rotor] {:path "flight_log.log"
                                                        :max-size (* 512 1024)
                                                        :backlog 10})
  (timbre/info "flight-log started successfully"))

(defn destroy
  "destroy will be called when your application shuts down, put any clean up code here"
  []
  (timbre/info "flight-log is shutting down..."))

(def app (app-handler [home-routes] :middleware [middleware/friend-request middleware/log-request]))
