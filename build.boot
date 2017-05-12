(set-env!
  :resource-paths #{"src"}
  :dependencies '[[org.clojure/clojure "1.9.0-alpha15"]
                  [nightlight "1.6.4" :scope "test"]
                  [org.datavec/datavec-api "0.7.0"]
                  [org.deeplearning4j/deeplearning4j-nlp "0.7.0"]
                  [org.deeplearning4j/deeplearning4j-core "0.7.0"]
                  [org.nd4j/nd4j-native "0.7.0"]
                  [org.clojure/data.json "0.2.6"]
                  [org.slf4j/slf4j-log4j12 "1.7.1"]
                  [log4j/log4j "1.2.17" :exclusions [javax.mail/mail
                                                     javax.jms/jms
                                                     com.sun.jmdk/jmxtools
                                                     com.sun.jmx/jmxri]]])

(require
  '[nightlight.boot :refer [nightlight]]
  'dl4clj-nlp.core)

(deftask night []
  (comp
    (wait)
    (nightlight :port 4000)))
