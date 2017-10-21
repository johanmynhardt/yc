(ns yc-app.pages
  (:require [yc-app.navigation :refer [route-for]]
            [yc-app.components :as ycc]))

(defn home []
  [:div
   (ycc/focus-widget "Some text")
   [:br]
   [:a {:href "/does-not-exist"} "/does-not-exist"]])

;;-------------------------------------------------------
(defn not-found []
  [:div
   [:h2 "404 - Not found"]
   [:p
    "No handler for route "
    [:code  "[" (.. js/window -location -pathname) "]"]
    " was found :("]
   [:a {:href (route-for :home)} "Home"]])
