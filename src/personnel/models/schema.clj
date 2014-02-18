(ns personnel.models.schema
  (:use [lobos.core :only (defcommand migrate)]
        korma.core)
  (:require [korma.db :refer :all]
            [lobos.migration :as lm]))

(def db-spec (h2 {:subname "mem:personnel_test_db"}))

(declare employees addresses)

(defentity employees
  (has-many addresses {:fk :employee_id}))

(defentity addresses
  (belongs-to employees {:fk :employee_id}))

(defcommand pending-migrations []
  (println sname)
  (lm/pending-migrations db-spec sname))

(defn actualized?
  "checks if there are no pending migrations"
  []
  (empty? (pending-migrations)))

(def actualize migrate)

