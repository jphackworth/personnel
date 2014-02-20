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
(init-db!)
```

**Macro-defined functions**:

```clojure
(employees/create {:name "kermit" :email "kermit@thefrog.com"})
(addresses/create {:location "home" :street "1 Malibu Drive"})
(employees/all)

; same functions available with addresses (i.e. addresses/find-or-create ...)

(employees/find-or-create {:name "kermit"})
(employees/find-id 1)
(employees/find-all {:column value})
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
