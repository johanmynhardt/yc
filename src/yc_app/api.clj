(ns yc-app.api
  (:require [hiccup.page :refer :all]))

(defn categories [request]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (slurp "src/yc_app/json/categorydb.json")})

(defn products [request]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (slurp "src/yc_app/json/productdb.json")})

(defn search [request]
  {:status 200
   :body (str "Search Results for query: " (:query-string request))})

(defn handler [request]
  {:status 200
   :body (html5
          [:div "request: " [:code (:uri request)]]
          [:code [:pre  (with-out-str (clojure.pprint/pprint request))]])})
