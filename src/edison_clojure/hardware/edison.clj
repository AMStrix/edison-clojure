(ns edison-clojure.hardware.edison
  (:import (mraa mraa)))

(clojure.lang.RT/loadLibrary "mraajava")
(clojure.lang.RT/loadLibrary "javaupm_lsm9ds0")

(def nine-degrees-of-freedom
  "define and setup the 9 degrees of freedom sensors"
  ((fn [ndof]
     (.init ndof)
     (.update ndof)
     ndof)
   (upm_lsm9ds0.LSM9DS0. 1 0x6b 0x1d)))

(defn get-ndof-state
  "get current state of 9dof sensors"
  []
  {:accel (into [] (.getAccelerometer nine-degrees-of-freedom))
   :gyro (into [] (.getGyroscope nine-degrees-of-freedom))
   :mag (into [] (.getMagnetometer nine-degrees-of-freedom))})

(defn ndof-status-line [name data]
  (str name (apply str (map
                        (fn [v prefix] (str prefix (format "%+.3f" v)))
                        data
                        '("  x: " "  y: " "  z: ")))))

(defn print-ndof-status
  "print out the current state of the 9dof board"
  []
  (let [state (get-ndof-state)]
    (println "[nine-degrees-of-freedom]")
    (println (ndof-status-line "  accelerometer " (state :accel)))
    (println (ndof-status-line "  gyroscope     " (state :gyro)))
    (println (ndof-status-line "  magnetometer  " (state :mag)))))

(defn sample-ndof
  "take samples from the 9dof sensors"
  [num-times delay-ms]
  (map (fn [n]
         (. Thread (sleep delay-ms))
         (.update nine-degrees-of-freedom)
         (get-ndof-state))
       (range num-times)))


(defn print-info []
  (println "[edison]")
  (println (str "  version: " (mraa/getVersion)))
  (println (str "  platform name: " (mraa/getPlatformName)))
  (print-ndof-status))
