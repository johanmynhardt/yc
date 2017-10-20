(ns yc-app.server
  (:require [hiccup.page :refer :all]))

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

(defn handler [request]
  {:status 200
   :body (html5 [:head
                 [:meta {:charset "UTF-8"}]
                 [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
                 (include-css "css/style.css")
                 [:link {:rel "icon" :href "img/favicon.ico"}]]
                [:body
                 [:div#app
                  (load-widget :heading "Loading" :sub-heading "[Preparing the kitchen]")]
                 (include-js "js/compiled/yc_app.js")])})
