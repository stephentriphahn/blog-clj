(ns folio.core-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [folio.core :as core]))

(deftest fake-test
  (testing "fake description"
    (is (= 1 2))))
