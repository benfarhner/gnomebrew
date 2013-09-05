/*

main.cpp

Main game loop for capturing user input and rendering updates to the screen.

*/

#include <iostream>
#include <curses.h>
#include <string>
using namespace std;

#include "gamemanager.h"

int main()
{
    // Initialize curses
    initscr();
    noecho();
    start_color();
    curs_set(0); // invisible cursor
    
    GameManager manager;
    
    while (manager.update() != GameMode::Quit);
    
    // Close curses
    endwin();
    
    return 0;
}
