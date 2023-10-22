(ns category-theory.monoid)

(defrecord Monoid [value])
(defn new-monoid [value] (->Monoid value))
(defn id [e] e)
(defn morph [e f]
  (let [internal-value (get e :value)]
    (-> internal-value
        f
        new-monoid)))
