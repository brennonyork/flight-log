(ns flight-log.routes.home
  (:use compojure.core)
  (:require [flight-log.views.layout :as layout]
            [flight-log.util :as util]
            [flight-log.views.templates.dashboard :as dashboard]))

(defn home-page []
  (layout/render (dashboard/main) {:content (util/md->html "/md/docs.md")}))

(defn flight-log-page []
  (layout/render (dashboard/main)))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/dashboard" [] (home-page))
  (GET "/flight-log" [] (flight-log-page)))
