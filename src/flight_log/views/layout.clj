(ns flight-log.views.layout
  (:require [liberator.core :as liberate :refer [resource]]
            [cemerick.friend :as friend]
            [ring.util.response :as resp]
            [compojure.route :as route]
            [flight-log.views.pages [dashboard :as dashboard]
                                    [login :as login]
                                    [flight-entry :as flight-entry]
                                    [flight-log :as flight-log]]))

(def base-resource
  "Defines a base liberator resource with healthy defaults from the liberator defaults"
  (merge liberate/default-functions
         {:known-methods [:get :post]
          :allowed-methods [:get]
          :available-languages ["en-us"]
          :available-media-types ["text/html"]
          :authorized? (comp boolean friend/identity :request)
          :handle-not-acceptable "Failed not-acceptable" ;{"application/json" {:success false
                                 ;                     :message "No acceptable resource available"}
                                 ; "text/plain" "No acceptable resource available."
                                 ; "text/html" (route/not-found "Route not found!")}
          :handle-not-found "Failed not-found";{"application/json" {:success false
                            ;                     :message "Resource not found."}
                            ; "text/plain" "Resource not found."
                            ; "text/html" (route/not-found "Route not found!")}
          :handle-unauthorized (fn [ctx] (prn "UNAUTHORIZED" ctx) "Failed unauthorized");{"application/json" {:success false
                               ;                     :message "Not authorized!"}
                               ; :default (constantly "Not authorized.")}
          }))

(defn mk-resource
  "From the base resource and passed in overrides builds a modified resource"
  [handle-ok-content & {:as k}]
  (resource (merge base-resource k {:handle-ok handle-ok-content})))

(defn page [page-key]
  (condp = page-key
    :dashboard    (mk-resource dashboard/render)
    :login        (mk-resource login/render :authorized? true)
    :logout       (friend/logout* (resp/redirect "/login"))
    :flight-entry (mk-resource flight-entry/render)
    :flight-log   (mk-resource flight-log/render)))
