//Dictionary.c
//Rishab Jain, rjain9, 12B, 8/18/17

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

// private types --------------------------------------------------------------
const int tableSize = 101;

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
   int sizeInBits = 8*sizeof(unsigned int);
   shift = shift & (sizeInBits - 1);
   if ( shift == 0 )
      return value;
   return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) { 
   unsigned int result = 0xBAE86554;
   while (*input) { 
      result ^= *input++;
      result = rotate_left(result, 5);
   }
   return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
   return pre_hash(key)%tableSize;
}

// NodeObj
typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

Node newNode(char* x, char* y) {
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);
   N->key = calloc(strlen(x)+1, sizeof(char));
   strcpy(N->key, x);
   N->value = calloc(strlen(y)+1, sizeof(char));
   strcpy(N->value, y);
   N->next = NULL;
   return(N);
}
// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
	  Node N = *pN;
	  free(N->key);
	  free(N->value);
	  free(*pN);
      *pN = NULL;
   }
}
typedef struct DictionaryObj{
   Node* hashT;
   int numItems;
}
DictionaryObj;

void deleteAll(Node N){
   if(N != NULL){
      deleteAll(N->next);
      freeNode(&N);
   }
}

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
   Dictionary D = malloc(sizeof(DictionaryObj));
   assert(D != NULL);
   D->hashT= calloc(tableSize, sizeof(Node*));
   D->numItems = 0;
   return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
   Dictionary D = *pD;   
   makeEmpty(D); // free all nodes
   free(D->hashT); // now free the hashtable bin array
   free(*pD);
   *pD = NULL;
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
   if( D ==NULL){
      return 1;
   }
   return (D->numItems == 0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
   if(D == NULL){
   	fprintf(stderr,"Can't call size() on a list that doesn't exist");
   	exit(EXIT_FAILURE);
   }
   return D->numItems;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
   if( D==NULL){
       fprintf(stderr, "Can't call lookup() on Null Dictionary\n");
       exit(EXIT_FAILURE);
    }
 
   for(int i = 0; i<tableSize;i++){
      if(D->hashT[i] != NULL){
         for(Node N = D->hashT[i]; N!= NULL; N=N->next){
            if(strcmp(k,N->key)==0){
               return N->value;
            }
         }
      }
   }
   return NULL;
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
   if(lookup(D,k) != NULL){
      fprintf(stderr, "Can't call insert() on a key that already exists\n");
      exit(EXIT_FAILURE);
   }
   else{
      Node N;
      if(D->hashT[hash(k)] == NULL){
         D->hashT[hash(k)] = newNode(k,v);
         D->numItems++;
      }
      else{
         N=D->hashT[hash(k)];
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
   if(lookup(D,k) == NULL){
      fprintf(stderr, "Dictionary Error: Can't call delete() on a key that doesn't exists\n");
      exit(EXIT_FAILURE);
   }
   if(D->numItems == 0){
      fprintf(stderr, "Dictionary Error: Can't call delete() on an empty reference\n");
   }

   Node C,P;
   
   C = D->hashT[hash(k)];
   while(C != NULL && strcmp(C->key, k) != 0){
      C=C->next;
   }
   
   P = D->hashT[hash(k)];
   while(P->next != NULL && strcmp(P->next->key, k) != 0){
      P=P->next;
   }
   
   if(P!=NULL){
         P->next = C->next;     //remove element from link list
   }
   if(D->hashT[hash(k)] == C){
         D->hashT[hash(k)] = C->next;
   }
   deleteAll(C);
   D->numItems--;

}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
   if(D==NULL){
      fprintf(stderr, "Can't call makeEmpty() on and empty list\n");
      exit(EXIT_FAILURE);
   }
   for(int i=0; i<tableSize;i++){
        deleteAll( D->hashT[i]);
         D->hashT[i] =  NULL;

      }
   D->numItems=0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
   if( D==NULL ){
      fprintf(stderr,"Can't call printDictionary() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   for(int i = 0; i < tableSize; i++){
      if(D->hashT[i] != NULL){
         for(Node N = D->hashT[i]; N!=NULL; N=N->next){ 
            fprintf(out, "%s%s", N->key, " ");
            fprintf(out, "%s\n",N->value);
         }
      }
   }
}
