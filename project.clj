(defproject personnel "0.1.0-SNAPSHOT"
  :description "example app demonstrating Korma, Lobos and method-defining macros"
  :url "https://github.com/jphackworth/personnel"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [korma "0.3.0-RC6"]
                 [lobos "1.0.0-beta1"]
                 [clj-time "0.6.0"]
                 [com.h2database/h2 "1.3.174"]]
  :main ^:skip-aot personnel.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
