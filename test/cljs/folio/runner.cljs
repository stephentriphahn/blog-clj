(ns folio.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [folio.core-test]))

(doo-tests 'folio.core-test)
