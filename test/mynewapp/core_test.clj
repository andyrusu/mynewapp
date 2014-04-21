(ns mynewapp.core-test
  (:use clojure.test
        
        newapp.core))

(deftest system-test
  (testing "System.getParams test"
    (is (= "C:\\Users\\Andy\\AppData\\Local\\Temp\\" (System/getProperty "java.io.tmpdir")))))