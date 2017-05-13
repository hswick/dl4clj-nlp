(ns dl4clj-nlp.core-test
  (:require [dl4clj-nlp.core :as nlp]
            [dl4clj-nlp.sentence-iterator :as iter]
            [dl4clj-nlp.tokenization :as token]
            [clojure.test :refer :all]
            [clojure.tools.logging :as log]))

(def filepath (nlp/absolute-path "neuromancer.txt"))

(deftest file-io
  (is (= true (.exists (clojure.java.io/file filepath)))))

(def w2v (nlp/word-2-vec "neuromancer.txt"))

(nlp/fit! w2v)

(deftest word-2-vec-test
  (is (= 100 (count (nlp/words-nearest w2v "chiba" 100))))
  (is (= true (nlp/has-word? w2v "chiba")))
  (is (= true (not (nil? (nlp/get-word-vector w2v "chiba")))))
  (is (= java.lang.Double (class (nlp/similarity w2v "chiba" "city")))))

(nlp/write-word-vectors w2v "data/neuromancer.csv")

(def w2v2 (nlp/read-word-vectors (clojure.java.io/file "data/neuromancer.csv")))

(deftest serialization-test
  (is (= true (.exists (clojure.java.io/file "data/neuromancer.csv"))))
  (is (= org.deeplearning4j.models.word2vec.Word2Vec (class w2v2)))
  (is (= 100 (count (nlp/words-nearest w2v "chiba" 100)))))

(def stopwords (nlp/stop-words))

(deftest stopwords-test
  (is (< 0 (.size stopwords)))
  (is (= true (not (nil? stopwords)))))

(def w2v3 (nlp/word-2-vec 
            (iter/default-iterator (nlp/absolute-path "neuromancer.txt"))
            (token/default-tokenizer-factory (token/common-stemmer-preprocessor))
            {:min-word-frequency 6
             :stopwords (nlp/stop-words)
             :window-size 10
             :layer-size 150}))
              
(nlp/fit! w2v3)

(deftest word-2-vec3-test
  (is (= 100 (count (nlp/words-nearest w2v3 "chiba" 100))))
  (is (= true (nlp/has-word? w2v3 "chiba")))
  (is (= true (not (nil? (nlp/get-word-vector w2v3 "chiba")))))
  (is (= java.lang.Double (class (nlp/similarity w2v3 "chiba" "city")))))
