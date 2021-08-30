<h><b>Gif image search using Giphy</b></h>

 The app provides the user to search gifs using Giphy API.

Supported OS: Android 6 (API 23) - Android 11 (API 30)

Design Consideration (MVVM + Coroutine + Paging)

The code consist of 5 vital elements:

1st - View - Activity and Fragment Navigation. It handles the UI interactions.
2nd - ViewModel - Manages View interaction and data flow.
3rd - Model - Data content
4th - Paging Source - Fetches data from API and emits a stream of data
5th - Test Cases - Mockito Test covers the ViewModel and Paging Source and Espresso used for testing the UI.

Application Design

Giphy Search  

- User search gif
- Gif list shows in gridView
- User can click any item to see a detailed view 

Giphy Details 

- Gif image shows fullscreen with title




