(ns folio.core
  (:require [folio.posts  :as posts]
            [ring.util.response :refer :all]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; external API with Ring responses

(defn get-posts
  "Gets all of the posts on the front page."
  []
  (response (posts/get-all)))

(defn add-post
  [post]
  (created "api/posts/<id>" (posts/add post)))
