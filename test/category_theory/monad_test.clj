(ns category-theory.monad-test
  (:require [category-theory.functions :refer [safe-div]]
            [category-theory.monad :refer [>>= just? new-maybe nothing?]]
            [clojure.test :refer [deftest is testing]]))

(deftest monad-safe-div-test-just
  (testing "Deveria retornar Just 5 visto que é possível dividir 10 por 2"
    (is (just? (let [maybe (new-maybe 10)]
                 ((>>= maybe safe-div) 2))))))

(deftest monad-safe-div-test-nothing
  (testing "Deveria retornar Nothing visto que não é possível dividir por 0"
    (is (nothing? (let [maybe (new-maybe 10)]
                 ((>>= maybe safe-div) 0))))))