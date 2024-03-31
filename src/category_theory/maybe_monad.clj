(ns category-theory.maybe-monad)

(defrecord Maybe [value])
(defn new-maybe
  ([value] (->Maybe value))
  ([] (->Maybe nil)))

(defprotocol Just (just? [maybe]))
(defprotocol Nothing (nothing? [maybe]))

(extend-type
 Maybe
  Just
  (just? [maybe] (not= nil (:value maybe))))

(extend-type
 Maybe
  Nothing
  (nothing? [maybe] (nil? (:value maybe))))

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