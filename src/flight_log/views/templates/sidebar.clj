(ns flight-log.views.templates.sidebar
  (:require [clj-template.html5 :refer :all :exclude [map meta time]]))

(defn user-panel
  ""
  []
  (div {:class "user-panel"}
   (div {:class "pull-left image"}
    (img- {:src "img/avatar3.png" :class "img-circle" :alt "User Image"}))
   (div {:class "pull-left info"}
    (p "Hello, Jane!")
    (a {:href "#"}
     (i {:class "fa fa-circle text-success"}) " Online"))))

(defn search
  ""
  []
  (form {:action "#" :method "get" :class "sidebar-form"}
   (div {:class "input-group"}
    (input- {:type "text" :name "q" :class "form-control" :placeholder "Search..."})
    (span {:class "input-group-btn"}
     (button {:type "submit" :name "search" :id "search-btn" :class "btn btn-flat"}
      (i {:class "fa fa-search"}))))))

(defn dashboard
  ""
  []
  (li {:class "active"}
   (a {:href "index.html"}
    (i {:class "fa fa-dashboard"})
    (span " Dashboard"))))

(defn flight-log
  ""
  []
  (li
   (a {:href "pages/widgets.html"}
    (i {:class "fa fa-plane"})
    (span " My Flight Log")
    (small {:class "badge pull-right bg-green"} "new"))))

(defn settings
  ""
  []
  (li {:class "treeview"}
   (a {:href "#"}
    (i {:class "fa fa-gears"})
    (span "Settings")
    (i {:class "fa fa-angle-left pull-right"}))
   (ul {:class "treeview-menu"}
    (li
     (a {:href "pages/charts/morris.html"}
      (i {:class "fa fa-angle-double-right"})
      " Account"))
    (li
     (a {:href "pages/charts/flot.html"}
      (i {:class "fa fa-angle-double-right"})
      " Preferences"))
    (li
     (a {:href "pages/charts/inline.html"}
      (i {:class "fa fa-angle-double-right"})
      " ???")))))
