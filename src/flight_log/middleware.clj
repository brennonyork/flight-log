(ns flight-log.middleware
  (:require [taoensso.timbre :as timbre]
            [ring.middleware [session :as session]
                             [params :as params]
                             [keyword-params :as keyword-params]
                             [nested-params :as nested-params]]
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
    ;:unauthorized-handler (fn [request] (prn "HANDLER-UNAUTHORIZED" request) request)
    ;:unauthenticated-handler (fn [request] (prn "HANDLER-UNAUTHENTICATED" request) request)
    :workflows [(openid/workflow
                 :login-failure-handler (fn [request] (prn "FAILED REQUEST!!" request) request)
                 :openid-uri "/openid"
                 :realm "http://localhost:3000"
                 :credential-fn identity)]}))

;; (defn make-session [handler]
;;   (fn [req]
;;     (assoc (handler req) :session nil)))

;; (defn session-request [handler]
;;   (session/wrap-session handler))

;; (defn param-request [handler]
;;   (-> handler
;;       (params/wrap-params)
;;       (keyword-params/wrap-keyword-params)
;;       (nested-params/wrap-nested-params)))
