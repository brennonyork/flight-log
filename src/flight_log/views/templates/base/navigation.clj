(ns flight-log.views.templates.base.navigation
  (:require [clj-template.html5 :refer :all :rename {map html-map
                                                     meta html-meta
                                                     time html-time
                                                     header html-header}]))

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
  (li {:style "padding-left:5px" :class "dropdown notifications-menu"}
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

(defn user-menu
  ""
  [usr-firstname usr-lastname gravatar-hash]
  (let [full-name (str usr-firstname " " usr-lastname)]
    (li {:style "padding-left:10px" :class "dropdown user user-menu"}
     (a {:href "#" :class "dropdown-toggle" :data-toggle "dropdown"}
      (i {:class "glyphicon glyphicon-user"})
      (span full-name (i {:class "caret"})))
     (ul {:class "dropdown-menu"}
      (li {:class "user-header" :style "background-color:#333"}
       (img- {:src (str "http://www.gravatar.com/avatar/" gravatar-hash) :class "img-circle" :alt "User Image"})
       (p full-name (small "Member since Nov. 2012"))
       (button {:class "btn btn-default"}
        (a {:href "/logout"} "Logout")))))))

(defn header
  ""
  [website-title usr gravatar-hash]
  (let [{:keys [firstname lastname]} usr]
    (html-header {:class "header"}
     (a {:href "/dashboard" :class "logo"} website-title)
     (nav {:class "navbar navbar-static-top" :role "navigation"}
      (a {:href "#" :class "navbar-btn sidebar-toggle" :data-toggle "offcanvas" :role "button"}
       (span {:class "sr-only"} "Toggle navigation")
       (span {:class "icon-bar"})
       (span {:class "icon-bar"})
       (span {:class "icon-bar"}))
      (div {:class "navbar-right"}
       (ul {:class "nav navbar-nav"}
        (messages)
        (notifications)
        ;(tasks)
        (user-menu firstname lastname gravatar-hash)))))))
