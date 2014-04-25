(ns flight-log.views.templates.flight-log
  (:require [flight-log.views.templates.base :as fl-base]
            [clj-template.html5 :refer :all :exclude [main map meta time]]))

(defn mk-stat-box
  ""
  [box-color stat-num stat-title stat-icon stat-href]
  (div {:class (str "small-box bg-" box-color)}
   (div {:class "inner"}
    (h3 stat-num)
    (p stat-title))
   (div {:class "icon"}
    (i {:class stat-icon}))
   (a {:href stat-href :class "small-box-footer"}
    "More info " (i {:class "fa fa-arrow-circle-right"}))))

(defn- content-header
  ""
  []
  (str
   (h1 "My Flight Log" (small "Details and Statistics"))
   (ol {:class "breadcrumb"}
    (li
     (a {:href "#"}
      (i {:class "fa fa-dashboard"})
      " Home"))
    (li "My Flight Log")
    (li {:class "active"} "Overview"))))

(defn- content
  ""
  []
  (div {:class "row"}
   (div {:class "col-md-12"}
    (div {:class "box"}
     (div {:class "box-header"}
      (h3 {:class "box-title"} "Log Entries"))
     (div {:class "box-body table-responsive"}
      (table {:id "example2" :class "table table-condensed"}
       (thead
        (tr
         (th "Date")
         (th "Aircraft Type")
         (th "Aircraft Identifier")
         (th "Route of Flight")
         (th "Remarks")
         (th "Total Flight Time")))
       (tbody
        (tr
         (td "11/23/2014")
         (td "Pitts S2-C")
         (td "N390BF")
         (td (str "KANP " (i {:class "fa fa-caret-right"}) " KCGE " (i {:class "fa fa-caret-right"}) " KANP"))
         (td "takeoffs / landings, loops, rudder walks")
         (td "3.2")))))))))

(defn main
  ""
  []
  (fl-base/main {:sidebar-key :flight-log} (content-header) (content)))
