(ns personnel.models.helpers 
  (:use korma.core
    [korma.db :only (defdb)]))

; Inspired by https://github.com/cldwalker/datomico
; create-model-fns taken from: https://github.com/cldwalker/datomico/blob/master/src/datomico/core.clj
; create-model-fn taken from: https://github.com/cldwalker/datomico/blob/master/src/datomico/model.clj

(defn find-first
  "Queries with given map of attribute names to values and returns a vector of maps with no namespace."
  [nsp entity attr]
  (first ((partial (deref (resolve (symbol (str nsp) "find-all"))) attr))))

(defn find-id 
  [nsp entity id] 
  (find-first nsp entity {:id id}))

(defn find-by [nsp k v]
   ((partial (deref (resolve (symbol (str nsp) "find-all"))) {k v})))

(defn all [nsp entity] 
  ((partial (deref (resolve (symbol (str nsp) "find-all"))))))
 
(defn find-by! [nsp k v]
  (first (find-by nsp k v)))

(defn delete-id [nsp entity id] 
  (when-let [ent (find-id id)]
    (delete entity (where ent))))

(defn delete-all [nsp entity & [attr]]
  (if (map? attr) 
    (delete entity (where attr))
    (delete entity)))

(defn create [nsp entity attr]
  (let [response (insert entity (values attr))]
    (find-id nsp entity (first (vals response)))))

(defn find-or-create [nsp entity attr] 
  (or (find-first nsp entity attr) (create nsp entity attr)))

(defn update-id 
  [nsp entity id attr] 
  (update entity (set-fields attr) (where {:id id})))

(defmacro create-model-fn
  "Creates a local function that wraps a fn with a keyword namespace (model scope)."
  [fn-name nsp entity]
  `(do
    (def ~(symbol (name fn-name))
      (partial ~(deref (resolve (symbol "personnel.models.helpers" (name fn-name)))) ~nsp ~entity))))

(defmacro create-model-fns
  "Creates model fns that are scoped to the given model (keyword namespace)."
[nsp entity]
(let [fns [:find-id :find-or-create :find-first :create :update-id :delete-id :all]]
  `(do ~@(map (fn [name] `(create-model-fn ~name ~nsp ~entity)) fns))
   ))

(defmacro create-model-finder-fn
  "Creates a local function that wraps a fn with a keyword namespace (model scope)."
  [nsp fn-name]
  `(do
    (def ~(symbol (str "find-by-" (name fn-name)))
      (partial ~(deref (resolve (symbol "personnel.models.helpers" "find-by"))) ~nsp ~fn-name))
    (def ~(symbol (str "find-by-" (name fn-name) "!"))
      (partial ~(deref (resolve (symbol "personnel.models.helpers" "find-by!"))) ~nsp ~fn-name)) 
    ))

(defmacro create-model-finder-fns
  "Creates model finder fns that are scoped to the given model (keyword
    namespace)."
[nsp fns]
`(do ~@(map (fn [name] `(create-model-finder-fn ~nsp ~name)) fns)))

