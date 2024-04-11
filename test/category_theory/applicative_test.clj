(ns category-theory.applicative-test
  (:require [category-theory.applicative :refer [<*> pure]]
            [category-theory.functions :refer [factorial]]
            [category-theory.functor :refer [fmap]]
            [clojure.test :refer [deftest is testing]]))

(deftest applicative-test-identity
  (testing "Usar applicative sobre um functor deve produzir o mesmo resultado se aplicar fmap ao functor"
    (let [functor [1 2 3 4]]
      (is (= (fmap functor factorial)
             (<*> (pure [] factorial) functor))))))

; functor = [1 2 3 4]
; fmap [1 2 3 4] factorial
; [1 2 6 24]

; <*> (pure [] factorial) [1 2 3 4]
; <*> [factorial] [1 2 3 4]
; fmap [1 2 3 4] factorial
; [1 2 6 24]

(deftest applicative-test-composition
  (testing "The result of applying an Applicative Functor that yields the function composition operator to the Applicative u, then apply the resulting Functor to v and finally applying that result to the final Applicative w should be the same as applying v to w and then applying u to the resulting Applicative"
    (let [w [1 2 3 4]
          v [#(+ 10 %)]
          u (pure [] #(* 2 %))]
      (is (= (<*> u (<*> v w))
             (-> (pure [] (fn [x] (partial comp x)))
                 (<*> u)
                 (<*> v)
                 (<*> w)))))))

; pure [] (fn [x] (partial comp x))
; [(fn [x] (partial comp x))]
; u = (pure [] (fn [x] (* 2 x)))
; <*> [(fn [x] (partial comp x))] (pure [] (fn [x] (* 2 x)))
; <*> [(fn [x] (partial comp x))] [(fn [x] (* 2 x)]
; [(comp (fn [x] (* 2 x))]

; <*> [(comp (fn [x] (* 2 x))] (pure [] #(+ 10 %))
; <*> [(comp (fn [x] (* 2 x))] [(fn [x] (+ 10 x))]
; [(comp (fn [x] (* 2 x)) (fn [x] (+ 10 x)))]

; <*> [(comp (fn [x] (* 2 x)) (fn [x] (+ 10 x)))] [1 2 3]
; fmap [1 2 3] (comp (fn [x] (* 2 x)) (fn [x] (+ 10 x)))
; 11 * 2 -> 22
; 12 * 2 -> 24
; 13 * 2 -> 16

(deftest applicative-test-homomorphism
  (testing "The result of applying the pure value of f to the pure value of x should be the same as applying f directly to x and then feeding that into pure"
    (let [f (fn [x] (+ 1 x))]
      (is (= (pure [] (f 1))
             (-> (pure [] f)
                 (<*> (pure [] 1))))))))

; f = (fn [x] (+ 1 x))
; (pure [] (f 1)) = [2]

; (pure [] f)
; [(fn [x] (+ 1 x))]
; <*> [(fn [x] (+ 1 x))] (pure [] 1)
; <*> [(fn [x] (+ 1 x))] [1]
; fmap [1] (fn [x] (+ 1 x))
; [2]

(deftest applicative-test-interchange
  (testing "The result of applying an Applicative Functor u to the pure value of y should be the same as taking the Applicative obtained by calling pure with a function that applies its argument to y and then applying that to u"
    (let [u (pure [] (fn [x] (+ 10 x)))
          y 50
          dollar-y #(% y)]
      (is (= (<*> u (pure [] y))
             (<*> (pure [] dollar-y) u))))))

; u = (pure [] (fn [x] (+ 10 x)))
; u = [(fn [x] (+ 10 x))]
; y = 50
; dollar-y = (fn [x] (x y))

; <*> u (pure [] y)
; <*> [(fn [x] (+ 10 x))] [50]
; [60]

; <*> (pure [] dollar-y) u
; <*> [(fn [x] (x y))] [(fn [x] (+ 10 x))]
; <*> [(fn [x] (x 50))] [(fn [x] (+ 10 x))]
; fmap [(fn [x] (+ 10 x))] (fn [x] (x 50))
; (fn [x] (x 50))) (fn [x] (+ 10 x))
; (fn [(+ 10 x)] (+ 10 50)))
; 60