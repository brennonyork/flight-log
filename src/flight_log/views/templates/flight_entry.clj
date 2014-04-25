(ns flight-log.views.templates.flight-entry
  (:require [flight-log.views.templates.base :as fl-base]
            [clj-template.html5 :refer :all :exclude [main map meta time]]))

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
  (div {:class "row"}
   (div {:class "col-lg-8"}
    (div {:class "box box-primary"}
     (div {:class "box-header"}
      (h3 {:class "box-title"} "Add An Entry"))
     (form {:role "form" :method "get" :action "/data"}
      (div {:class "box-body"}
       (div {:class "row"}
        (div {:class "col-sm-6"}
         (div {:class "form-group"}
          (label "Date:")
          (div {:class "input-group"}
           (div {:class "input-group-addon"}
            (i {:class "fa fa-calendar"}))
           (input- {:type "text" :class "form-control input-sm" :data-inputmask "'alias': 'dd/mm/yyyy'" :data-mask ""}))))
        (div {:class "col-sm-6"}
         (div {:class "form-group"}
          (label "Total Flight Time")
          (input- {:type "text" :class "form-control input-sm"}))))
       (div {:class "row"}
        (div {:class "col-xs-6"}
         (div {:class "form-group"}
          (label "Aircraft Type")
          (input- {:type "text" :class "form-control input-sm" :name "fname"})))
        (div {:class "col-xs-6"}
         (div {:class "form-group"}
          (label "Aircraft Indentifier")
          (input- {:type "text" :class "form-control input-sm"}))))
       (div {:class "form-group"}
        (label "Route of Flight")
        (small {:style "padding-left:5px"} "Separate airports with a space")
        (input- {:type "text" :class "form-control input-sm"}))
       (div {:class "row"}
        (div {:class "col-sm-6"} (div {:class "row"}
        (div {:class "col-xs-6"}
         (div {:class "form-group"}
          (input- {:type "radio" :class "line-red" :name "aircraft-class" :value "sel"})
          (label "SEL")))
        (div {:class "col-xs-6"}
         (div {:class "form-group"}
          (input- {:type "radio" :class "line-red" :name "aircraft-class" :value "mel"})
          (label "MEL")))))
        (div {:class "col-sm-6"} (div {:class "row"}
        (div {:class "col-xs-6"}
         (div {:class "form-group"}
          (input- {:type "radio" :class "line-red" :name "aircraft-class" :value "ses"})
          (label "SES")))
        (div {:class "col-xs-6"}
         (div {:class "form-group"}
          (input- {:type "radio" :class "line-red" :name "aircraft-class" :value "mes"})
          (label "MES"))))))
       (div {:class "row"}
        (div {:class "col-xs-6"}
         (div {:class "row"}
          (div {:class "col-xs-4"}
           (div {:class "form-group"}
            (input- {:type "checkbox" :class "line-aero" :name "flight-rule" :value "vfr" :id "solo"})
            (label "Solo")))
          (div {:class "col-xs-8"}
           (div {:class "form-group"}
            (input- {:type "text" :class "form-control input-sm" :placeholder "Solo time ..." :id "solo-hours" :disabled ""}))))
        ;(div {:class "col-xs-6"}
         (div {:class "form-group"}
          (input- {:type "checkbox" :class "line-aero" :name "flight-rule" :value "ifr"})
          (label "IFR"))))
       (div {:class "row"}
        (div {:class "col-xs-6"}
         (div {:class "form-group"}
          (input- {:type "radio" :class "line-green" :name "flight-distance" :value "vfr"})
          (label "Local")))
        (div {:class "col-xs-6"}
         (div {:class "form-group"}
          (input- {:type "radio" :class "line-green" :name "flight-distance" :value "ifr"})
          (label "Cross Country"))))
       (div {:class "row"}
        (div {:class "col-xs-6"}
         (div {:class "form-group"}
          (input- {:type "radio" :class "line-aero" :name "flight-time" :value "vfr"})
          (label "Day")))
        (div {:class "col-xs-6"}
         (div {:class "form-group"}
          (input- {:type "radio" :class "line-aero" :name "flight-time" :value "ifr"})
          (label "Night"))))
       (div {:class "row"}
        (div {:class "col-xs-4"}
         (div {:class "form-group"}
          (input- {:type "radio" :class "line-purple" :name "command-type" :value "pic"})
          (label "PIC")))
        (div {:class "col-xs-4"}
         (div {:class "form-group"}
          (input- {:type "radio" :class "line-purple" :name "command-type" :value "sic"})
          (label "SIC")))
        (div {:class "col-xs-4"}
         (div {:class "form-group"}
          (input- {:type "radio" :class "line-purple" :name "command-type" :value "dual"})
          (label "Dual"))))
       (div {:class "form-group"}
        (label {:for "imageAttachment"} "Attach Image")
        (input- {:type "file" :id "imageAttachment"})
        (p {:class "help-block"} "Attach an image to help validate the flight records.")))
      (div {:class "box-footer"}
       (input- {:type "submit" :class "btn btn-primary"} "Submit")))))
   (div {:class "col-lg-4"}
    (div {:class "box box-solid box-success"}
     (div {:class "box-header"}
      (h3 {:class "box-title"} "Recent Activity"))))))

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
