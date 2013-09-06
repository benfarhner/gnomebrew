/*

menu.h

Manager class to handle the display of an arbitrary menu of items.

*/

//#define DEBUG 1

#ifndef MENU_H
#define MENU_H

#include <curses.h>
#include <list>
#include <string>
using namespace std;

#include "menuitem.h"

class GameManager;

class Menu
{
    public:
        // Constructors
        Menu(GameManager*);
        Menu(GameManager*, list<MenuItem>);
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
        void selectNext();
        void selectPrevious();
        void selectFirst();
    
        // Private Properties
        GameManager* _owner;
        WINDOW* _menu;
        list<MenuItem> _items;
        list<MenuItem>::iterator _selected;
};

#include "gamemanager.h"

#endif
