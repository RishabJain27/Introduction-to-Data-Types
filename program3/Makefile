#------------------------------------------------------------------------------
#   makefile for Dictionary ADT
#Rishab Jain,rjain9,7/24/17,12B,Makefile
#------------------------------------------------------------------------------
# Variables
SOURCES    = Dictionary.java DictionaryInterface.java DictionaryTest.java\
             KeyNotFoundException.java DuplicateKeyException.java
MAINCLASS  = DictionaryTest
CLASSES    = Dictionary.class DictionaryInterface.class DictionaryTest.class\
             Dictionary\$$Node.class KeyNotFoundException.class DuplicateKeyException.class
JARFILE    = DictionaryTest

# Build Targets
all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(SOURCES)
	javac $(SOURCES)  

clean:
	rm -f $(CLASSES) $(JARFILE) 
