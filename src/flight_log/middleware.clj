(ns flight-log.middleware
  (:require [taoensso.timbre :as timbre]
            [cemerick.friend :as friend]
            [cemerick.friend.openid :as openid]
            [environ.core :refer [env]]))

(defn log-request [handler]
  (if (env :dev)
    (fn [req]
      (timbre/debug req)
      (handler req))
    handler))

(defn friend-request [handler]
  (friend/authenticate
   handler
   {:allow-anon? true
    :default-landing-uri "/"
    ;:unauthorized-handler (fn [request] (prn "UNAUTHORIZED" request) request)
    ;:unauthenticated-handler (fn [request] (prn "UNAUTHENTICATED" request) request)
    :workflows [(openid/workflow
                 :login-failure-handler (fn [request] (prn "FAILED REQUEST!!" request) request)
                 :openid-uri "/openid"
                 :realm "http://localhost:3000"
                 :credential-fn identity)]}))
