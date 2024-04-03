(ns category-theory.functor)

(defprotocol Functor (fmap [functor function]))
(extend-type
 clojure.lang.PersistentVector
  Functor
  (fmap [functor function] (map function functor)))
