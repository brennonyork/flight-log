(ns flight-log.views.templates.home
  (:require [clj-template.html5 :refer :all]))

(def website-title "Flight Log")

(def index
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
      (recur (str links (link- {:rel "stylesheet" :type "text/css" :href (first hrefs)})) (rest hrefs)))))
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
      (li {:class "dropdown messages-menu"}
       (a {:href "#" :class "dropdown-toggle" :data-toggle "dropdown"}
        (i {:class "fa fa-envelope"})
        (span {:class "label label-success"} "4"))
       (ul {:class "dropdown-menu"}
        (li {:class "header"} "You have 4 messages")
        (li
         (ul {:class "menu"}
          (li
           (a {:href "#"}
            (div {:class "pull-left"}
             (img- {:src "img/avatar3.png" :class "img-circle" :alt "User Image"}))
            (h4 "Support Team " (small (i {:class "fa fa-clock-o"}) " 5 mins"))
            (p "Why not buy a new awesome theme?")))))
        (li {:class "footer"} "See All Messages")))
      (li {:class "dropdown notifications-menu"}
       (a {:href "#" :class "dropdown-toggle" :data-toggle "dropdown"}
        (i {:class "fa fa-warning"})
        (span {:class "label label-warning"} "10"))
       (ul {:class "dropdown-menu"}
        (li {:class "header"} "You have 10 notifications")
        (li
         (ul {:class "menu"}
          (li
           (a {:href "#"}
            (i {:class "ion ion-ios7-people info"})
            " 5 new members joined today"))))
        (li {:class "footer"} (a {:href "#"} "View all"))))
      (li {:class "dropdown tasks-menu"}
       (a {:href "#" :class "dropdown-toggle" :data-toggle "dropdown"}
        (i {:class "fa fa-tasks"})
        (span {:class "label label-danger"} "9"))
       (ul {:class "dropdown-menu"}
        (li {:class "header"} "You have 9 tasks")
        (li
         (ul {:class "menu"}
          (li
           (a {:href "#"}
            (h3 "design some buttons" (small {:class "pull-right"} "20%"))
            (div {:class "progress xs"}
             (div {:class "progress-bar progress-bar-aqua" :style "width:20%" :role "progressbar" :aria-valuemin "0"
                   :aria-valuemax "100"}
              (span {:class "sr-only"} "20% complete")))))))
        (li {:class "footer"}
         (a {:href "#"} "View all tasks"))))
      (li {:class "dropdown user user-menu"}
       (a {:href "#" :class "dropdown-toggle" :data-toggle "dropdown"}
        (i {:class "glyphicon glyphicon-user"})
        (span "Jane Doe" (i {:class "caret"})))
       (ul {:class "dropdown-menu"}
        (li {:class "user-header bg-light-blue"}
         (img- {:src "img/avatar3.png" :class "img-circle" :alt "User Image"})
         (p "Jane Doe - Web Dev" (small "Member since Nov. 2012")))))))))
   (script {:src "http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"})
   (script {:src "//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"})
   (loop [scripts "", srcs ["js/jquery-ui-1.10.3.min.js" "js/bootstrap.min.js" "js/plugins/morris/morris.min.js"
                            "js/plugins/sparkline/jquery.sparkline.min.js"
                            "js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"
                            "js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"
                            "js/plugins/fullcalendar/fullcalendar.min.js" "js/plugins/jqueryKnob/jquery.knob.js"
                            "js/plugins/daterangepicker/daterangepicker.js"
                            "js/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"
                            "js/plugins/iCheck/icheck.min.js" "js/AdminLTE/app.js" "js/AdminLTE/dashboard.js"]]
    (if (empty? srcs) scripts
      (recur (str scripts (script {:type "text/javascript" :src (first srcs)})) (rest srcs)))))))

;;         <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
;;         <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
;;         <!--[if lt IE 9]>
;;           <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
;;           <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
;;         <![endif]-->
