(ns category-theory.core
  (:gen-class)
  (:require [category-theory.functions :refer [factorial fibonacci]]
            [category-theory.monoid :refer [id morph new-monoid]]))

(defn -main
  [& _]
  (let [monoid (new-monoid 5)]
    (println (id monoid))
    (println (morph monoid factorial))
    (println (morph monoid fibonacci)))) 
