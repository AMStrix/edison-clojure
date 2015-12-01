(ns edison-clojure.core
  (:gen-class))

(require 'edison-clojure.hardware.edison)
(alias 'edison 'edison-clojure.hardware.edison)

(defn -main
  "edison-clojure main, start the party"
  [& args]
  (do
    (edison/print-info)))
