(ns category-theory.functor-test
  (:require [category-theory.functions :refer [factorial fibonacci]]
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

(deftest fmap-identity-test
  (testing "Aplicar a função de identidade sobre o functor deve produzir o mesmo resultado que aplica-la sobre o functor em si, ou seja fmap id functor = id functor"
    (let [functor [1 2 3 4]]
      (is (= (fmap functor identity) (identity functor))))))

(deftest fmap-composition-test
  (testing "A composição deve ser respeitada, ou seja, fmap (g . f) functor = fmap (fmap functor f) g"
    (let [functor [1 2 3 4]
          composition (comp factorial fibonacci)]
      (is (= (fmap functor composition) (fmap (fmap functor fibonacci) factorial))))))