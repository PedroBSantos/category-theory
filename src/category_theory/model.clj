(ns category-theory.model
  (:require [category-theory.functor :refer [Functor]]))

(defrecord Account [id owner balance])
(defn new-account [id owner] (->Account id owner 0.0))
(defn balance [account] (get account :balance 0.0))

(extend-type
 Account
  Functor
  (fmap [account function] (function account)))