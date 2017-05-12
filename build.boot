(set-env!
  :resource-paths #{"src"}
  :source-paths #{"test" "data"}
  :dependencies '[[org.clojure/clojure "1.9.0-alpha15"]
                  [org.clojure/tools.logging "0.3.1"]
                  [nightlight "1.6.4" :scope "test"]
                  [adzerk/boot-test "1.2.0" :scope "test"]
                  [org.datavec/datavec-api "0.8.0"]
                  [org.nd4j/nd4j-native-platform "0.8.0"]
                  [org.nd4j/nd4j-native "0.8.0"]
                  [org.deeplearning4j/deeplearning4j-nlp "0.8.0"]
                  [org.deeplearning4j/deeplearning4j-core "0.8.0"]
                  [org.slf4j/slf4j-log4j12 "1.7.1"]
                  [log4j/log4j "1.2.17" :exclusions [javax.mail/mail
                                                     javax.jms/jms
                                                     com.sun.jmdk/jmxtools
                                                     com.sun.jmx/jmxri]]])

(require
  '[nightlight.boot :refer [nightlight]]
  'dl4clj-nlp.core
  '[adzerk.boot-test :refer :all])

(deftask night []
  (comp
    (wait)
    (nightlight :port 4000)))
