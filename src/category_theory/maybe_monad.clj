(ns category-theory.maybe-monad 
  (:require [category-theory.model :refer [new-maybe nothing?]]))

(defn- should-apply? [_ & maybes]
  (->> maybes
       (some (fn [maybe] (nothing? maybe) ,,,))
       not ,,,))

; Maybe a -> (a -> Maybe b) -> Maybe b
; f a -> a -> f b -> f b
; Composição segura de funções. Uma função depende do efeito colateral de outra
(defmulti >>= should-apply?)

(defmethod >>= true [f & maybes]
  (->> maybes
       (map :value ,,,)
       (apply f ,,,)))

(defmethod >>= false [_ & _] (new-maybe))