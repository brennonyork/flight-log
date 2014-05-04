(ns flight-log.routes.cljsexample
  (:require [compojure.core :refer :all]
            ;[flight-log.views.layout :as layout]
            ))

(def messages
  (atom
    [{:message "Hello world"
      :user    "Foo"}
     {:message "Ajax is fun"
      :user    "Bar"}]))

(defroutes cljs-routes
  (GET "/cljsexample" [] "<p>fail</p>");(layout/render "cljsexample.html"))
  (GET "/messages" [] {:body @messages})
  (POST "/add-message" [message user]
        {:body (swap! messages conj {:message message :user user})}))
