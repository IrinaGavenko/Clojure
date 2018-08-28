(ns clojure-http.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/add" request (str {:result (->>
                              (vals (:param request))
                              (map (fn [val] (Integer/parseInt val)))
                              (reduce +))}))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))