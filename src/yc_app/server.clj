(ns yc-app.server
  (:require [hiccup.page :refer :all]
            [bidi.ring :refer (make-handler)]
            [yc-app.api :as api]))

(defn viewport-meta []
  [:meta {:name "viewport"
          :content (clojure.string/join ", " ["width=device-width"
                                              "initial-scale=1"
                                              "minimum-scale=1"
                                              "user-scalable=0"])}])

(defn load-widget [&{:keys [heading sub-heading]}]
  [:div.load-wrapper.flex.vertical
   [:div.flex]
   [:div.load-container.flex.horizontal
    [:div.flex]
    [:div.loader]
    [:div.flex]]
   (if-not nil? heading [:h2 heading])
   (if-not nil? sub-heading [:p sub-heading])
   [:div.flex]])

(defn index-handler [request]
  {:status 200
   :body (html5
          [:head
           [:meta {:charset "UTF-8"}]
           (viewport-meta)
           (include-css "css/style.css" "css/font-awesome.min.css")
           [:link {:rel "icon" :href "img/favicon.ico"}]]
          [:body
           [:div#app
            (load-widget :heading "Loading" :sub-heading "[Preparing the kitchen]")]
           (include-js "js/compiled/yc_app.js")])})


(defn not-found-handler [request]
  ;; The not-found handler needs more logic to return the index handler when it is
  ;; a browser request, and a plain response otherwise. The issue here is to still
  ;; allow a user to refresh the page and end up where they were before, instead of
  ;; now getting a 'plain' 404 error page.
  {:status 404
   :body (html5 [:head
                 [:meta {:charset "UTF-8"}]
                 (viewport-meta)]
                [:body
                 [:h2 "HTTP/404 - Not Found"]
                 [:p "We are not able to handle your request for this location."]
                 [:code [:pre (with-out-str (clojure.pprint/pprint request))]]])})

(def backend-routes
  ["/" [["" index-handler]
        ["api/categories" api/categories]
        ["api/products" api/products]
        ["api/search" api/search]
        [true index-handler]]])

(def handler
  (make-handler backend-routes))
