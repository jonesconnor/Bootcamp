
###  Multithreads for File Access . Using Synchronized threads

##### FileObject should be common resource. 
##### Use Multithread concept to Read/write data. Data consitency to be maintained

* class DataFile

	Use parameterized constructor for getting the file name
* Method : fileOperations (string type)
    if type=read, then read info from file and display
    
     else if type=write then write the content as "my assignment completed"




* Thread classes
	
	 1) MentorProcess --> For reading the content from file
 
	2) StudentProcess --> For writing the content inside file
	
* OrganizerMain 
   
  Get options from user and invoke Mentor/Student thread