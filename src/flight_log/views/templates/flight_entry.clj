(ns flight-log.views.templates.flight-entry
  (:require clojure.string
            [flight-log.views.templates.base :as fl-base]
            [clj-template.html5 :refer :all :exclude [main map meta time]]
            [clj-template.util.bootstrap :refer :all]))

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
    (li {:class "active"} "Add An Entry"))))

(defn- content
  ""
  []
  (let [col-xs-6-fg (fn [& body]
                      (col-xs-6 (form-group (clojure.string/join " " body))))
        col-sm-6-fg (fn [& body]
                      (col-sm-6 (form-group (clojure.string/join " " body))))
        label-with-input (fn [l & {:as k}]
                           (str (label l) (input- (merge {:type "text" :class "form-control input-sm"} k))))]
  (row
   (col-lg-8
    (div {:class "box box-primary"}
     (div {:class "box-header"}
      (h3 {:class "box-title"} "Add An Entry"))
     (form {:role "form" :method "get" :action "/data"}
      (div {:class "box-body"}
       (row
        (col-xs-6-fg
          (label "Date:")
          (div {:class "input-group"}
           (div {:class "input-group-addon"}
            (i {:class "fa fa-calendar"}))
           (input- {:type "text" :class "form-control input-sm" :data-inputmask "'alias': 'dd/mm/yyyy'" :data-mask ""})))
        (col-xs-6-fg
          (label-with-input "Total Flight Time" :name "total-flight-time")))
       (row
        (col-xs-6-fg
          (label-with-input "Aircraft Type" :name "aircraft-type"))
        (col-xs-6-fg
          (label-with-input "Aircraft Indentifier" :name "aircraft-ident")))
       (row
        (col-sm-6-fg
          (label "Route of Flight")
          (small {:style "padding-left:5px"} "Separate airports with a space")
          (input- {:type "text" :class "form-control input-sm"}))
        (col-sm-6
         (row
          (col-xs-6-fg
            (label-with-input "# Ldgs." :name "number-landings"))
          (col-xs-6-fg
            (label-with-input "# Inst. App." :name "number-instrument-approaches")))))
       (row
        (col-xs-12
         (label "Aircraft Category"))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "radio" :class "line-red" :name "aircraft-class" :value "sel"})
            (label "SEL"))
          (col-xs-6-fg
            (input- {:type "radio" :class "line-red" :name "aircraft-class" :value "mel"})
            (label "MEL"))))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "radio" :class "line-red" :name "aircraft-class" :value "ses"})
            (label "SES"))
          (col-xs-6-fg
            (input- {:type "radio" :class "line-red" :name "aircraft-class" :value "mes"})
            (label "MES")))))
       ;; Conditions of Flight
       (row
        (col-xs-12
         (label "Conditions of Flight"))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-blue" :name "condition-night" :id "condition-night"})
            (label "Night"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "Night time ..." :id "condition-night-hours" :disabled ""}))))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-blue" :name "instrument-actual" :id "instrument-actual"})
            (label "Inst. (Actual)"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "Actual Instrument time ..." :id "instrument-actual-hours" :disabled ""}))))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-blue" :name "instrument-simulated" :id "instrument-simulated"})
            (label "Inst. (Simulated)"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "Simulated Instrument time ..." :id "instrument-simulated-hours" :disabled ""}))))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-blue" :name "flight-simulator" :id "flight-simulator"})
            (label "Flight Simulator"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "Flight Simulator time ..." :id "flight-simulator-hours" :disabled ""})))))
       ;; Types of Piloting
       (row
        (col-xs-12
         (label "Type of Piloting"))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-grey" :name "pic" :id "pic"})
            (label "PIC (incl. Solo)"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "PIC time ..." :id "pic-hours" :disabled ""}))))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-grey" :name "sic" :id "sic"})
            (label "SIC"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "SIC time ..." :id "sic-hours" :disabled ""}))))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-grey" :name "dual" :id "dual"})
            (label "Dual"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "Dual time ..." :id "dual-hours" :disabled ""}))))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-grey" :name "cfi" :id "cfi"})
            (label "CFI"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "CFI time ..." :id "cfi-hours" :disabled ""}))))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-grey" :name "cross-country" :id "cross-country"})
            (label "Cross Country"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "Cross Country time ..." :id "cross-country-hours" :disabled ""}))))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-grey" :name "safety" :id "safety"})
            (label "Safety"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "Safety time ..." :id "safety-hours" :disabled ""})))))
       ;; Remarks
       (row
        (col-xs-12
         (form-group
          (label "Additional Remarks")
          (textarea {:class "form-control input-sm" :name "remarks"}))))
       (form-group
        (label {:for "imageAttachment"} "Attach Image")
        (input- {:type "file" :id "imageAttachment"})
        (p {:class "help-block"} "Attach an image to help validate the flight records.")))
      (div {:class "box-footer"}
       (input- {:type "submit" :class "btn btn-primary"} "Submit")))))
   (div {:class "col-lg-4"}
    (div {:class "box box-solid box-success"}
     (div {:class "box-header"}
      (h3 {:class "box-title"} "Recent Activity")))))))

(defn footer-content
  ""
  []
  (str
   (script {:src "js/plugins/input-mask/jquery.inputmask.js" :type "text/javascript"})
   (script {:src "js/plugins/input-mask/jquery.inputmask.date.extensions.js" :type "text/javascript"})
   (script {:src "js/plugins/input-mask/jquery.inputmask.extensions.js" :type "text/javascript"})
   (script {:type "text/javascript"}
    "$(document).ready(function(){
       $(\"#datemask\").inputmask(\"dd/mm/yyyy\", {\"placeholder\": \"dd/mm/yyyy\"});
       $(\"[data-mask]\").inputmask();

       $('input[type=radio], input[type=checkbox]').each(function(){
         var self = $(this);
         css_color = self.attr('class');
         label = self.parent().next('label');
         label_text = label.text();

         label.remove();
         self.iCheck({
           checkboxClass: 'icheckbox_'+css_color,
           radioClass: 'iradio_'+css_color,
           insert: '<div class=\"icheck_line-icon\"></div>'+label_text
         });
       });
       $(':checkbox').on('ifChanged', function(event){
         id = $(this).attr('id');
         if ($(this).is(':checked')) {
           $('#'+id+'-hours').removeAttr('disabled');
         } else {
           $('#'+id+'-hours').attr('disabled','disabled');
         }
       });
     });")))

(defn main
  ""
  []
  (fl-base/main {:sidebar-key :flight-log} (content-header) (content) (footer-content)))
