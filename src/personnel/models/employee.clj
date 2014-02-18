(ns personnel.models.employee
  (:use korma.core
        [korma.db :only (defdb)])
  (:require [personnel.models.schema :refer [db-spec addresses employees]]
            [personnel.models.util :as utils]))

(defdb db db-spec)

(utils/create-model-fns "employees")
(utils/create-model-finder-fns "employees" [:name :email])
