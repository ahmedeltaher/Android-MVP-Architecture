# What is MVP ? 

 - **View :** is a layer that displays data and reacts to user actions. On
   Android, this could be an Activity, a Fragment, an android.view.View
   or a Dialog.
 - **Model :** is a data access layer such as database API or remote server API.
 - **Presenter:** is a layer that provides View with data from Model. Presenter also handles background tasks.


----------

On Android MVP is a way to separate background tasks from activities/views/fragments to make them independent of most lifecycle-related events. This way an application becomes simpler, overall application reliability increases up to 10 times, application code becomes shorter, code maintainability becomes better and developer's life becomes happier.


-----------------------------------------------------------
***How It does works ?*** 
![enter image description here](https://lh3.googleusercontent.com/-2j5KgH7iCH4/WFhwfTovV4I/AAAAAAAAHdU/RHJs6oeqBjIoANU1wBBXy_QkH3kff85tgCLcB/s0/MVP.png "MVP.png")

