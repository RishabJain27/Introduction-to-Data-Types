//-----------------------------------------------------------------------------
// Simulation.java
// Rishab Jain, rjain9, 12b, Simulation.java
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class Simulation{

//-----------------------------------------------------------------------------
//
// The following function may be of use in assembling the initial backup and/or
// storage queues.  You may use it as is, alter it as you see fit, or delete it
// altogether.
//
//-----------------------------------------------------------------------------

   public static Job getJob(Scanner in) {
      String[] s = in.nextLine().split(" ");
      int a = Integer.parseInt(s[0]);
      int d = Integer.parseInt(s[1]);
      return new Job(a, d);
   }

  private static void simulateJobSchedule(int numOfProcs, int numOfJobs, Job[] jobs){
    //    5.      declare and initialize an array of n processor Queues and any 
    //            necessary storage Queues
    Queue[] processors = new Queue[numOfProcs+1];
    Queue finishedJobs = new Queue();
    int maxWait = 0;
    int totalWait = 0;
    float avgWait = 0;
    Job nextJob;
    int currentTime = 0;
    int nextFinishTime = -1;
    int numOfJobsFinished = 0;

    for(int p=0; p<=numOfProcs;p++) {
    
      processors[p] = new Queue();

    }

    // initialize storage queue in procs to 0
    for(int j=0;j<numOfJobs;j++){
      processors[0].enqueue(jobs[j]);
    }
    System.out.println(" proc. 0: " + processors[0]);
      // 6.      while unprocessed jobs remain  {

      while((processors[0].length() != 0) || (numOfJobsFinished < numOfJobs) ) {

        //    7.          determine the time of the next arrival or finish event and 
        //                update time
        if(processors[0].length() != 0){
        
          nextJob = (Job)processors[0].peek();
          System.out.println(" remaining proc[0] length: "+ processors[0].length()+ "number of procs: " + numOfProcs);
          
          if(nextFinishTime != -1){
            currentTime = Math.min(nextJob.getArrival(),nextFinishTime);
          }
          else{
            currentTime = nextJob.getArrival();
          }
        }
        else{
          nextJob = null;
          currentTime = nextFinishTime;
        }

        System.out.println("currentTime: " + currentTime + " nextFinishTime: " + nextFinishTime);

        //look for jobs that would finish at current time and calc. stats
        //    8.          complete all processes finishing now
        for(int p=1; p<=numOfProcs; p++){

          if(processors[p].length() != 0){

            System.out.println("checking processor job fini time: " + p +" job queue " + processors[p]);
            if(((Job)processors[p].peek()).getFinish() == currentTime){   //jobs finished time has come
              Job procJob;
              procJob = (Job)processors[p].dequeue();
              System.out.println("calculate stats for job " + procJob);
              int currentWait = procJob.getWaitTime();
              System.out.println(" processor "  + p+": job wait time "+ currentWait);

              totalWait += currentWait;
              maxWait = Math.max(maxWait,currentWait);
              avgWait = ((avgWait*numOfJobsFinished) + currentWait)/(numOfJobsFinished +1);       //avg
              numOfJobsFinished++;

              //Add to finish job Queue()
              finishedJobs.enqueue(procJob);
            }
          }
        } // End of for(numOfProcs)
       
//    9.          if there are any jobs arriving now, assign them to a processor 
//                Queue of minimum length and with lowest index in the queue array.
        System.out.println("Before if: currentTime: "+ currentTime);
        if((nextJob != null) && (currentTime == nextJob.getArrival()) ){
          System.out.println("Selecting processor to add job " +  nextJob);
          //Remove the first job from the incoming queue
          processors[0].dequeue();
          //find shortest Queue length proc.
          int shortestQLengthProc = 1;
          //shortest length
          int shortestQLength = processors[1].length();  
          for(int p = 2; p <= numOfProcs; p++){
            if (shortestQLength > processors[p].length()) {
              shortestQLengthProc = p;
              shortestQLength = processors[p].length();
            }
          }
          Job lastJob;

        //  lastJob = processors[shortestQLengthProc].peek(back) //Get last job in this queue and look at finish time
         // nextJob.computeFinishTime(lastJob.getFinishTime);

          processors[shortestQLengthProc].enqueue(nextJob);

          System.out.println("nextJob wait time: " + nextJob+ " currentTime: " + currentTime);
          System.out.println("job added to processor" + shortestQLengthProc + " : " + nextJob+ " job queue: "+ processors[shortestQLengthProc]);

        } // end if(currentTime)


        // calculate the next best finish time for the jobs in the processor queue
        nextFinishTime = -1;
        for(int p=1;p<=numOfProcs; p++) {
          if(processors[p].length() != 0){

            // calculate if this time is the nextFinishTime
            if( nextFinishTime != -1 ) {
              nextFinishTime = Math.min(nextFinishTime,((Job)processors[p].peek()).getFinish()) ;
            } 
            else {
              nextFinishTime = ((Job)processors[p].peek()).getFinish() ;
            }

          }
        }
        System.out.println("calculated next finish time = " + nextFinishTime);

    }  //end while loops
    System.out.println("totalWait:" + totalWait + "avgWait: " + avgWait+ "maxWait: " + maxWait);
  }





//-----------------------------------------------------------------------------
//
// The following stub for function main contains a possible algorithm for this
// project.  Follow it if you like.  Note that there are no instructions below
// which mention writing to either of the output files.  You must intersperse
// those commands as necessary.
//
//-----------------------------------------------------------------------------

   public static void main(String[] args) throws IOException{

//    1.  check command line arguments
      if(args.length != 1){
        System.err.println("Usage: Simulation needs 1 input file");
        System.exit(1);
      }
//
//    2.  open files for reading and writing
      Scanner inputFile = new Scanner( new File(args[0]));
      PrintWriter report = new PrintWriter(new FileWriter(args[0]+ ".rpt"));
      PrintWriter trace = new PrintWriter(new FileWriter(args[0]+ ".trc"));

//
//    3.  read in m jobs from input file
      String s = inputFile.nextLine();
      int numOfJobs = Integer.parseInt(s);
      System.out.println(numOfJobs);
      Job[] jobArray = new Job[numOfJobs];
      for (int i = 0;i<numOfJobs ;i++ ) {
        jobArray [i] = getJob(inputFile);
        System.out.println(jobArray[i]);
      }
//    
//    4.  run simulation with n processors for n=1 to n=m-1  {
      // change n<1 to n<numOfJobs
      for(int n=1; n< 2; n++){
        System.out.println("Calling simulate:" + n);
        simulateJobSchedule(n,numOfJobs,jobArray);

        //Reset finish time for next cycle
        for(int j = 0; j< numOfJobs; j++){
          jobArray[j].resetFinishTime();
        }

//
//    6.      while unprocessed jobs remain  {
//
//    7.          determine the time of the next arrival or finish event and 
//                update time
//
//    8.          complete all processes finishing now
//
//    9.          if there are any jobs arriving now, assign them to a processor 
//                Queue of minimum length and with lowest index in the queue array.
//
//    10.     } end loop
//
//    11.     compute the total wait, maximum wait, and average wait for 
//            all Jobs, then reset finish times
//
//    12. } end loop
      }
//
//    13. close input and output files

   }
}
