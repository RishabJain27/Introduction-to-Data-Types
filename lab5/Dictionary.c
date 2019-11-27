//-----------------------------------------------------------------------
//Rishab Jain, rjain9, 12M, 8/6/17, Dictionary.c for lab5
//   Dictionary.c
//   Implementation file for DictionaryStack ADT
//----------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"


// private types 

// NodeObj
typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
} 
NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* x, char* y) {
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);
   N->key = x;
   N->value = y;
   N->next = NULL;
   return(N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
      free(*pN);
      *pN = NULL;
   }
}

// DictionaryObj
typedef struct DictionaryObj{
   Node top;
   int numItems;
} 
DictionaryObj;
// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
   Dictionary D = malloc(sizeof(DictionaryObj));
   assert(D!=NULL);
   D->top = NULL;
   D->numItems = 0;
   return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
   if(pD != NULL && *pD != NULL){
      if(!isEmpty(*pD)){
         makeEmpty(*pD);
      }
      free(*pD);
      *pD = NULL;
   }
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
   if(D->top==NULL){
      return 1;
   }
   else{
      return 0;
   }
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
   return D->numItems;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
   Node N = NULL;
   N = D->top;
   while(N != NULL){
      if(strcmp(k,N->key)==0){
         return N->value;
      }
      N = N->next;
   }
   return NULL;
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
   if(lookup(D,k) != NULL){
      fprintf(stderr, "Dictionary Error: Can't call insert on a key that already exists\n");
      exit(EXIT_FAILURE);
   }
   else{
      Node N;
      if(D->top == NULL){
        D->top = newNode(k,v);
         D->numItems++;
      }
      else{
         N=D->top;
         while (N->next != NULL){
            N = N->next;
         }
         N->next = newNode(k,v);
         D->numItems++;
      }
   }
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
   Node P;
   Node C;

   if(lookup(D,k) == NULL){
      fprintf(stderr, "Dictionary Error: Can't call delete() on a key that doesn't exists\n");
      exit(EXIT_FAILURE);
   }
   if(D->numItems == 0){
      fprintf(stderr, "Dictionary Error: Can't call delete() on an empty reference\n");
   }
   
   P=NULL;
   
   for(C=D->top; C!=NULL; C=C->next){
         
         if(!strcmp(C->key,k)){
            break;         //break out of for loops if key matches
         }
         P=C;
      }
   if(P!=NULL){                 // update prev node next ptr (except for first element)
         P->next = C->next;     //remove element from link list
      }
   if(D->top == C){
         D->top = C->next;
      }

   freeNode(&C); // free the memory of the node that is removed from the list.
   D->numItems--;

}
// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
  Node C; 
  if(D==NULL){
      fprintf(stderr, "Dictionary Error: Calling makeEmpty() on and empty list\n");
      exit(EXIT_FAILURE);
   }
   while(D->top != NULL){
   	C = D->top->next;
   	freeNode(&D->top);
   	D->top = C;
   }
   D->numItems = 0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
   Node N;
   if(D==NULL){
      fprintf(stderr, "Dictionary Error: Calling printDictionary() on a NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   for(N=D->top; N!=NULL; N=N->next){
      fprintf(out, "%s ",N->key);
      fprintf(out, "%s\n",N->value);
   }
   
}
