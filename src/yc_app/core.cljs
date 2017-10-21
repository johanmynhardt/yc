(ns yc-app.core
    (:require [rum.core :as rum]))

(enable-console-print!)

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(rum/defc menu-button []
  [:button.header-button {:onClick #(js/console.log "echo")}])

(rum/defc help-button []
  [:div.fa.fa-question-circle-o.fa-lg])

(rum/defc search-button []
  [:button {:onClick #(js/alert "Launch Search")} [:i.fa.fa-search.fa-lg]])

(rum/defc widget-yc []
  [:div.allcaps.bold "Yuppiechef"])

(rum/defc yc []
  [:div
   [:header#main-menu
    (menu-button)
    (widget-yc)
    [:div.inline-group
     [:div "Shop"]
     [:div "Registry"]
     [:div "Gifting"]
     [:div "Subscriptions"]
     [:div "Trade"]]
    [:div.flex]
    (comment  [:div.inline-group
               (help-button)])
    (search-button)
    [:div]
    [:div]]
   [:div#content-wrapper
    [:div "Page"]]])


(rum/mount (yc)
           (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
