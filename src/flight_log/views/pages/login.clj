(ns flight-log.views.pages.login
  (:require [clj-template.html5 :refer :all :exclude [main map meta time]]
            [flight-log.views.templates.base :as fl-base]))

(defn content
  ""
  []
  (div {:class "form-box" :id "login-box"}
   (div {:class "header bg-maroon"} "Flight Log")
    (div {:class "body bg-gray"}
     (p {:class "text-center"} "Sign in with the following:")
     (form {:method "post" :action "/openid" :class "text-center" }
      (input- {:value "https://www.google.com/accounts/o8/id" :name "identifier" :type "hidden"})
      (button {:class "btn btn-lg bg-red btn-circle" :type "submit"}
       (i {:class "fa fa-google-plus"})))
     (form {:method "post" :action "/openid" :class "text-center"}
      (input- {:value "https://me.yahoo.com/" :name "identifier" :type "hidden"})
      (button {:class "btn btn-lg bg-purple btn-circle" :type "submit"}
       (i {:class "ion ion-social-yahoo"}))))
    (div {:class "footer text-center"}
     (p "&copy 2014. All rights reserved."))))

(defn render
  ""
  [req]
  (fl-base/render req
   {:html-attrs {:class "bg-black"}
    :body-attrs {:class "bg-black"}
    :css-includes ["css/bootstrap.min.css"
                   "css/font-awesome.min.css"
                   "css/ionicons.min.css"
                   "css/AdminLTE.css"]
    :js-includes ["js/bootstrap.min.js"]
    :content (content)}))
