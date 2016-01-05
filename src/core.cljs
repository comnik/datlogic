(ns datlogic.core
    (:require
        [datascript.core :as d]))

(defn fact-extends [child parent]
    "Returns the datom representation of an inheritance relation between the given types."
    [:db/add [:name child] :extends [:name parent]])

(defn gen-db []
    "Returns a database populated with some symbols for testing."
    (let [schema {:name       {:db/unique :db.unique/identity}
                  :subtype-of {:db/valueType :db.type/ref}
                  :extends    {:db/valueType :db.type/ref} }]
        (d/db-with (d/empty-db schema)
                   [[:db/add -1 :name "void"]
                    [:db/add -2 :name "Object"]
                    [:db/add -3 :name "Shape"]
                    [:db/add -4 :name "Circle"]
                    [:db/add -5 :name "Rectangle"]
                    [:db/add -6 :name "Square"]

                    (fact-extends "Circle" "Shape")
                    (fact-extends "Rectangle" "Shape")
                    (fact-extends "Square" "Rectangle")])))

(def not-nil? (complement nil?))


;; Ex 1 - Subtyping rules

(def typing-rules
    '[  [(subtype ?t1 ?t2) [?t2 :name "Object"]]
        [(subtype ?t1 ?t2) [?t1 :name ?n] [?t2 :name ?n]]
        [(subtype ?t1 ?t2) [?t1 :extends ?t2]]
        [(subtype ?t1 ?t2) [?t1 :extends ?anything] (subtype ?anything ?t2)] ])

(defn is-subtype? [db tname1 tname2]
    "Checks wether tname1 is a subtype of tname2, following a set of typing rules."
    (let [result (d/q '[:find [?t1 ?t2]
                        :in $ % ?tname1 ?tname2
                        :where [?t1 :name ?tname1]
                               [?t2 :name ?tname2]
                               (subtype ?t1 ?t2) ] db typing-rules tname1 tname2)]
        (not-nil? result)))


;; Ex 2 - Consistency checks

(defn check-void [db]
    "Void should not be the subtype of anything."
    (nil? (d/q '[   :find [?t]
                    :in $ %
                    :where  [?void :name "void"]
                            [?t :name _]
                            (!= ?t ?void)
                            (subtype ?void ?t) ] db typing-rules)))


;; Tests

(defn test-subtype [db t1 t2]
    "Given two type names, will print a human readable string on their relation."
    (let [relation (if (is-subtype? db t1 t2) "is a subtype of" "is NOT a subtype of")]
        (println "\t" t1 relation t2)))

(let [db (gen-db)
      type-names ["Object" "Shape" "Circle" "Rectangle" "Square"]]
    (println "[TYPE CHECKS]")
    (dorun
        (for [t2 type-names t1 type-names] (test-subtype db t1 t2)))
    (println "[CONSISTENCY CHECKS]")
    (println "\t" "Void check: " (check-void db)))