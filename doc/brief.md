[back](../README.md)

# The Brief

Over the past 18 months, we've been making moves towards a fully responsive UI and design system. And, as you already know, we're also slowly but surely moving towards a React-like methodology using Clojure, Rum and ClojureScript.

We'd like you to show us how you'd interpret the attached UI design mockup(s), and how you'd go about building it out, using a mix of HTML, CSS and (hopefully) ClojureScript. Ideally, we'd want to see:

1. An HTML / CSS layout of the UI
  * You can use the Sass CSS extension language if you like, but we'd need the compiled CSS file as well, please
  * Ideally, we'd like to see a responsive layout, but you can choose to do this or not depending on what you feel is achievable in the given time
1. A basic database structure for product categories, sub-categories and products
  * This needn't be a full on MySQL DB, you can simply create a JSON file or similar
1. Using the category structure:
  1. populate the main menu items
  1. populate the drop-down menu items (based on their parent category)
  1. The basic taxonomy is:
    * Category 1
    * Sub-category 1
    * Sub-category 2
    * Sub-category 3
      - Product 1
      - Product 2
      - Product 3
    * Sub-category
    * Category 2
    * Category 3
  1. Don't get too hung up on the exact categories and products
1. Using the same category structure, create a predictive / autocomplete search form...
  * wherein users can search for categories, sub-categories or products
  * You can see an example of this on our existing site
1. Create a modal (triggered by pressing the country flag), wherein a user can select their shipping country and which currency they'd like to see prices displayed.
  * When the user selects their country, the corresponding currency should select...
  * ...but the user should also be able to select / override this by selecting the currency of their choice.
  * On form submission, the modal should close and a text block on the page (not shown in mockup, so just pop a div with currency on the page) should change to the corresponding currency.
    * Bonus points if you do some kind of basic exchange rate calculation.
1. Anything else that catches your eye :)
