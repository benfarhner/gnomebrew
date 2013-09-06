/*

main.cpp

Main game loop for capturing user input and rendering updates to the screen.

*/

//#define DEBUG 1

#include <iostream>
#include <curses.h>
#include <string>
using namespace std;

#include "gamemanager.h"

int main()
{
#ifndef DEBUG
    // Initialize curses
    initscr();
    noecho();
    start_color();
    curs_set(0); // invisible cursor
#endif
    
    GameManager manager;
    
#ifndef DEBUG
    while (manager.update() != GameMode::Quit);
    
    // Close curses
    endwin();
#endif
    
    return 0;
}
