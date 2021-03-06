(ns awesome-you.adapters.achievement
  (:require [awesome-you.contracts.in.achievement :as in.achievement]
            [awesome-you.models.achievement :as m.achievement]
            [schema.core :as s]))

(s/defn to-new-internal :- m.achievement/New
  [{:keys [title description date]} :- in.achievement/New]
  (conj {:achievement/title       title
         :achievement/description description}
        (when date {:achievement/date date})))

(s/defn to-internal :- m.achievement/Achievement
  [{:keys [id title description date]} :- in.achievement/Achievement]
  {:achievement/id          id
   :achievement/title       title
   :achievement/description description
   :achievement/date        date})

(s/defn from-internal
  [{id          :achievement/id
    title       :achievement/title
    description :achievement/description
    date        :achievement/date} :- m.achievement/Achievement]
  {:id          id
   :title       title
   :description description
   :date        date})
