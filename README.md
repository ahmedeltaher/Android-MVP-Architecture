**What is MVP ?**
=============
 - **View :** is a layer that displays data and reacts to user actions. On
   Android, this could be an Activity, a Fragment, an android.view.View
   or a Dialog.
 - **Model :** is a data access layer such as database API or remote server API.
 - **Presenter:** is a layer that provides View with data from Model. Presenter also handles background tasks.


----------

On Android MVP is a way to separate background tasks from activities/views/fragments to make them independent of most lifecycle-related events. This way an application becomes simpler, overall application reliability increases up to 10 times, application code becomes shorter, code maintainability becomes better and developer's life becomes happier.

----------

**MVP vs MVC ?**
------------
![enter image description here](https://lh3.googleusercontent.com/-z-YMiy8M_c8/WFh1GtNsenI/AAAAAAAAHdk/GkpPq_Y3C6EMz2s0NVS9RHfUOOwQqh0_QCLcB/s0/Screen+Shot+2016-12-20+at+01.01.14.png "Screen Shot 2016-12-20 at 01.01.14.png")

**What is MVP Pros and Cons?**
--------------------------
***Pros***
 - Testable ( leading to TDD ) 
 - Maintainable ( Code reuse ) 
 - Easy to get reviewed 
 - Information Hiding

***Cons***
 - Redundant, especially when the app size is small
 - Extra learning curve( maybe )
 - Requires time before starting coding ( but I bet, the architecture
   step is the must step for all development )

-----------------------------------------------------------

***How does It work ?***
-----------------------------
*simple and clean way , all of your calls have to be in only one direction , that is mean you shouldn't have any reference from your presenter in your useCase , and reference from your useCase in your entities , but can have a reference from useCase to presenter and from your presenter to your Android Main component (Activity , Fragment , Service , BroadCast Receiver ...... ) .*

![enter image description here](https://lh3.googleusercontent.com/-2j5KgH7iCH4/WFhwfTovV4I/AAAAAAAAHdU/RHJs6oeqBjIoANU1wBBXy_QkH3kff85tgCLcB/s0/MVP.png "MVP.png")


----------

**Interaction between Component**
-----------------------------
*The objective is the separation of concerns by keeping the business rules not knowing anything at all about the outside world, thus, they can be tested without any dependency to any external element. To achieve this, my proposal is about breaking up the project into 3 different layers, in which each one has its own purpose and works separately from the others. It is worth mentioning that each layer uses its own data model so this independence*

![enter image description here](https://lh3.googleusercontent.com/-w6E0Cb-3xIc/WFh7BpXCBOI/AAAAAAAAHd8/yycsUCNHoK8YB6rRggyybbUKcNzrKjvfACLcB/s0/Screen+Shot+2016-12-20+at+01.17.19.png "Screen Shot 2016-12-20 at 01.17.19.png")
