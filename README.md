# Expedia Demo App.
This android app parses and display information from hotel_search_results.json. There are 100 hotel results in the file. 

The app provides list representation and map representation of the results from json. 

User has ability to switch between the map and list views as well as sort the results by price, guest rating, or hotel name.

In Map View, user can zoom into view all the markers and tap on the markers to view the hotel name. Use back button on navigation bar to go back to list view of the results. 

# Improvements
. For the demo, we are using the static json file to populate the results. However on real time scenario, we can use AysnTask to perform parsing of json. 

. We can also add option to filter the recycler view list based on parameter.

. Show details view upon performing a click on a item in recycler view.
