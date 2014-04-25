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

(defn- content-header
  ""
  []
  (str
   (h1 "Dashboard" (small "Control Panel"))
   (ol {:class "breadcrumb"}
    (li
     (a {:href "#"}
      (i {:class "fa fa-dashboard"})
      " Home"))
    (li {:class "active"} "Dashboard"))))

(defn content
  ""
  []
  (str
   (div {:class "row"}
    (div {:class "col-lg-3 col-xs-6"}
     (mk-stat-box "aqua" (str "150" (sup {:style "font-size:20px"} "hr")) "Total Flight Time" "ion ion-plane" "#"))
    (div {:class "col-lg-3 col-xs-6"}
     (mk-stat-box "green" (str "53" (sup {:style "font-size:20px"} "%")) "Bounce Rate" "ion ion-stats-bars" "#"))
    (div {:class "col-lg-3 col-xs-6"}
     (mk-stat-box "yellow" "44" "User Registrations" "ion ion-person-add" "#"))
    (div {:class "col-lg-3 col-xs-6"}
     (mk-stat-box "red" (str "8" (sup {:style "font-size:20px"} " days")) "Since Last Flight" "ion ion-calendar" "#")))
   (div {:class "row"}
    (div {:class "col-xs-12 connectedSortable"}))
   (div {:class "row"}
    (section {:class "col-lg-6 connectedSortable"}
     (div {:class "box box-danger" :id "loading-example"}
      (div {:class "box-header"}
       (div {:class "pull-right box-tools"}
        (button {:class "btn btn-danger btn-sm" :data-widget "collapse" :data-toggle "tooltip" :title "Collapse"}
         (i {:class "fa fa-minus"})))
       (i {:class "fa fa-cloud"})
       (h3 {:class "box-title"} "Server Load"))
      (div {:class "box-body no-padding"}
       (div {:class "row"}
        (div {:class "col-sm-7"}
         (div {:class "chart" :id "bar-chart" :style "height:250px;"}))
        (div {:class "col-sm-5"}
         (div {:class "pad"}
          ;; Progress Bars
          (div {:class "clearfix"}
           (span {:class "pull-left"} "Bandwidth")
           (small {:class "pull-right"} "10/200 GB"))
          (div {:class "progress xs"}
           (div {:class "progress-bar progress-bar-green" :style "width:70%;"}))
          (div {:class "clearfix"}
           (span {:class "pull-left"} "Transferred")
           (small {:class "pull-right"} "10 GB"))
          (div {:class "progress xs"}
           (div {:class "progress-bar progress-bar-red" :style "width:70%;"}))
          (div {:class "clearfix"}
           (span {:class "pull-left"} "Activity")
           (small {:class "pull-right"} "73%"))
          (div {:class "progress xs"}
           (div {:class "progress-bar progress-bar-light-blue" :style "width:70%;"}))
          (div {:class "clearfix"}
           (span {:class "pull-left"} "FTP")
           (small {:class "pull-right"} "30 GB"))
          (div {:class "progress xs"}
           (div {:class "progress-bar progress-bar-aqua" :style "width:70%;"}))
          (p
           (button {:class "btn btn-default btn-sm pull-right"}
            (i {:class "fa fa-cloud-download"})
            " Generate PDF"))))))
      (div {:class "box-footer"}
       (div {:class "row"}
        (div {:class "col-xs-4 text-center" :style "border-right:1px solid #f4f4f4;"}
         (input- {:type "text" :class "knob" :data-readonly "true" :value "80" :data-min "0" :data-max "150" :data-width "60" :data-height "60" :data-angleArc "250" :data-angleOffset "-125" :data-fgColor "#f56954"})
         (div {:class "knob-label"} "CPU"))
        (div {:class "col-xs-4 text-center" :style "border-right:1px solid #f4f4f4;"}
         (input- {:type "text" :class "knob" :data-readonly "true" :value "50" :data-width "60" :data-height "60" :data-angleArc "250" :data-angleOffset "-125" :data-fgColor "#00a65a"})
         (div {:class "knob-label"} "Disk"))
        (div {:class "col-xs-4 text-center" :style "border-right:1px solid #f4f4f4;"}
         (input- {:type "text" :class "knob" :data-readonly "true" :value "30" :data-width "60" :data-height "60" :data-angleArc "250" :data-angleOffset "-125" :data-fgColor "#3c8dbc"})
         (div {:class "knob-label"} "RAM"))
            )))))))

(defn main
  ""
  []
  (fl-base/main {:sidebar-key :dashboard} (content-header) (content)))
