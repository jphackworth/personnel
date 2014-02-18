(defproject personnel "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [korma "0.3.0-RC6"]
                 [lobos "1.0.0-beta1"]
                 [org.xerial/sqlite-jdbc "3.7.2"]
                 [clj-time "0.6.0"]
                 [com.h2database/h2 "1.3.174"]]
  :main ^:skip-aot personnel.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
