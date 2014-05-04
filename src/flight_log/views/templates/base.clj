(ns flight-log.views.templates.base
  (:require digest
            [clojure.string :as clj-str]
            [clj-template.html5 :refer :all :exclude [main map meta time]]
            [clj-template.util :refer [str-loop]]
            [flight-log.views.templates.base.head :as fl-head]
            [flight-log.views.templates.base.navigation :as fl-nav]
            [flight-log.views.templates.base.sidebar :as fl-sidebar]))

(defn inject-js
  "Inject any default javascript files (possibly through CDN) as well as any other passed in files to render"
  [js-includes js-local-script]
  (str
   (script {:src "http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"})
   (str-loop [js-src js-includes]
     (script {:type "text/javascript" :src js-src}))
   (when (not-empty js-local-script)
     (script {:type "text/javascript"} js-local-script))))

(defn render
  ""
  [req ;; request map
   {:keys [html-attrs ;; attributes for the `html` tag
           body-attrs ;; attributes for the `body` tag
           sidebar-key ;; sidebar key if the any sidebar item should be activated
           content-header
           content
           css-includes
           js-includes ;; vector of javascript files to include
           js-local-script] ;; singular string of javascript as the last include within the page
    :or {html-attrs {}
         body-attrs {}
         sidebar-key nil
         content-header ""
         content ""
         css-includes []
         js-includes []
         js-local-script ""}}
   & opts]
  (let [;; Set the overall website name here
        website-title "Flight Log"
        ;; Optional keys are processed as a set
        opts (set opts)
        openid (get-in req [:request :session :cemerick.friend/identity])
        {id :identity :keys [firstname lastname email language country] :as usr}
        (get-in openid [:authentications (get openid :current)])
        gravatar-hash (when-not (empty? email)
                        (digest/md5 (clj-str/lower-case (clj-str/trim email))))]
    (html html-attrs
     (fl-head/head website-title css-includes)
     (if (contains? opts :with-navigation)
       ;; IF TRUE
       (body body-attrs
        (fl-nav/header website-title usr gravatar-hash)
        (div {:class "wrapper row-offcanvas row-offcanvas-left"}
         (aside {:class "left-side sidebar-offcanvas"}
          (fl-sidebar/sidebar sidebar-key usr gravatar-hash))
         (aside {:class "right-side"}
          (section {:class "content-header"} content-header)
          (section {:class "content"} content)))
        (inject-js js-includes js-local-script))
       ;; ELSE FALSE
       (body body-attrs
        content
        (inject-js js-includes js-local-script))))))
