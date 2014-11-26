Introduction:

   In this project you will create a splay tree for integers with 
   a number of custom methods.



Description:

   Create a class called SplayTree.  SplayTree will implement a splay tree
   as described in chapter 4 of the textbook.  The SplayTree will store 
   integers for its data rather than being generic.  The rotations will 
   need the parent and grandparent, so you will either need to store them
   on a stack as you move down the tree, or save a parent pointer on each
   node so that you can refer back up.
   
   Note that the splay tree code in chapter 12 is not applicable as it is
   for a top-down splay tree and this project is for a bottom-up splay tree.

   You are expected to code this from scratch by thinking through the various
   operations.  You should not copy tree code from the Internet or other 
   sources.

   Your tree should have the following methods.  The methods should 
   all operate on the object making the call (none are static).  


   a) add
       Adds a node to the tree containing the passed integer value.  
       Rotates the node to the top.

   15 points
   b) find
        Returns true if the value passed is in the tree.
        Rotates the node to the top if found or the last node
        accessed if not found.

   c) leafCount
        Returns the count of all of the leaves in the tree.
        This should be performed as a recursive function that
        traverses the tree and counts the leaves.

   
   d) treeSum
        Returns the sum of all of the values in the tree.
        This should be performed as a recursive function that
        traverses the tree and sums the values.
   
   e) toString
        Returns a string of the values of an inorder traversal of the nodes.
   
   f) printLevels
        Prints the values level-by-level, root first, then its children,
        then their children, etc.

   10 points
   g) Main
        Demonstrates all of the above methods.
