(ns flight-log.views.templates.base
  (:require [clj-template.html5 :refer :all :exclude [main map meta time]]
            [flight-log.views.templates.base.navigation :as fl-nav]
            [flight-log.views.templates.base.sidebar :as fl-sidebar]))

(def website-title "Flight Log")

(defn main
  ""
  [content]
  (html
   (head
    (meta- {:charset "UTF-8"})
    (title website-title)
    (meta- {:content "width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" :name "viewport"})
    (loop [links "", hrefs ["css/bootstrap.min.css" "css/font-awesome.min.css" "css/ionicons.min.css"
                            "css/morris/morris.css" "css/jvectormap/jquery-jvectormap-1.2.2.css"
                            "css/fullcalendar/fullcalendar.css" "css/daterangepicker/daterangepicker-bs3.css"
                            "css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" "css/AdminLTE.css"]]
      (if (empty? hrefs) links
        (recur (str links (link- {:rel "stylesheet" :type "text/css" :href (first hrefs)})) (rest hrefs))))
    "<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
     <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
     <!--[if lt IE 9]>
         <script src=\"https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js\"></script>
         <script src=\"https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js\"></script>
     <![endif]-->")
   (body {:class "skin-blue"}
    (header {:class "header"}
     (a {:href "index.html" :class "logo"} website-title)
     (nav {:class "navbar navbar-static-top" :role "navigation"}
      (a {:href "#" :class "navbar-btn sidebar-toggle" :data-toggle "offcanvas" :role "button"}
       (span {:class "sr-only"} "Toggle navigation")
       (span {:class "icon-bar"})
       (span {:class "icon-bar"})
       (span {:class "icon-bar"}))
      (div {:class "navbar-right"}
       (ul {:class "nav navbar-nav"}
        (fl-nav/messages)
        (fl-nav/notifications)
        (fl-nav/tasks)
        (fl-nav/user-menu)))))
     (div {:class "wrapper row-offcanvas row-offcanvas-left"}
      (aside {:class "left-side sidebar-offcanvas"}
       (section {:class "sidebar"}
        (fl-sidebar/user-panel)
        (ul {:class "sidebar-menu"}
         (fl-sidebar/dashboard)
         (fl-sidebar/flight-log)
         (fl-sidebar/settings))))
       (aside {:class "right-side"}
        (section {:class "content-header"}
         (h1 "Blank Page" (small "Control Panel"))
         (ol {:class "breadcrumb"}
          (li
           (a {:href "#"}
            (i {:class "fa fa-dashboard"})
            " Home"))
          (li {:class "active"} "Blank page")))
        ;; Magic happens here
        (section {:class "content"} content)))
     (script {:src "http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"})
     (script {:src "//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"})
     (loop [scripts "", srcs ["js/jquery-ui-1.10.3.min.js" "js/bootstrap.min.js" "js/plugins/morris/morris.min.js"
                              "js/plugins/sparkline/jquery.sparkline.min.js"
                              "js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"
                              "js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"
                              "js/plugins/fullcalendar/fullcalendar.min.js" "js/plugins/jqueryKnob/jquery.knob.js"
                              "js/plugins/daterangepicker/daterangepicker.js"
                              "js/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"
                              "js/plugins/iCheck/icheck.min.js" "js/AdminLTE/app.js"
                              "js/AdminLTE/dashboard.js"
                              ]]
      (if (empty? srcs) scripts
        (recur (str scripts (script {:type "text/javascript" :src (first srcs)})) (rest srcs)))))))
