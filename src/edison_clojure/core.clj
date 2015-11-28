(ns edison-clojure.core
  (:gen-class)
  (:import (mraa mraa)))

(defn prn-ndof [name arr] (println
                           (str "[" name  "]  x: " (aget arr 0)
                                ", y: " (aget arr 1)
                                ", z: " (aget arr 2) )))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (do
    (clojure.lang.RT/loadLibrary "mraajava")
    (clojure.lang.RT/loadLibrary "javaupm_lsm9ds0")
    (println (str "version: " (mraa/getVersion)))
    (println (str "platform name: " (mraa/getPlatformName)))
    (let [ndof (upm_lsm9ds0.LSM9DS0. 1 0x6b 0x1d)]
      (.init ndof)
      (.update ndof)
      (println (prn-ndof "Accelerometer" (.getAccelerometer ndof)))
      (println (prn-ndof "Gyroscope" (.getGyroscope ndof)))
      (println (prn-ndof "Magnetometer" (.getMagnetometer ndof))))))
