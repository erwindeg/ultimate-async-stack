# Assignments
The solution can be found by checking out the solution branch.
## Build the movies homepage
1. Add a movies [service](https://angular.io/guide/dependency-injection) to the app.
1. Write a method in this service to get the movies from the backend. Use the Angular [HTTP client](https://angular.io/guide/http) for this.
1. In the `movies.component.ts` use the service you created to call the webservice and display the movies that were retrieved. To do so you need to [subscribe](https://angular.io/guide/http) to the result of the service.

## Build the search page using websockets
1. Create the new search component. It should have a property `movies` which is an array of the type `Movie`.
1. We need an url for our new page. We'll do this next. Add the search page to the routing config in the `app-routing.module.ts`.
1. Now that we have an url, we need an easy way to navigate to our search page. Add a [link](https://angular.io/guide/router) to the navbar to go to the search page.
1. We should now be able to see our new page after we click the link in the navbar. Guess what... It's not doing anything yet. Let's change that. Add a [form input](https://angular.io/tutorial/toh-pt1#two-way-binding) to enter the search term.
1. Great! We have a text input now. Now we need to do some setup. We want to be able to send requests when we type in the search box. To do so we need to listen to changes to the search box. The first step is to create a new [Subject](https://angular.io/tutorial/toh-pt6#add-the-ability-to-search-by-name) in the `search.component.ts` called `searchTerm$`.
1. We now have a `Subject` which we can subscribe to. Eventually we can use this to trigger our search requests. Next we need to tell the `searchTerm$` Subject whenever the searchbox has changed. We can achieve this by adding a `ngModelChange` to the input field in the view and send the value in the `searchTerm$` Subject. Use the `.next()` method with the search term to do this. IMPORTANT: the `ngModelChange` only works if the input also has an `ngModel` attribute.
1. The search term is now sent in the subject each time the input changes! The next step is to subscribe to our Subject. We can do this in the `ngOnInit` hook. We will use this subscription later to send the search term to the websocket. 
1. Now that we know when the search box has changed, we need to pass the searchterm into the websocket connection. This starts with the actual connection. Write a method in the search component which sets up an Observable from the websocket connection. Use the [RxJS API](https://stackoverflow.com/questions/44060315/reconnecting-a-websocket-in-angular-and-rxjs/44067972#44067972) to get the websocket connection as a Observable.
1. We now have a websocket connection. Next we need to start the connection when the searchterm changes. Subscribe to the `searchTerm$` Subject in the `ngOnInit` method of the `search.component.ts`. Make sure to call the method we wrote in step 7 with the search term.
1. Now that we trigger our websocket connection when the searchterm changes we need to pass data to it. Use `.next()` to do so. The websocket only accepts strings, so don't forget to use `JSON.stringify` to convert the data to the correct format. The data we need to send is an object with the properties `action` and `body`. The `action` should be `search` and the body should be the searchterm.
1. We now correctly send data to the backend. We do need to still handle the results we get back. In the method where we set up our websocket connection we need to `subscribe` to the socket Observable. Whenever we receive data from the webservice we should push this in the `movies` array.
1. We need to display the movies on our search page. See `movies.component.html` for how this works.
1. Awesome! We now have results. There are still a few issues though... Our results are added to all the previous results... We can solve this by clearing the `movies` array whenever the websocket connection is opened and called with a new searchterm.
1. The first message from the websocket is a connection ok message. Add a `.filter()` to the socket subscription to only show websocket messages which contain movie data.
1. Another issue we currently have is that we search even when only one character is entered. We can make this more efficient by restricting our search. Add a `.filter()` to the subscription on `searchTerm$` to only start our search when three or more characters are entered.
1. Looking good. We can improve our search even more though! Currently we send our search request instantly when the `searchTerm$` is changed. If we add a small delay to this, we can account for typos etc. and only send requests we actually want to make. Add a 300 millisecond delay to the search by adding `.debounceTime(300)` to the subscription on `searchTerm$`. 
1. Imagine we have typed 'the' in the searchbox. If we then type another character and use backspace to delete it we send a new request with the same searchterm as before. We made two request with 'the' as the searchterm! RxJS actually offers an easy way to only execute something when it is unique. By adding `.distinctUntilChanged()` to the `searchTerm$` subscription we only send the request for 'the' once!
1. All done! Now it is time to enjoy the coolness of your async application!

## Bonus assignments
1. Build another search page which works with REST requests.
2. Add a movie detail page, showing different aspect of the movie than the overview.
