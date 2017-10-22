(ns yc-app.components
  (:require [rum.core :as rum]))

(defn button [text]
  [:div.paper-button text])

(defn focus-widget [text]
  [:div.focus-widget
   (button text)])

(defn close-sidebar []
  (swap! yc-app.core/app-state assoc :show-sidebar false))

(rum/defc side-menu []
  (let [backdrop-handler (fn [e]
                           (swap! yc-app.core/app-state assoc :show-sidebar false))]
    [:div#side-menu
     [:div.backdrop {:on-click backdrop-handler
                     :hidden (not (:show-sidebar @yc-app.core/app-state))}]
     [:div.contents.flex.vertical {:hidden (not (:show-sidebar @yc-app.core/app-state))}
      [:div#side-menu-header [:div.flex.horizontal [:div.flex "Menu"] [:div.fa.fa-close {:on-click close-sidebar}]]]
      [:div#side-menu-content-wrapper.with-shadow.flex.vertical.scroll 
       (for [it (range 0 40)]
         [:div [:a {:href (str "/menu-" it)} "item " it]])]]]))

(rum/defc menu-button []
  (let [menu-click (fn [e]
                     ;(js/alert "hello")
                     (swap! yc-app.core/app-state assoc :show-sidebar true)
                     (println yc-app.core/app-state))]
    [:button.header-button {:onClick menu-click}]))

(rum/defc help-button []
  [:div.fa.fa-question-circle-o.fa-lg])

(rum/defc search-button []
  [:button {:onClick #(js/alert "Launch Search")} [:i.fa.fa-search.fa-lg]])

(rum/defc widget-yc []
  [:div.allcaps.bold "Yuppiechef"])
