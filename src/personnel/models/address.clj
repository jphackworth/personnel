(ns personnel.models.address
  (:use korma.core
        [korma.db :only (defdb)])
  (:require [personnel.models.schema :refer [db-spec employees addresses]]
            [personnel.models.util :as utils]))

(defdb db db-spec)

(utils/create-model-fns "addresses")
(utils/create-model-finder-fns "addresses" [:location :street])


