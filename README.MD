# dl4clj-nlp

Clojure project meant to do...

# Usage

Run `boot night` to startup nightlight and begin editing your project in a browser.

(:require '[dl4clj-nlp.core :as nlp])

(def w2v (nlp/word-2-vec "path/to/text-file"))

(nlp/fit! w2v)

## License

Copyright © 2017 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
