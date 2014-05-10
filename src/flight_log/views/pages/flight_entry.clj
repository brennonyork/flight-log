(ns flight-log.views.pages.flight-entry
  (:require clojure.string
            [flight-log.views.templates.base :as fl-base]
            [clj-template.html5 :refer :all :rename {main html-main
                                                     map html-map
                                                     meta html-meta
                                                     time html-time}]
            [clj-template.util :refer [str-loop]]
            [clj-template.util.bootstrap :refer :all]))

(defn content-header
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

(defn content
  ""
  []
  (let [col-xs-6-fg (fn [& body] (col-xs-6 (form-group (clojure.string/join " " body))))
        col-sm-6-fg (fn [& body] (col-sm-6 (form-group (clojure.string/join " " body))))
        label-with-input (fn [l & {:as k}]
                           (str (label l) (input- (merge {:type "text" :class "form-control input-sm"} k))))]
  (row
   (col-lg-8
    (div {:class "box box-primary"}
     (div {:class "box-header"}
      (h3 {:class "box-title"} "Add An Entry"))
     (form {:role "form" :method "post" :action "/data"}
      (div {:class "box-body"}
       (row
        (col-xs-6-fg
          (label "Date:")
          (div {:class "input-group"}
           (div {:class "input-group-addon"}
            (i {:class "fa fa-calendar"}))
           (input- {:type "text" :class "form-control input-sm" :data-inputmask "'alias': 'dd/mm/yyyy'" :data-mask "" :name "date"})))
        (col-xs-6-fg
          (label-with-input "Total Flight Time" :name "total-flight-time")))
       (row
        (col-xs-6-fg
          (label-with-input "Aircraft Type" :name "aircraft-type" :id "aircraft-type"))
        (col-xs-6-fg
          (label-with-input "Aircraft Indentifier" :name "aircraft-ident" :id "aircraft-ident")))
       (row
        (col-sm-6-fg
          (label "Route of Flight")
          (small {:style "padding-left:5px"} "Separate airports with a space")
          (input- {:type "text" :class "form-control input-sm" :name "flight-route"}))
        (col-sm-6
         (row
          (col-xs-6-fg
            (label-with-input "# Ldgs." :name "number-landings" :placeholder "1"))
          (col-xs-6-fg
            (label-with-input "# Inst. App." :name "number-inst-approaches" :placeholder "0")))))
       ;; Remarks
       (row
        (col-xs-12
         (form-group
          (label "Remarks")
          (textarea {:class "form-control input-sm" :name "remarks"}))))
       (row
        (col-xs-12
         (label "Aircraft Category")
         (sup {:style "padding-left:2px"} (a {:href "#"} (i {:class "fa fa-question-circle"}))))
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
         (label "Conditions of Flight")
         (sup {:style "padding-left:2px"} (i {:class "fa fa-question-circle" :data-toggle "tooltip" :data-placement "right"
                                              :title "http://www.ecfr.gov/cgi-bin/text-idx?SID=0d4a360812218d9b7b1fe9927ecb0b10&node=14:2.0.1.1.2.1.1.31&rgn=div8"})))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-blue" :name "cof-night" :id "cof-night"})
            (label "Night"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "Night time ..." :id "cof-night-hours" :disabled ""}))))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-blue" :name "cof-inst-actual" :id "cof-inst-actual"})
            (label "Inst. (Actual)"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "Actual Instrument time ..." :id "cof-inst-actual-hours" :disabled ""}))))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-blue" :name "cof-inst-simulated" :id "cof-inst-simulated"})
            (label "Inst. (Simulated)"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "Simulated Instrument time ..." :id "cof-inst-simulated-hours" :disabled ""}))))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-blue" :name "cof-flight-simulator" :id "cof-flight-simulator"})
            (label "Flight Simulator"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "Flight Simulator time ..." :id "cof-flight-simulator-hours" :disabled ""})))))
       ;; Types of Piloting
       (row
        (col-xs-12
         (label "Type of Piloting")
         (sup {:style "padding-left:2px"} (i {:class "fa fa-question-circle" :data-toggle "tooltip" :data-placement "right"
                                              :title "http://www.ecfr.gov/cgi-bin/text-idx?SID=0d4a360812218d9b7b1fe9927ecb0b10&node=14:2.0.1.1.2.1.1.31&rgn=div8"})))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-grey" :name "top-pic" :id "top-pic"})
            (label "PIC (incl. Solo)"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "PIC time ..." :id "top-pic-hours" :disabled ""}))))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-grey" :name "top-sic" :id "top-sic"})
            (label "SIC"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "SIC time ..." :id "top-sic-hours" :disabled ""}))))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-grey" :name "top-dual" :id "top-dual"})
            (label "Dual"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "Dual time ..." :id "top-dual-hours" :disabled ""}))))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-grey" :name "top-cfi" :id "top-cfi"})
            (label "CFI"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "CFI time ..." :id "top-cfi-hours" :disabled ""}))))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-grey" :name "top-cross-country" :id "top-cross-country"})
            (label "Cross Country"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "Cross Country time ..." :id "top-cross-country-hours" :disabled ""}))))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-grey" :name "top-safety" :id "top-safety"})
            (label "Safety"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "Safety time ..." :id "top-safety-hours" :disabled ""})))))
       ;; Additional Logging
       (row
        (col-xs-12
         (label "Additional")
         (sup {:style "padding-left:2px"} (i {:class "fa fa-question-circle" :data-toggle "tooltip" :data-placement "right"
                                              :title "http://www.ecfr.gov/cgi-bin/text-idx?SID=0d4a360812218d9b7b1fe9927ecb0b10&node=14:2.0.1.1.2.1.1.31&rgn=div8"})))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-purple" :name "add-tailwheel" :id "add-tailwheel"})
            (label "Tailwheel"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "Tailwheel time ..." :id "add-tailwheel-hours" :disabled ""}))))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-orange" :name "add-high-performance" :id "add-high-performance"})
            (label "High Performance"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "High performance time ..." :id "add-high-performance-hours" :disabled ""}))))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-aero" :name "add-aerobatic" :id "add-aerobatic"})
            (label "Aerobatic"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "Aerobatic time ..." :id "add-aerobatic-hours" :disabled ""}))))
        (col-sm-6
         (row
          (col-xs-6-fg
            (input- {:type "checkbox" :class "line-green" :name "add-complex" :id "add-complex"})
            (label "Complex"))
          (col-xs-6-fg
            (input- {:type "text" :class "form-control input-sm" :placeholder "Complex time ..." :id "add-complex-hours" :disabled ""})))))
       (form-group
        (label {:for "imageAttachment"} "Attach Image")
        (input- {:type "file" :id "imageAttachment"})
        (p {:class "help-block"} "Attach an image to help validate the flight records.")))
      (div {:class "box-footer"}
       (input- {:type "submit" :onclick "return validate_form();" :class "btn btn-primary"} "Submit")))))
   (div {:class "col-lg-4"}
    (div {:class "box box-solid box-success"}
     (div {:class "box-header"}
      (h3 {:class "box-title"} "Recent Activity")))))))

(def js-local-script
  "function validate_form() {
     return true;
   }

   $(document).ready(function(){
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
   });")

(defn render
  ""
  [req]
  (fl-base/render req
   {:body-attrs {:class "skin-black"}
    :css-includes :all-css
    :js-includes ["js/jquery-ui-1.10.3.min.js"
                  "js/bootstrap.min.js"
                  "js/plugins/iCheck/icheck.min.js"
                  "js/AdminLTE/app.js"
                  "js/plugins/input-mask/jquery.inputmask.js"
                  "js/plugins/input-mask/jquery.inputmask.date.extensions.js"
                  "js/plugins/input-mask/jquery.inputmask.extensions.js"]
    :js-local-script js-local-script
    :content-header (content-header)
    :content (content)}
   :with-navigation))
