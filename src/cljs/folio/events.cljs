(ns folio.events
  (:require [re-frame.core :as re-frame]
            [folio.db :as db]
            [ajax.core :as ajax]))

(re-frame/reg-event-db
  ::failed-response
  (fn [db [_ res]]
    (assoc db :error-message res)))

(re-frame/reg-event-db
  ::set-posts-response
  (fn [db [_ resp]]
    (-> db
        (assoc :posts resp)
        (assoc :loading? false))))

(re-frame/reg-event-fx
  ::get-all-posts
  (fn [{:keys [db]} _]
    {:db (assoc db :loading? true)
    :http-xhrio {:method          :get
                 :uri             "http://localhost:3000/api/posts"
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success [::set-posts-response]
                 :on-failure [::failed-response]}}))

(re-frame/reg-event-fx
 ::initialize-db
 (fn  [{:keys [db]} _]
   {:db db/default-db
   :dispatch [::get-all-posts]}))


(re-frame/reg-event-db
 ::set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))
