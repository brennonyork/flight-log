(ns flight-log.views.templates.base
  (:require [clj-template.html5 :refer :all :exclude [main map meta time]]
            [clj-template.util :refer [str-loop]]
            [flight-log.views.templates.base.head :as fl-head]
            [flight-log.views.templates.base.navigation :as fl-nav]
            [flight-log.views.templates.base.sidebar :as fl-sidebar]))

(defn inject-js
  "Inject any default javascript files (possibly through CDN) as well as any other passed in files to render"
  [js-includes]
  (str
   (script {:src "http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"})
   (str-loop [js-src js-includes]
     (script {:type "text/javascript" :src js-src}))))

(defn render
  ""
  [{:keys [html-attrs ;; attributes for the `html` tag
           body-attrs ;; attributes for the `body` tag
           sidebar-key ;; sidebar key if the any sidebar item should be activated
           content-header
           content
           css-includes
           js-includes]
    :or {html-attrs {}
         body-attrs {}
         sidebar-key nil
         content-header ""
         content ""
         css-includes []
         js-includes []}}
   & opts]
  (let [website-title "Flight Log"
        opts (set opts)]
    (html html-attrs
     (fl-head/head website-title css-includes)
     (if (contains? opts :with-navigation)
       ;; IF TRUE
       (body body-attrs
        (fl-nav/header website-title)
        (div {:class "wrapper row-offcanvas row-offcanvas-left"}
         (aside {:class "left-side sidebar-offcanvas"}
          (fl-sidebar/sidebar sidebar-key))
         (aside {:class "right-side"}
          (section {:class "content-header"} content-header)
          (section {:class "content"} content)))
        (inject-js js-includes))
       ;; ELSE FALSE
       (body body-attrs
        content
        (inject-js js-includes))))))
