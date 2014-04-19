(ns flight-log.views.layout
  (:require ;[selmer.parser :as parser]
            [clojure.string :as s]
            [ring.util.response :refer [content-type response]]
            [compojure.response :refer [Renderable]]
            [flight-log.views.templates.base :as base]))

;(def template-path "flight_log/views/templates/")

(deftype RenderableTemplate [template params]
  Renderable
  (render [this request]
    (content-type
      ;(->> (assoc params
      ;            (keyword (s/replace template #".html" "-selected")) "active"
      ;            :servlet-context (:context request))
      ;  (parser/render-file (str template-path template))
      ;  response)
      (response base/index)
      "text/html; charset=utf-8")))

(defn render [template & [params]]
  (RenderableTemplate. template params))
