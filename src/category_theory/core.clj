(ns category-theory.core
  (:gen-class)
  (:require [category-theory.monoid :refer [id morph new-monoid]]
            [clojure.spec.alpha :as s]))

(defn factorial
  [number]
  {:pre [(s/valid? nat-int? number)]
   :post [(s/valid? nat-int? %)]}
  (cond
    (= 0 number) 1
    :else (* number (factorial (- number 1)))))

(defn -main
  [& _]
  (let [monoid (new-monoid 5)]
    (println (id monoid))
    (println (morph monoid factorial)))) 
