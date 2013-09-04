CC=g++
CFLAGS=-lncurses
EXECUTABLE=gnomebrew

all: $(EXECUTABLE)

$(EXECUTABLE):
	$(CC) $(CFLAGS) *.cpp -o $(EXECUTABLE)

clean:
	rm -rf gnomebrew