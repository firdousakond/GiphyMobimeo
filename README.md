<pre>
<span style="font-family:Arial,Helvetica,sans-serif"><span style="font-size:18px"><strong>GIF image search using Giphy API</strong></span></span>

 <span style="font-family:Arial,Helvetica,sans-serif"><span style="font-size:12px">The app provides the user to search gifs using Giphy API.</span></span>

<strong><span style="font-size:12px"><span style="font-family:Arial,Helvetica,sans-serif">Supported OS: Android 6 (API 23) - Android 11 (API 30)</span></span></strong>

<span style="font-family:Arial,Helvetica,sans-serif"><strong><span style="font-size:14px">Design Consideration (MVVM + Coroutine + Paging)
</span></strong></span>
<span style="font-family:Arial,Helvetica,sans-serif"><span style="font-size:12px"><strong>The code consist of 5 vital elements:</strong></span></span>

<span style="font-size:12px">1st - <strong>View</strong> - Activity and Fragment Navigation. It handles the UI interactions.
2nd - <strong>ViewModel</strong> - Manages View interaction and data flow.
3rd - <strong>Model</strong> - Data content
4th - <strong>Paging Source</strong> - Fetches data from API and emits a stream of data
5th - <strong>Test Cases</strong> - Mockito Test covers the ViewModel and Paging Source and Espresso used for testing the UI.</span>

<strong><span style="font-size:14px"><span style="font-family:Arial,Helvetica,sans-serif">Application Design</span></span></strong>

<span style="font-family:Arial,Helvetica,sans-serif"><span style="font-size:12px"><strong>Giphy Search</strong></span></span>  

<span style="font-family:Arial,Helvetica,sans-serif"><span style="font-size:12px">- User search gif
- Gif list shows in gridView
- User can click any item to see a detailed view</span></span> 

<span style="font-size:12px"><span style="font-family:Arial,Helvetica,sans-serif"><strong>Giphy Details </strong></span></span>

<span style="font-family:Arial,Helvetica,sans-serif"><span style="font-size:12px">- Gif image shows fullscreen with title</span></span>




</pre>
