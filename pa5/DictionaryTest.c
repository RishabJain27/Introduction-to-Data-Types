//DictionaryTest.c
//Rishab Jain, rjain9, 12B, 8/14/17
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char*argv[]){

Dictionary A = newDictionary();

//Test insert
insert(A,"one", "two");
insert(A, "two", "three");
printDictionary(stdout,A);   //Test printDictionary()

//Test isEmpty()
printf("%s\n", (isEmpty(A)?"true":"false"));

//Test Size
printf("%d\n", size(A));

//Test lookup()
char* x;
x= lookup(A,"one");
printf("%s\n" , x); 

//Test delete()
delete(A,"two");
printDictionary(stdout,A);

//Test makeEmpty()
makeEmpty(A);
insert(A,"four", "five");
printDictionary(stdout,A);

}

