# personnel

Example app demonstrating Korma, Lobos and macro-defined functions.

## Installation

```bash
git clone git@github.com:jphackworth/personnel.git
cd personnel
lein repl
```

## Usage

In-memory db setup:

```clojure
; in REPL
(use 'personnel.core)
(create-db!)
(init-db! (default-values))
```

**Macro-defined functions**:

```clojure
(employees/create {:name "kermit" :email "kermit@thefrog.com"})
(addresses/create {:location "home" :street "1 Malibu Drive"})

; same functions available with addresses (i.e. addresses/find-or-create ...)

(employees/find-or-create {:name "kermit"})
(employees/find-id 1)
(employees/find-all)
(employees/find-first {:name "kermit"})
(employees/update-id 1 {:name "misspiggy"})
(employees/delete-id 1) 
```

**Macro-defined finder functions**:

```clojure
(employees/find-by-name "bert")
(employees/find-by-email "bert@test.com")
(addresses/find-by-location "office")
```


## Example 

```clojure
$ cd personnel
personnel $ lein repl
Feb 18, 2014 11:30:05 PM com.mchange.v2.log.MLog <clinit>
INFO: MLog clients using java 1.4+ standard logging.
nREPL server started on port 56366 on host 127.0.0.1
REPL-y 0.3.0
Clojure 1.5.1
    Docs: (doc function-name-here)
          (find-doc "part-of-name-here")
  Source: (source function-name-here)
 Javadoc: (javadoc java-object-or-class-here)
    Exit: Control+D or (exit) or (quit)
 Results: Stored in vars *1, *2, *3, an exception in *e
personnel.core=> (use 'personnel.core)
nil
personnel.core=> (create-db!)
nil
add-employees-table
add-addresses-table
nil
personnel.core=> (init-db! (default-values))
<snip init-db spam>
nil
personnel.core=> (employees/find-all)
[{:updated_on nil, :created_on #inst "2014-02-18T15:30:43.281000000-00:00", :email "bert@test.com", :name "bert", :id 1} {:updated_on nil, :created_on #inst "2014-02-18T15:30:43.291000000-00:00", :email "ernie@test.com", :name "ernie", :id 2}]
personnel.core=> (addresses/find-all)
[{:updated_on nil, :created_on #inst "2014-02-18T15:30:43.290000000-00:00", :employee_id 1, :location "office", :street "1 Sesame St", :id 1} {:updated_on nil, :created_on #inst "2014-02-18T15:30:43.291000000-00:00", :employee_id 2, :location "office", :street "1 Sesame St", :id 2}]
personnel.core=> (employees/find-or-create {:name "kermit"})
{:updated_on nil, :created_on #inst "2014-02-18T15:31:30.791000000-00:00", :email nil, :name "kermit", :id 3}
personnel.core=> (employees/find-by-name "kermit")
[{:updated_on nil, :created_on #inst "2014-02-18T15:31:30.791000000-00:00", :email nil, :name "kermit", :id 3}]
personnel.core=>
```

