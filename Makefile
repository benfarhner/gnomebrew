CC=g++
CFLAGS=-lncurses
EXECUTABLE=gnomebrew

all:
	$(CC) $(CFLAGS) *.cpp -o $(EXECUTABLE)

clean:
	rm -rf gnomebrew