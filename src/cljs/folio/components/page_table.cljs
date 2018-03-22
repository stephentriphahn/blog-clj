(ns folio.components.page-table
  (:require [reagent.core :as reagent]))

(defn forward
  [{:keys [curr next prev] :as pgs}]
  (-> pgs
      (update :prev conj (vec curr))
      (assoc :curr (first next))
      (assoc :next (rest next))))

(defn backward
  [{:keys [curr next prev] :as pgs}]
  (-> pgs
      (assoc :curr (peek (vec prev)))
      (update :next conj curr)
      (assoc :prev (butlast next))))

(defn pages
  [[h & t]]
  {:curr h
   :next t
   :prev []})

(defn page-navigator
  [page-index]
  [:div
   [:span {:on-click #(swap! page-index dec)} "PREV"]
   [:span "  " @page-index "  "]
   [:span {:on-click #(swap! page-index inc)} "NEXT"]])

(defn paginated-table
  [data]
  (let [last-page (dec (count data))
        page-index (reagent/atom 0)]
    (fn [data]
      [:div
       (for [i (get data @page-index)]
        ^{:key i}[:div (:val i)])
       [page-navigator page-index]])))


