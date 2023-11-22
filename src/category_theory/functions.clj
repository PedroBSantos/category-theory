(ns category-theory.functions
  (:require [clojure.spec.alpha :as s]))

(defn factorial
  [number]
  {:pre [(s/valid? nat-int? number)]
   :post [(s/valid? nat-int? %)]}
  (cond
    (= 0 number) 1
    :else (* number (factorial (- number 1)))))

(defn fibonacci
  [number]
  {:pre [(s/valid? (s/and nat-int? (fn [x] (> x 0))) number)]
   :post [(s/valid? nat-int? %)]}
  (cond
    (= 1 number) 1
    (= 2 number) 1
    :else (+ (fibonacci (- number 2)) (fibonacci (- number 1)))))
