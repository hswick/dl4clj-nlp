(def project 'hswick/dl4clj-nlp)
(def version "0.0.1")

(set-env!
  :resource-paths #{"src"}
  :source-paths #{"test" "data"}
  :dependencies '[[org.clojure/clojure "1.9.0-alpha15"]
                  [org.clojure/tools.logging "0.3.1"]
                  [nightlight "1.6.4" :scope "test"]
                  [adzerk/boot-test "1.2.0" :scope "test"]
                  [adzerk/bootlaces "0.1.13" :scope "test"]
                  [org.datavec/datavec-api "0.8.0"]
                  [org.nd4j/nd4j-native-platform "0.8.0"]
                  [org.nd4j/nd4j-native "0.8.0"]
                  [org.deeplearning4j/deeplearning4j-nlp "0.8.0"]
                  [org.deeplearning4j/deeplearning4j-core "0.8.0"]
                  [org.apache.lucene/lucene-snowball "3.0.3"]])

(require
  '[nightlight.boot :refer [nightlight]]
  'dl4clj-nlp.core
  '[adzerk.boot-test :refer :all]
  '[adzerk.bootlaces :refer :all])

(bootlaces! version)

(task-options! pom {:project project      
                    :version version     
                    :description "Clojure library for word embedding using deeplearning4j"      
                    :url         "https://github.com/hswick/dl4clj-nlp"      
                    :scm         {:url "https://github.com/hswick/dl4clj-nlp"}      
                    :license     {"Eclipse Public License"                    
                                  "http://www.eclipse.org/legal/epl-v10.html"}})

(deftask night []
  (comp
    (wait)
    (nightlight :port 4000)))

(deftask build
  "Build and install the project locally"
  []
  (comp (pom) (jar) (install) (target)))
