(defproject edison-clojure "0.1.0-SNAPSHOT"
  :description "Intel Edison Clojure Project"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"] [net.n01se/clojure-jna "1.0.0"]]
  :main ^:skip-aot edison-clojure.core
  :target-path "target/%s"
  :resource-paths ["resources/mraa.jar", "resources/upm_lsm9ds0.jar"]
  :native-path "/usr/lib/java"
  :repl-options {:timeout 120000}
  :profiles {:uberjar {:aot :all}
             :repl {:plugins [[cider/cider-nrepl "0.9.1"]]}})
