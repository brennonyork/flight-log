(ns flight-log.views.layout
  (:require [liberator.core :as liberate :refer [resource]]
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
          :available-media-types ["text/html"]}))

(defn mk-resource
  "From the base resource and passed in overrides builds a modified resource"
  [handle-ok-content & {:as k}]
  (resource (merge base-resource k {:handle-ok handle-ok-content})))

(defn page [page-key request]
  (condp = page-key
    :dashboard (mk-resource (dashboard/render request))
    :login (mk-resource (login/render request))
    :flight-entry (mk-resource (flight-entry/render request))
    :flight-log (mk-resource (flight-log/render request))
    :logout (mk-resource (login/render request)
                         )))
