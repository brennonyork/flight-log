(ns flight-log.routes.home
  (:require [compojure.core :refer [defroutes]]
            [compojure.route :as route]
            [flight-log.views.layout :as layout]
            [flight-log.util :as util]
            [flight-log.views.pages.login :as login]
            [flight-log.views.pages.dashboard :as dashboard]
            [flight-log.views.pages.flight-log :as flight-log]
            [flight-log.views.pages.flight-entry :as flight-entry]))

(defmacro GET [route-str page-key]
  `(compojure.core/GET ~route-str [] (layout/page ~page-key)))

(defmacro POST [route-str page-key]
  `(compojure.core/POST ~route-str [] (layout/page ~page-key)))

(defroutes home-routes
  (GET  "/"             :dashboard)
  (GET  "/login"        :login)
  (GET  "/dashboard"    :dashboard)
  (GET  "/flight-log"   :flight-log)
  (GET  "/flight-entry" :flight-entry)
  (POST "/data"         :flight-entry)
  (GET  "/logout"       :logout)
  ;; Add resources
  (route/resources "/")
  ;; Add `not-found`
  (route/not-found "Not found."))
