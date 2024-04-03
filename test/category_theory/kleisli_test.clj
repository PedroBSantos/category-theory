(ns category-theory.kleisli-test
  (:require [category-theory.kleisli :refer [safe-root-reciprocal]]
            [category-theory.model :refer [just? nothing?]]
            [clojure.test :refer [deftest is testing]]))

(deftest kleisli-safe-root-reciprocal-test-just
  (testing "Deveria compor as funções safe-reciprocal e safe-root para um valor x, se x != 0 em safe-reciprocal e x >= 0 em safe-root"
    (is (just? (let [result (safe-root-reciprocal 5)]
                 (result))))))

(deftest kleisli-safe-root-reciprocal-test-nothing
  (testing "Não deveria compor as funções safe-reciprocal e safe-root para um valor x, se x = 0 em safe-reciprocal"
    (is (nothing? (let [result (safe-root-reciprocal -5)]
                 (result))))))
