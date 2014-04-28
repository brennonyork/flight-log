(ns flight-log.views.templates.base.head
  (:require [clj-template.util :refer [str-loop]]
            [clj-template.html5 :refer :all :rename {map html-map
                                                     meta html-meta
                                                     time html-time
                                                     head html-head}]))

(def all-css
  ["css/bootstrap.min.css" "css/font-awesome.min.css" "css/ionicons.min.css"
   "css/morris/morris.css" "css/jvectormap/jquery-jvectormap-1.2.2.css"
   "css/fullcalendar/fullcalendar.css" "css/daterangepicker/daterangepicker-bs3.css"
   "css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" "css/AdminLTE.css"
   "css/iCheck/all.css"])

(defn head
  "Returns the <head></head> tag"
  [website-title css-vec]
  (let [css-vec (if (= :all-css css-vec) all-css css-vec)]
   (html-head
    (meta- {:charset "UTF-8"})
    (title website-title)
    (meta- {:content "width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" :name "viewport"})
    (str-loop [link css-vec]
      (link- {:rel "stylesheet" :type "text/css" :href link}))
    "<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
     <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
     <!--[if lt IE 9]>
         <script src=\"https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js\"></script>
         <script src=\"https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js\"></script>
     <![endif]-->")))
