(ns flight-log.views.resource
  (:require [liberator.core :as liberate :refer [resource]]))

(def base-resource
  "Defines a base liberator resource with healthy defaults from the liberator defaults"
  (merge liberate/default-functions
         {:known-methods [:get :post]
          :allowed-methods [:get]
          :available-languages ["en-us"]
          :available-media-types ["text/html"]
          :handle-not-acceptable "Failed not-acceptable" ;{"application/json" {:success false
                                 ;                     :message "No acceptable resource available"}
                                 ; "text/plain" "No acceptable resource available."
                                 ; "text/html" (route/not-found "Route not found!")}
          :handle-not-found "Failed not-found";{"application/json" {:success false
                            ;                     :message "Resource not found."}
                            ; "text/plain" "Resource not found."
                            ; "text/html" (route/not-found "Route not found!")}
          :handle-unauthorized "Failed unauthorized";(resp/redirect "/login")
          }))

(defn mk-resource
  "From the base resource and passed in overrides builds a modified resource"
  [handle-ok-content & {:as k}]
  (resource (merge base-resource k {:handle-ok handle-ok-content})))
