(ns yc-app.components
  (:require [rum.core :as rum]
            [accountant.core :as accountant]))

;; ------------- SIDE MENU ------------------------
(def action-items
  [{:text "Shipping to" :icon-div ':div.fa.fa-flag.fa-fw} ; TODO: shipping dialog + currency converter
   {:text "Need Help?"  :icon-div ':div.fa.fa-question-circle.fa-fw}
   {:text "Shop"        :icon-div ':div.fa.fa-shopping-cart.fa-fw}
   {:text "Registry"    :icon-div ':div.fa.fa-heart.fa-fw}
   {:text "Gifting"     :icon-div ':div.fa.fa-gift.fa-fw}
   {:text "Subscriptions" :icon-div ':div.fa.fa-refresh.fa-fw}
   {:text "Trade"       :icon-div ':div.fa.fa-briefcase.fa-fw}
   ])

(defn button [text]
  [:div.light-button text])

(defn focus-widget [text]
  [:div.focus-widget
   (button text)])

(defn open-sidebar []
  (swap! yc-app.core/app-state assoc :show-sidebar true))

(defn close-sidebar []
  (swap! yc-app.core/app-state assoc :show-sidebar false))

(rum/defc side-menu []
  [:div#side-menu
   [:div.backdrop {:on-click close-sidebar
                   :hidden (not (:show-sidebar @yc-app.core/app-state))}]
   [:div.contents.flex.vertical {:hidden (not (:show-sidebar @yc-app.core/app-state))}
    [:div#side-menu-header
     [:div.flex.horizontal
      [:div.flex [:span.person-badge] [:span.flex] [:div.light-button "Account"] [:span.flex]]
      [:div.fa.fa-close {:on-click close-sidebar}]]
     [:h3 "Anonymous"]
     [:p "Public User"]]
    [:div#side-menu-content-wrapper.with-shadow.flex.vertical.scroll
     (for [item action-items]
       [:div {:on-click (fn [e] (close-sidebar) (accountant/navigate! (str "/action/" (:text item))))}
        [:a.flex.horizontal 
         [(:icon-div item)]
         [:div.margin-left (:text item)]]])
     
     [:span.divider]
     [:h3.margin-left.allcaps "Shop"]
     [:span.divider]

     ;; TODO: populate categories
     (for [item (:top-menu-items @yc-app.core/app-state)]
       [:div [:a {:href (str "/menu-" (:label item))} (:label item)]])]]])

(rum/defc menu-button []
  [:button.header-button {:on-click open-sidebar}])

(rum/defc help-button []
  [:div.fa.fa-question-circle-o.fa-lg])

(rum/defc shop-button []
  [:div.fa.fa-shopping-cart.fa-fw])

(rum/defc search-button []
  [:button {:on-click #(js/alert "Launch Search")} [:i.fa.fa-search.fa-lg]])

(rum/defc widget-yc []
  [:div.allcaps.bold.padding-left "Yuppiechef"])

(rum/defc user-button []
  [:div.mw-640 
   [:div.fa.fa-user.fa-fw {:on-click open-sidebar}]]
  )

(rum/defc top-menu []
  [:nav#top-menu
   [:ul
    (for [item (:top-menu-items @yc-app.core/app-state)]
      [:li (str (:label item))])]])

(rum/defc search-dialog []
  [:div#search-dialog])
