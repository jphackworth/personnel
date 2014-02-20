(ns personnel.models.employee
  (:use korma.core
        [korma.db :only (defdb)])
  (:require [personnel.models.schema :refer [db-spec addresses employees]]
            [personnel.models.helpers :refer [create-model-fns create-model-finder-fns]]))

(defdb db db-spec)

(defn find-all [& [attr]]
  (if (map? attr)
    (select employees (with addresses) (where attr))
    (select employees (with addresses))))

(create-model-fns *ns* employees)
(create-model-finder-fns *ns* [:name :email])
