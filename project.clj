(defproject datlogic "0.0.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [datascript "0.13.3"]]

  :plugins [[lein-cljsbuild "1.1.2"]
            [lein-figwheel "0.5.0-2"]]
  
  :cljsbuild {
    :builds [{
      :id "dev"  
      :figwheel true
      :source-paths ["src"]
      :compiler { 
        :main "datlogic.core"
        :output-to "out/main.js"
        :output-dir "out/"}}]})