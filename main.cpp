/*

main.cpp

Main game loop for capturing user input and rendering updates to the screen.

*/

#include <iostream>
#include <curses.h>
#include <string>
#include <unistd.h>
using namespace std;

#include "game.h"

int main()
{
    // Initialize curses
    initscr();
    noecho();
    curs_set(0); // invisible cursor
    
    Game game;
    
    while (game.isRunning())
    {
        game.update();
        
        //sleep(1);
    }
    
    // Close curses
    endwin();
    
    return 0;
}
