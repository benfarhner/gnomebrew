/*

menu.h

Manager class to handle the display of an arbitrary menu of items.

*/

#ifndef MENU_H
#define MENU_H

#include <curses.h>
#include <list>
#include <string>
using namespace std;

#include "menuitem.h"

class Game;

class Menu
{
public:
    // Constructors
    Menu(Game*);
    Menu(Game*, list<MenuItem>);
	Menu(const Menu&);
    ~Menu();
    
    // Mutator Functions
    void setItems(list<MenuItem>);
    
    // Member Functions
    void update();

private:
    // Private Member Functions
    void handleInput();
    void render();
    
    // Private Properties
    Game* _owner;
    WINDOW* _menu;
    list<MenuItem> _items;
    list<MenuItem>::iterator _selected;
};

#include "game.h"

#endif
