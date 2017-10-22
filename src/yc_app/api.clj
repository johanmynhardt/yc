(ns yc-app.api
  (:require [hiccup.page :refer :all]))

(defn categories [request]
  {:status 200
   :body "Categories..."})

(defn products [request]
  {:status 200
   :body "Products..."})

(defn search [request]
  {:status 200
   :body (str "Search Results for query: " (:query-string request))})

(defn handler [request]
  {:status 200
   :body (html5
          [:div "request: " [:code (:uri request)]]
          [:code [:pre  (with-out-str (clojure.pprint/pprint request))]])})
