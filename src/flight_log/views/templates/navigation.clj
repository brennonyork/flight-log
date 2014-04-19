(ns flight-log.views.templates.navigation
  (:require [clj-template.html5 :refer :all :exclude [map meta time]]))

(defn messages
  ""
  []
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
    (li {:class "footer"} (a {:href "#"} "See All Messages")))))

(defn notifications
  ""
  []
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
    (li {:class "footer"} (a {:href "#"} "View all")))))

(defn tasks
  ""
  []
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
     (a {:href "#"} "View all tasks")))))

(defn user-menu
  ""
  []
  (li {:class "dropdown user user-menu"}
   (a {:href "#" :class "dropdown-toggle" :data-toggle "dropdown"}
    (i {:class "glyphicon glyphicon-user"})
    (span "Jane Doe" (i {:class "caret"})))
   (ul {:class "dropdown-menu"}
    (li {:class "user-header bg-light-blue"}
     (img- {:src "img/avatar3.png" :class "img-circle" :alt "User Image"})
     (p "Jane Doe - Web Dev" (small "Member since Nov. 2012"))))))
