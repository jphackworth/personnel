(ns personnel.core
  (:require [personnel.models.schema :as schema]       
            [personnel.models.employee :as employees]
            [personnel.models.address :as addresses])
  (:gen-class))


(defn default-values [] 
  {:employees 
   [{:name "bert"
    :email "bert@test.com"
    :addresses [{:location "office" :street "1 Sesame St"}]}
    {:name "ernie"
     :email "ernie@test.com"
     :addresses [{:location "office" :street "1 Sesame St"}]}]})

(defn create-db! []
  (if-not (schema/actualized?)
    (schema/actualize)))

(defn init-db!  
  "Populate the database with initial values"
  [m]
  (if-let [employees (get m :employees)]
    (doseq [employee employees]
      (let [id (get (employees/create (dissoc employee :addresses)) :id)]
        (if-let [addresses (get employee :addresses)]
          (doseq [address addresses]
            (addresses/create (assoc address :employee_id id))))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (create-db!)
  (init-db! (default-values))
  )
