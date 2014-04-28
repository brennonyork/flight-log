(ns flight-log.routes.home
  (:require [compojure.core :refer [defroutes]]
            [liberator.core :as liberate :refer [defresource]]
            [flight-log.views.layout :as layout]
            [flight-log.util :as util]
            [flight-log.views.pages.login :as login]
            [flight-log.views.pages.dashboard :as dashboard]
            [flight-log.views.pages.flight-log :as flight-log]
            [flight-log.views.pages.flight-entry :as flight-entry]))

(defmacro GET [route-str page-key]
  `(compojure.core/GET ~route-str [& ~'data] (layout/page ~page-key ~'data)))

(defmacro POST [route-str page-key]
  `(compojure.core/POST ~route-str [& ~'data] (layout/page ~page-key ~'data)))

(defroutes home-routes
  (GET "/" :dashboard)
  (GET "/login" :login)
  (GET "/dashboard" :dashboard)
  (GET "/flight-log" :flight-log)
  (GET "/flight-entry" :flight-entry)
  (GET "/data" :flight-entry))
