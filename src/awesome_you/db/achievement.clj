(ns awesome-you.db.achievement
  (:require [awesome-you.models.achievement :as m.achievement]
            [schema.core :as s]))

(s/defn upsert! :- m.achievement/Achievement
  [{id :achievement/id :as achievement} :- m.achievement/Achievement
   db :- clojure.lang.Atom]
  (-> db
      (swap! #(assoc-in % [:achievements id] achievement))
      (get-in [:achievements id])))

(s/defn delete! :- m.achievement/Achievement
  [id :- s/Uuid
   db :- clojure.lang.Atom]
  (-> db
      (swap! (fn[m] (update m :achievements #(dissoc % id))))))

(s/defn one :- (s/maybe m.achievement/Achievement)
  [id :- s/Uuid
   db :- clojure.lang.Atom]
  (get-in @db [:achievements id]))

(s/defn all :- (s/maybe m.achievement/Achievement)
  [db :- clojure.lang.Atom]
  (vals (get @db :achievements)))
