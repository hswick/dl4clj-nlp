(ns dl4clj-nlp.core-test
  (:require [dl4clj-nlp.core :as nlp]
            [clojure.test :refer :all]
            [clojure.tools.logging :as log]))

(def filepath (nlp/absolute-path "neuromancer.txt"))

(deftest file-io
  (is (= true (.exists (clojure.java.io/file filepath)))))

(def w2v (nlp/word-2-vec "neuromancer.txt"))

(nlp/fit! w2v)

(deftest word-2-vec-test
  (is (= 100 (count (nlp/words-nearest w2v "chiba" 100)))))

(nlp/write-word-vectors w2v "data/neuromancer.csv")

(def w2v2 (nlp/load-w2v-from-txt (clojure.java.io/file "neuromancer.csv")))

(deftest serialization-test
  (is (= true (.exists (clojure.java.io/file "data/neuromancer.csv"))))
  (is (= org.deeplearning4j.models.word2vec.Word2Vec (class w2v2)))
  (is (= 100 (count (nlp/words-nearest w2v "chiba" 100)))))
  
