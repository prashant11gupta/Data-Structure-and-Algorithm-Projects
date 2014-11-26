Objective:

    Work with Dijkstra's single-source shortest path algorithm.


Overview:

    In this project you will use Dijkstra's algorithm to find route information between two airports
    chosen by the user.  The user will be shown a route that results in the lowest price.


Details:

    Write a command-line program (no GUI) that lets the user choose two airports and finds the route
    that has the least price.

    Your program should read the airports.txt file that is posted, or any file having the same format.  
    It should then prompt the user to enter two airports.  

    Dijkstra's algorithm should be run to find the least expensive route.  The price, number of connections,
    and actual route should be shown.  

    The user should be able to continue checking routes until no more routes are requested.  

    You are expected to write Dijkstra's algorithm yourself following the pseudocode found in both the
    slides and in the textbook.  You can use the Vertex class and printPath found in the textbook.
    You may not use code for Dijkstra's algorithm found from other sources.

    Your output should match the sample output below:

    Sample output:

    Enter departure airport:  DFW
    Enter arrival airport:    SFO 

    By price:
      Price:       1100
      Connections: 1
      Route:       DFW -> LAX -> SFO

  
    Check another route (Y/N)? 
