(defproject flight-log "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"

  :plugins [[lein-ring "0.8.10"]
            [lein-environ "0.4.0"]
            [lein-cljsbuild "0.3.3"]]

  :dependencies [[ring-server "0.3.1"]
                 [domina "1.0.2"]
                 [environ "0.4.0"]
                 [markdown-clj "0.9.41"]
                 [com.taoensso/timbre "3.1.6"]
                 [prismatic/dommy "0.1.2"]
                 [org.clojure/clojurescript "0.0-2138"]
                 [org.clojure/clojure "1.6.0"]
                 [com.taoensso/tower "2.0.2"]
                 [cljs-ajax "0.2.3"]
                 [liberator "0.11.0"]
                 ;[clj-template "0.9.1"]
                 [selmer "0.6.5"]
                 [lib-noir "0.8.1"]
                 [compojure "1.1.6"]]

  :cljsbuild {:builds [{:source-paths ["src-cljs"]
                        :compiler {:pretty-print false
                                   :output-to "resources/public/js/site.js"
                                   :optimizations :advanced}}]}
  :ring {:handler flight-log.handler/app
         :init flight-log.handler/init
         :destroy flight-log.handler/destroy}

  :repl-options {:init-ns flight-log.repl}

  :profiles {:uberjar {:aot :all}
             :production {:ring {:open-browser? false
                                 :stacktraces? false
                                 :auto-reload? false}}
             :dev {:dependencies [[ring-mock "0.1.5"]
                                  [ring/ring-devel "1.2.2"]]
                   :env {:dev true}}}

  :aliases {"start" ["ring" "server-headless"]}

  :min-lein-version "2.0.0")
