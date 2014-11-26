Objective:

     Work with hash tables.

Overview:

     In this project you will create a linear probing hash table and use it to perform spell
     checking of a document.

Part 1 

     Create your own linear probing hash table.  
     Your class should at least have these methods:

      a) public boolean contains( AnyType x )
           returns true if the item is found.

      b) public boolean insert( AnyType x )
           inserts the item using linear probing.
           ignores duplicates.
           returns true if item is successfully inserted.
           expands table when half full.


Part 2             

     Create a class which will read a dictionary file and load into your hash table in part 1.
     Once loaded, read the provided document file and check each word in the hash table.
     In checking the words, your program will need to do word extraction by ignoring
     punctuation, ignoring capitalization, etc.
     For each word that is not found, provide a list of suggested words that are the same but
     have one letter changed.  (You can use code from the word ladder program for this).
     
     Expected output:

       werrwolves is misspelled
       Alternatives: werewolves 

       rast is misspelled
       Alternatives: bast cast east fast hast last mast past raft rant rapt rase rash rasp rest rust vast wast 

       pessemism is misspelled
       Alternatives: pessimism 
