(ns category-theory.monad)

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

(defn- should-apply? [maybe _] (just? maybe))

; Maybe a -> (a -> Maybe b) -> Maybe b
; f a -> a -> f b -> f b
; Composição segura de funções. Uma função depende do efeito colateral de outra
(defmulti >>= should-apply?)

; Retorna a invocação parcial de uma função visto que Maybe a é Just a
(defmethod >>= true [m f]
  (let [value (:value m)]
    (fn [a] (f value a))))

(defmethod >>= false [_ _] (new-maybe))