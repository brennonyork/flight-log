(ns flight-log.routes
  (:require [compojure.core :refer [defroutes]]
            [compojure.route :as route]
            [cemerick.friend :as friend]
            [ring.util.response :as resp]
            [flight-log.views.resource :as resource]
            [flight-log.views.pages [dashboard :as dashboard]
                                    [login :as login]
                                    [flight-entry :as flight-entry]
                                    [flight-log :as flight-log]]))

(defmacro authenticated
  "Wraps a resource page by determining if the friend identity is located in the session and leverages the compojure
  request bound to the `req` symbol"
  [p & args]
  `(if ((comp boolean friend/identity) ~'req)
     (resource/mk-resource ~p ~@args)
     (resp/redirect "/login")))

(defmacro GET [route-str page]
  `(compojure.core/GET ~route-str ~'req ~page))

(defmacro POST [route-str page]
  `(compojure.core/POST ~route-str ~'req ~page))

(defroutes home-routes
  (GET  "/"             (authenticated dashboard/render))
  (GET  "/login"        (resource/mk-resource login/render :authorized? true))
  (GET  "/dashboard"    (authenticated dashboard/render))
  (GET  "/flight-log"   (authenticated flight-log/render))
  (GET  "/flight-entry" (authenticated flight-entry/render))
  (POST "/data"         (authenticated flight-entry/render))
  (GET  "/logout"       (friend/logout* (resp/redirect "/login")))
  ;; Add resources
  (route/resources "/")
  ;; Add `not-found`
  (route/not-found "Not found."))
