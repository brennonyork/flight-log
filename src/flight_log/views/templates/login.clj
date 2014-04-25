(ns flight-log.views.templates.login
  (:require [clj-template.html5 :refer :all :exclude [main map meta time]]))

(defn main
  ""
  []
  (html {:class "bg-black"}
   (head
    (meta- {:charset "UTF-8"})
    (title "Flight Log")
    (meta- {:content "width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" :name "viewport"})
    (link- {:href "css/bootstrap.min.css" :rel "stylesheet" :type "text/css"})
    (link- {:href "css/font-awesome.min.css" :rel "stylesheet" :type "text/css"})
    (link- {:href "css/AdminLTE.css" :rel "stylesheet" :type "text/css"})
    "<!--[if lt IE 9]>
         <script src=\"https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js\"></script>
         <script src=\"https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js\"></script>
     <![endif]-->")
   (body {:class "bg-black"}
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
       (i {:class "fa fa-google-plus"}))))
    (script {:src "http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"})
    (script {:src "js/bootstrap.min.js" :type "text/javascript"}))))
