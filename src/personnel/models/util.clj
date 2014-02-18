(ns personnel.models.util 
  (:use korma.core
    [korma.db :only (defdb)]))

; Inspired by https://github.com/cldwalker/datomico
; create-model-fns taken from: https://github.com/cldwalker/datomico/blob/master/src/datomico/core.clj
; create-model-fn taken from: https://github.com/cldwalker/datomico/blob/master/src/datomico/model.clj
;
; create-model-finder-fns and create-model-finder-fn based on above 

; Common functionality across database models

(defn find-id 
  [nsp id] 
  (first (select (symbol nsp) (where {:id id}))))

(defn find-all
  "Queries with given map of attribute names to values and returns a vector of maps with no namespace."
  [nsp]
  (select nsp))

(defn find-first
  "Queries with given map of attribute names to values and returns a vector of maps with no namespace."
  [nsp attr]
  (first (select nsp (where attr))))

(defn find-by [nsp k v]
  (select nsp (where {k v})))

(defn delete-id [nsp id] 
  (when-let [ent (find-id id)]
    (delete nsp (where ent))))

(defn create [nsp attr]
  (let [response (insert nsp (values attr))]
    (find-id nsp (first (vals response)))))

(defn find-or-create [nsp attr] 
  (or (find-first nsp attr) (create nsp attr)))

(defn update-id 
  [nsp id attr] 
  (update nsp (set-fields attr) (where {:id id})))
; 

(defmacro create-model-fn
  "Creates a local function that wraps a fn with a keyword namespace (model scope)."
  [fn-name nsp]
  `(do
    (def ~(symbol (name fn-name))
      (partial ~(deref (resolve (symbol "personnel.models.util" (name fn-name)))) ~nsp))))

(defmacro create-model-fns
  "Creates model fns that are scoped to the given model (keyword namespace)."
[nsp & [fns]]
(let [fns (if (nil? fns) [:find-id :find-all :find-first :create :find-or-create :update-id :delete-id] fns)]
  `(do ~@(map (fn [name] `(create-model-fn ~name ~nsp)) fns))))

(defmacro create-model-finder-fn
  "Creates a local function that wraps a fn with a keyword namespace (model scope)."
  [fn-name nsp]
  `(do
    (def ~(symbol (str "find-by-" (name fn-name)))
      (partial ~(deref (resolve (symbol "personnel.models.util" "find-by"))) ~nsp ~fn-name))))

(defmacro create-model-finder-fns
  "Creates model finder fns that are scoped to the given model (keyword
    namespace)."
[nsp fns]
`(do ~@(map (fn [name] `(create-model-finder-fn ~name ~nsp)) fns)))
