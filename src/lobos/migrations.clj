(ns lobos.migrations
  (:refer-clojure 
    :exclude [alter drop bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema config helpers)))

(defmigration add-employees-table
  (up [] (create
    (tbl :employees
      (varchar :name :unique)
      (varchar :email 50 :unique))))
  (down [] (drop (table :employees))))

(defmigration add-addresses-table
  (up [] (create 
    (tbl :addresses 
      (varchar :street 50)
      (varchar :location 10)
      (refer-to :employees))))
  (down [] (drop (table :addresses))))






