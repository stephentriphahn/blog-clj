(ns folio.handler
  (:require [compojure.core :refer [GET POST defroutes context]]
            [compojure.route :refer [resources]]
            [ring.util.response :refer :all]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [ring.middleware.reload :refer [wrap-reload]]
            [folio.core :as core]))

(defroutes
  routes
  (GET "/" [] (resource-response "index.html" {:root "public"}))

  (context "/api/posts" []
    (GET "/" []
      (core/get-posts))

    (POST "/" req
      (core/add-post (:body req))))

  (resources "/"))


(def handler
  (-> #'routes
      wrap-json-response
      wrap-reload
      wrap-json-body))
