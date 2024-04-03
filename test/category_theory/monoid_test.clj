(ns category-theory.monoid-test
  (:require [category-theory.functions :refer [factorial fibonacci greatest]]
            [category-theory.monoid :refer [morph new-monoid]]
            [clojure.test :refer [deftest is testing]]))

(deftest monoid-factorial-test
  (testing "Deveria retornar um monoid de valor 120"
    (is (= (new-monoid 120)
           (let [monoid (new-monoid 5)]
             (morph monoid factorial))))))

(deftest monoid-fibonacci-test
  (testing "Deveria retornar um monoid de valor 5"
    (is (= (new-monoid 5)
           (let [monoid (new-monoid 5)]
             (morph monoid fibonacci))))))

(deftest monoid-factorial-after-fibonacci-test
  (testing "Deveria retornar um monoid de valor 2"
    (is (= (new-monoid 2)
           (let [monoid (new-monoid 3)
                 factorial-after-fibonacci (comp factorial fibonacci)]
             (morph monoid factorial-after-fibonacci))))))

(deftest monoid-greatest-test
  (testing "Deveria retornar o maior valor de [1 2 -5 9 -7 -5 10]"
    (is (= (new-monoid 10)
           (let [values [1 2 -5 9 -7 -5 10]]
             (greatest values)))))
  (testing "Deveria retornar Integer/MIN_VALUE"
    (is (= (new-monoid Integer/MIN_VALUE)
           (let [values []]
             (greatest values))))))