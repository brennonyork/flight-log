(ns flight-log.views.pages.login
  (:require [clj-template.html5 :refer :all :exclude [main map meta time]]
            [flight-log.views.templates.base :as fl-base]))

(defn content
  ""
  []
  (div {:class "form-box" :id "login-box"}
   (div {:class "header bg-maroon"} "Sign In")
   (form {:action "index.html" :method "post"}
    (div {:class "body bg-gray"}
     (div {:class "form-group"}
      (input- {:type "text" :name "user-id" :class "form-control" :placeholder "User ID"}))
     (div {:class "form-group"}
      (input- {:type "text" :name "password" :class "form-control" :placeholder "Password"}))
     (div {:class "form-group"}
      (input- {:type "checkbox" :name "remember-me"})
      " Remember Me"))
    (div {:class "footer"}
     (button {:type "submit" :class "btn bg-maroon btn-block"} "Sign me in")))
   (div {:class "margin text-center"}
    (span "Sign in with the following:")
    (br-)
    (button {:class "btn bg-light-blue btn-circle"}
     (i {:class "fa fa-facebook"}))
    (button {:class "btn bg-aqua btn-circle"}
     (i {:class "fa fa-twitter"}))
    (button {:class "btn bg-red btn-circle"}
     (i {:class "fa fa-google-plus"})))))

(defn render
  ""
  [& request]
  (fl-base/render
   {:html-attrs {:class "bg-black"}
    :body-attrs {:class "bg-black"}
    :css-includes ["css/bootstrap.min.css"
                   "css/font-awesome.min.css"
                   "css/AdminLTE.css"]
    :js-includes ["js/bootstrap.min.js"]
    :content (content)}))
