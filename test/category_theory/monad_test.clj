(ns category-theory.monad-test
  (:require [category-theory.functions :refer [safe-div]]
            [category-theory.monad :refer [>>= just? new-maybe nothing?]]
            [clojure.test :refer [deftest is testing]]))

(deftest monad-safe-div-test
  (testing "Deveria retornar Just 5 visto que é possível dividir 10 por 2"
    (is (just? (let [maybe-1 (new-maybe 10)
                     maybe-2 (new-maybe 2)]
                 (>>= safe-div
                      maybe-1
                      maybe-2)))))
  (testing "Deveria retornar Nothing visto que não é possível dividir por 0"
    (is (nothing? (let [maybe-1 (new-maybe 10)
                        maybe-2 (new-maybe 0)]
                    (>>= safe-div
                         maybe-1
                         maybe-2))))))