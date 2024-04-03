(ns category-theory.functions
  (:require [category-theory.model :refer [new-maybe]]
            [category-theory.monoid :refer [morph new-monoid]]
            [clojure.spec.alpha :as s]))

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

(defn safe-div
  [a b]
  (if (zero? b)
    (new-maybe)
    (-> a
        (/ b)
        new-maybe)))

(defn greatest
  ([values]
   (if (empty? values)
     (new-monoid Integer/MIN_VALUE)
     (->> values
          first ,,,
          new-monoid ,,,
          (greatest values))))
  ([values initial]
   (reduce (fn [current-greatest current-value]
             (morph current-greatest (partial max current-value)))
           initial values)))