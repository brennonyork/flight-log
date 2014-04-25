(ns flight-log.routes.home
  (:use compojure.core)
  (:require [flight-log.views.layout :as layout]
            [flight-log.util :as util]
            [flight-log.views.templates.login :as login]
            [flight-log.views.templates.dashboard :as dashboard]
            [flight-log.views.templates.flight-log :as flight-log]
            [flight-log.views.templates.flight-entry :as flight-entry]))

(defn home-page []
  (layout/render (dashboard/main) {:content (util/md->html "/md/docs.md")}))

(defn login-page []
  (layout/render (login/main)))

(defn flight-log-page []
  (layout/render (flight-log/main)))

(defn flight-entry-page []
  (layout/render (flight-entry/main)))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/login" [] (login-page))
  (GET "/dashboard" [] (home-page))
  (GET "/flight-log" [] (flight-log-page))
  (GET "/flight-entry" [] (flight-entry-page))
  (GET "/data" [& data] (flight-entry-page)))
