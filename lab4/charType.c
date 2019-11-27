//Rishab Jain,rjain9, 12M, 7/28/17, main code for lab
#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>
#include<stdio.h>
#include<string.h>
#include<ctype.h>
#define MAX_STRING_LENGTH 100

// function prototype 
void extract_alpha_num(char* s, char* a,char* d,char* p,char* w);

// function main which takes command line arguments 
int main(int argc, char* argv[]){
   FILE* in;        // handle for input file                  
   FILE* out;       // handle for output file                 
   char* line;      // string holding input line              
   char* alpha_num; // string holding all alpha chars
   char* numeric;   // string for the numbers
   char* punct;     //string for punctuation
   char* white;
   int lineNumber = 1;
   int alphaCount = 0;
   int numericCounter = 0;
   int punctCounter = 0;
   int whiteCounter = 0;
   // check command line for correct number of arguments 
   if( argc != 3 ){
      printf("Usage: %s input-file output-file\n", argv[0]);
      exit(EXIT_FAILURE);
   }

   // open input file for reading 
   if( (in=fopen(argv[1], "r"))==NULL ){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   // open output file for writing 
   if( (out=fopen(argv[2], "w"))==NULL ){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   // allocate strings line and alpha_num on the heap 
   line = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   
   alpha_num = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   numeric = calloc(MAX_STRING_LENGTH+1, sizeof(char ) );
   punct = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   white = calloc(MAX_STRING_LENGTH+1,sizeof(char));
  
   assert( line!=NULL && alpha_num!=NULL );
   assert(line!=NULL && numeric !=NULL);
   assert(line!=NULL && punct !=NULL);
   assert(line!=NULL && white !=NULL);
  
   // read each line in input file, extract alpha-numeric characters 
   while( fgets(line, MAX_STRING_LENGTH, in) != NULL ){
      extract_alpha_num(line, alpha_num,numeric,punct,white);
      
      alphaCount = strlen(alpha_num);
      numericCounter = strlen(numeric);
      punctCounter = strlen(punct);
      whiteCounter = strlen(white);
      
      if(alphaCount>0 || numericCounter>0 || punctCounter>0 || whiteCounter>1){
     	 fprintf(out,"%s", "line ");
     	 fprintf(out,"%d",lineNumber);
     	 fprintf(out,"%s\n", " contains: ");
         //prints line for just alphabetic char
     	 fprintf(out, "%d" ,alphaCount); 
     	 if(alphaCount != 1){
         fprintf(out,"%s"," alphabetic characters: ");
         }
         else{
         fprintf(out,"%s"," alphabetic character: ");
         }
     	 fprintf(out, "%s\n", alpha_num);
      
     	 //prints line for numeric char
         fprintf(out, "%d", numericCounter);
         if(numericCounter != 1){
     	 fprintf(out, "%s" , " numeric characters: ");
         }
         else{
         fprintf(out, "%s" , " numeric character: ");
         }
     	 fprintf(out, "%s\n", numeric);

         //prints punctuation line
     	 fprintf(out, "%d", punctCounter);
         if(punctCounter != 1){
     	 fprintf(out,"%s"," punctuation characters: ");
         }
         else{
         fprintf(out,"%s"," punctuation character: ");
         }
     	 fprintf(out,"%s\n", punct);
         
         //prints whitespace line
     	 fprintf(out, "%d",whiteCounter);
         if(whiteCounter != 1){
     	 fprintf(out, "%s", " whitespace characters: ");
         }
         else{
         fprintf(out, "%s", " whitespace character: ");
         }
     	 fprintf(out, "%s\n", white);
      }
      
      lineNumber++;
   }

   // free heap memory 
   free(line);
   free(alpha_num);
   free(numeric);
   free(punct);
   free(white);

   // close input and output files 
   fclose(in);
   fclose(out);

   return EXIT_SUCCESS;
}

// function definition 
void extract_alpha_num(char* s, char* a,char* d, char* p, char* w){
   int i=0, j=0, k=0, g =0, e = 0;
   while(s[i]!='\0' && i<MAX_STRING_LENGTH){
	if(( s[i]>= 'a' && s[i] <='z') ||( s[i] >= 'A' && s[i] <= 'Z')){
	 a[j++] = s[i];
	}
        
        if(isdigit(s[i])){
       	d[k++] = s[i];
        }
        if(ispunct(s[i])){
        p[g++] = s[i];
        } 
       	if(isspace(s[i])){
        w[e++] = s[i];
        }
      i++;
  }
   
   a[j] = '\0';
   d[k] = '\0';
   p[g] = '\0';
   w[e] = '\0';
   
}
