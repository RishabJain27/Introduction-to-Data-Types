// Rishab Jain, rjain9, 12M, 8/6/17, DictionaryTest.c
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180
int main(int argc, char* argv[]){
   Dictionary A = newDictionary();
   //Test isEmpty()
   printf("%s\n", (isEmpty(A)?"true":"false"));
   //Test insert()
   insert(A,"one","two");
   //Test isEmpty() again
   printf("%s\n", (isEmpty(A)?"true":"false"));
   //Test size()
   printf("%d\n", size(A));
   //Test lookup()
   char* temp;
   char* k = "one";
   temp = lookup(A,k);
   printf("key=\"%s\" %s\"%s\"\n", k, (temp==NULL?"not found ":"value="), temp);
   //Test print
   printDictionary(stdout, A);
   //Test delete()
   insert(A,"three", "four");
   delete(A,"one");
   printDictionary(stdout,A);
   //Test makeEmpty()
   makeEmpty(A);
   printf("%s\n", (isEmpty(A)?"true":"false"));
}
