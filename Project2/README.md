Introduction:

In this project you will add methods to an existing linked list class and
use this new class to make a stack to solve the balanced symbol problem.
  

Part 1 

   Modify the author's "MyLinkedList" class to add the following methods:


   
   a.  swap
        receives two index positions as parameters, and swaps the nodes at
        these positions, provided both positions are within the current size

   b.  shift
        receives an integer (positive or negative) and shifts the list this
        many positions forward (if positive) or backward (if negative).  

   c.  erase 
        receives an index position and number of elements as parameters, and
        removes elements beginning at the index position for the number of 
        elements specified, provided the index position is within the size
        and together with the number of elements does not exceed the size

   d.  insertList
        receives another MyLinkedList and an index position as parameters, and 
        moves the list from the passed list into the list at the specified
        position, provided the index position does not exceed the size.
        This should be done in constant time and the passed list should
        become empty.

   e.  main
        add code to the main method to demonstrate each of your methods
  


  Create a MyStack class using your modified MyLinkedList from Part 1.
  Use your MyStack class to demonstrate the nested symbol algorithm seen
  on slide 35 of the chapter 3 slides.
