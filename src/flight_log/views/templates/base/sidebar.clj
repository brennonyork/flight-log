(ns flight-log.views.templates.base.sidebar
  (:require [clj-template.html5 :refer :all :exclude [map meta time]]))

(defn user-panel
  ""
  [usr-firstname gravatar-hash]
  (div {:class "user-panel"}
   (div {:class "pull-left image"}
    (img- {:src (str "http://www.gravatar.com/avatar/" gravatar-hash) :class "img-circle" :alt "User Image"}))
   (div {:class "pull-left info"}
    (p (str "Hello, " usr-firstname "!"))
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
  [& [sidebar-key]]
  (li {:class (if sidebar-key "active" "")}
   (a {:href "dashboard"}
    (i {:class "fa fa-dashboard"})
    (span " Dashboard"))))

(defn flight-log
  ""
  [& [sidebar-key]]
  (li {:class (if sidebar-key "treeview active" "treeview")}
   (a {:href "#"}
    (i {:class "fa fa-plane"})
    (span " My Flight Log")
    (i {:class "fa fa-angle-left pull-right"}))
   (ul {:class "treeview-menu"}
    (li
     (a {:href "flight-log"}
      (i {:class "fa fa-angle-double-right"})
      " Overview"))
    (li
     (a {:href "flight-entry"}
      (i {:class "fa fa-angle-double-right"})
      " Add An Entry")))))

(defn settings
  ""
  [& [sidebar-key]]
  (li {:class (if sidebar-key "treeview active" "treeview")}
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
      " Site Preferences")))))

;; (defn activated-sidebar-menu
;;   "Ensures the sidebar is active for the passed in key"
;;   [sidebar-key]
;;   (ul {:class "sidebar-menu"}
;;    (if (= sidebar-key :dashboard) (dashboard :active) (dashboard))
;;    (if (= sidebar-key :flight-log) (flight-log :active) (flight-log))
;;    (if (= sidebar-key :settings) (settings :active) (settings))))

(defn sidebar
  ""
  [sidebar-key usr gravatar-hash]
  (let [{:keys [firstname]} usr]
    (section {:class "sidebar"}
     (user-panel firstname gravatar-hash)
     (ul {:class "sidebar-menu"}
      (if (= sidebar-key :dashboard) (dashboard :active) (dashboard))
      (if (= sidebar-key :flight-log) (flight-log :active) (flight-log))
      (if (= sidebar-key :settings) (settings :active) (settings))))))
