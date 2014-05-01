(ns flight-log.routes.home
  (:require [compojure.core :refer [defroutes]]
            [flight-log.views.layout :as layout]
            [flight-log.util :as util]
            [flight-log.views.pages.login :as login]
            [flight-log.views.pages.dashboard :as dashboard]
            [flight-log.views.pages.flight-log :as flight-log]
            [flight-log.views.pages.flight-entry :as flight-entry]))

(defmacro GET [route-str page-key]
  `(compojure.core/GET ~route-str ~'request (layout/page ~page-key ~'request)))

(defmacro POST [route-str page-key]
  `(compojure.core/POST ~route-str ~'request (layout/page ~page-key ~'request)))

(defroutes home-routes
  (GET "/" :dashboard)
  (GET "/login" :login)
  (GET "/dashboard" :dashboard)
  (GET "/flight-log" :flight-log)
  (GET "/flight-entry" :flight-entry)
  (POST "/data" :flight-entry)
  (GET "/logout" :logout))
