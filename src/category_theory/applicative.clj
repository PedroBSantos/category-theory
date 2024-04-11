(ns category-theory.applicative
  (:require [category-theory.functor :refer [fmap]])
  (:import [clojure.lang LazySeq PersistentVector]))

; pure :: a -> f a
(defmulti pure (fn [functor _] (class functor)))
(defmethod pure PersistentVector [_ value] [value])
(defmethod pure LazySeq [_ value] (lazy-seq [value]))

; <*> :: f (a -> b) -> f a -> f b
(defmulti <*> (fn [functor-of-functions & _] (class functor-of-functions)))
(defmethod <*> PersistentVector [functor-of-functions & functors]
  (let [function (first functor-of-functions)]
    (reduce concat (for [functor functors]
                     (fmap functor function)))))

(defmethod <*> LazySeq [functor-of-functions & functors]
  (let [function (first functor-of-functions)]
    (reduce concat (for [functor functors]
                     (fmap functor function)))))
