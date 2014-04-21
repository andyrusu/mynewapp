(defproject mynewapp "0.0.1-SNAPSHOT"
  :description "Clojure app that sets up new web application virtual hosts."
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [seesaw "1.4.4"]
                 [me.raynes/fs "1.4.4"]]
  :javac-options ["-target" "1.6" "-source" "1.6" "-Xlint:-options"]
  :aot [mynewapp.core]
  :main mynewapp.core)