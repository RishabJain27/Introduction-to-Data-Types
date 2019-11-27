#------------------------------------------------------------------------------
#   Rishab Jain, rjain9, 12M, 8/11/17
#   makefile for List ADT
#------------------------------------------------------------------------------

# Variables
SOURCES    = List.java ListInterface.java ListTest.java\
             ListIndexOutOfBoundsException.java
MAINCLASS  = ListTest
CLASSES    = List.class ListInterface.class ListTest.class\
             List\$$Node.class ListIndexOutOfBoundsException.class
JARFILE    = ListTest

# Build Targets
all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(SOURCES)
	javac -Xlint $(SOURCES)  

clean:
	rm -f $(CLASSES) $(JARFILE
