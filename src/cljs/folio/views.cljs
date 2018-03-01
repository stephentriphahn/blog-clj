(ns folio.views
  (:require [re-frame.core :as re-frame]
            [folio.subs :as subs]
            ))


;; POSTS
(defn post [p]
  [:div.post-wrapper
    [:h1 "Subject"]
    [:p (:subject p)]
    [:h1 "body"]
    [:p (:body p)]])

(defn posts-panel
  []
  (let [posts (re-frame/subscribe [::subs/posts])]
    [:div.posts-panel
     (for [p @posts]
       ^{:key (:timestamp p)}[post p])]))

;; about

(defn create-panel []
  [:div "This is the Create Page."
   [:div [:a {:href "#/"} "go to Posts Page"]]])


;; main

(defn- panels [panel-name]
  (case panel-name
    :posts-panel [posts-panel]
    :create-panel [create-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [show-panel @active-panel]))
