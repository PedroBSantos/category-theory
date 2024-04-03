(ns category-theory.kleisli 
  (:require [category-theory.model :refer [just? new-maybe]]))

; a -> (b, string)
(defn lower-case [string]
  [(.toLowerCase string) (str "lower-case " string " ")])

; a -> (b, string)
(defn upper-case [string]
  [(.toUpperCase string) (str "upper-case " string " ")])

(defn- pair [a b] [a b])

; Composition
(defn >=> [f g]
  (fn [a] 
    (let [[first-f-result second-f-result] (f a)
          [first-g-result second-g-result] (g first-f-result)]
      (pair first-g-result (str second-f-result second-g-result)))))

; Identity
(defn id [a] (pair a ""))

(defn should-apply? [maybe _] (just? maybe))
(defmulti >>= should-apply?)
(defmethod >>= false [maybe _] 
  (fn [] maybe))
(defmethod >>= true [maybe f] 
  (fn [] (f (:value maybe))))

(defn safe-reciprocal [value]
  (if (not= 0 value)
    (new-maybe (/ 1 value))
    (new-maybe)))

(defn safe-root [value]
  (if (>= value 0)
    (new-maybe (Math/sqrt value))
    (new-maybe)))

; a -> M b
(defn safe-root-reciprocal [value]
  (let [reciprocal (safe-reciprocal value)]
    (>>= reciprocal safe-root)))
