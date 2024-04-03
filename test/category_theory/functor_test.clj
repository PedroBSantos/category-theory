(ns category-theory.functor-test
  (:require [category-theory.functions :refer [factorial]]
            [category-theory.functor :refer [fmap]]
            [category-theory.model :refer [balance new-account]]
            [clojure.test :refer [deftest is testing]]))

(deftest fmap-factorial-test
  (testing "Deve aplicar o fmap em uma lista"
    (is (= '(1 2 6 24)
           (-> [1 2 3 4]
               (fmap ,,, factorial))))))

(deftest fmap-account-test
  (testing "Deve aplicar o fmap em uma conta"
    (is (zero?
         (let [account (new-account (random-uuid) "XPTO")]
           (fmap account balance))))))