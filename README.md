# The Issue
The issue is when we have a project that enables databinding and dynamic feature. 
More specifically when we have a logic in our dfm layout that accesses some resources from application or library module.  
See this `activity_dynamic.xml` example:  
``` xml
<TextView
   android:id="@+id/text_view_greeting"
   android:layout_width="wrap_content"
   android:layout_height="wrap_content"
   android:textSize="16sp"
   android:text="@{viewModel.login ? @string/text_login : @string/text_logout}"/>
```  
`@string/text_login` and `@string/text_logout` are from app module. 
So when we build the app, databinding will create a generated file called `ActivityDynamicBindingImpl`, and this file will contain some logic like this:  
``` java
// read viewModel.login ? @android:string/text_login : @android:string/text_logout
viewModel... = ((viewModelLogin) ? (textViewGreeting.getResources().getString(R.string.text_login)) : (textViewGreeting.getResources().getString(R.string.text_logout)));
```  
It will make compilation error because dynamic module doesn't know `R.string.text_login` and `R.string.text_logout`.  
So, we need to change them to full name `com.yeuristic.reproducedatabindingdfm.R.string.text_login` and `com.yeuristic.reproducedatabindingdfm.R.string.text_logout`.  
But we can't do that since it is generated file.  
  
# How to reproduce
Run the project and these errors will show up:  
```
error: cannot find symbol viewModelLoginTextViewGreetingAndroidStringTextLoginTextViewGreetingAndroidStringTextLogout = ((viewModelLogin) ? (textViewGreeting.getResources().getString(R.string.text_login)) : (textViewGreeting.getResources().getString(R.string.text_logout)));
symbol:   variable text_login
location: class string
```
```
error: cannot find symbol viewModelLoginTextViewGreetingAndroidStringTextLoginTextViewGreetingAndroidStringTextLogout = ((viewModelLogin) ? (textViewGreeting.getResources().getString(R.string.text_login)) : (textViewGreeting.getResources().getString(R.string.text_logout)));
symbol:   variable text_logout
location: class string
```  
# Workaround
Change your databinding logic to this  
``` xml
<TextView
    android:id="@+id/text_view_greeting"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:textSize="16sp"
    android:text="@{viewModel.login ? textViewGreeting.getResources().getString(com.yeuristic.reproducedatabindingdfm.R.string.text_login) : textViewGreeting.getResources().getString(com.yeuristic.reproducedatabindingdfm.R.string.text_logout)}"/>
```  
Or you can uncomment it from `activity_dynamic.xml` that I've provided.
