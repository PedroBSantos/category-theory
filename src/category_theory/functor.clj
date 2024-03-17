(ns category-theory.functor)

(defrecord Account [id owner balance])
(defn new-account [id owner] (->Account id owner 0.0))
(defn balance [account] (get account :balance 0.0))

(defprotocol Functor (fmap [functor function]))
(extend-type
 clojure.lang.PersistentVector
  Functor
  (fmap [functor function] (map function functor)))

(extend-type
 Account
  Functor 
  (fmap [account function] (function account)))