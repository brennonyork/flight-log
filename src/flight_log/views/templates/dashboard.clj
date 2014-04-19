(ns flight-log.views.templates.dashboard
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

(defn main
  ""
  []
  (fl-base/main
    (div {:class "row"}
     (div {:class "col-lg-3 col-xs-6"}
      (mk-stat-box "aqua" (str "150" (sup {:style "font-size:20px"} "hr")) "Total Flight Time" "ion ion-plane" "#"))
     (div {:class "col-lg-3 col-xs-6"}
      (mk-stat-box "green" (str "53" (sup {:style "font-size:20px"} "%")) "Bounce Rate" "ion ion-stats-bars" "#"))
     (div {:class "col-lg-3 col-xs-6"}
      (mk-stat-box "yellow" "44" "User Registrations" "ion ion-person-add" "#"))
     (div {:class "col-lg-3 col-xs-6"}
      (mk-stat-box "red" (str "8" (sup {:style "font-size:20px"} " days")) "Since Last Flight" "ion ion-calendar" "#")))))
