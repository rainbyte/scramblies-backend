(ns scramblies-demo.core-test
  (:require [clojure.test :refer :all]
            [scramblies-demo.core :refer :all]))

(deftest test01
  (testing "empty inside empty"
    (is (scramble? "" ""))))

(deftest test02
  (testing "empty inside foo"
    (is (scramble? "foo" ""))))

(deftest test03
  (testing "foo inside fboaor"
    (is (scramble? "fboaor" "foo"))))

(deftest test04
  (testing "foo not inside bar"
    (is (not (scramble? "bar" "foo")))))

(deftest test05
  (testing "foo not inside empty"
    (is (not (scramble? "" "foo")))))

(deftest test06
  (testing "char not inside char"
    (is (not (scramble? "b" "f")))))

(deftest test07
  (testing "char inside char"
    (is (scramble? "f" "f"))))

(deftest test08
  (testing "char inside multiple char"
    (is (scramble? "ffffffffff" "f"))))
