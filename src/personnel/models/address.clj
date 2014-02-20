(ns personnel.models.address
  (:use korma.core
        [korma.db :only (defdb)])
  (:require [personnel.models.schema :refer [db-spec employees addresses]]
            [personnel.models.helpers :refer [create-model-fns create-model-finder-fns]]))

(defdb db db-spec)

(defn find-all [& [attr]]
  (if (map? attr)
    (select addresses (with employees) (where attr))
    (select addresses (with employees))))

(create-model-fns *ns* addresses)
(create-model-finder-fns *ns* [:location :street])


