/*

game.h

Manager class to handle the whole game

*/

#ifndef GAME_H
#define GAME_H

#include <curses.h>
#include <list>
#include <string>
using namespace std;

#include "gametime.h"
#include "menu.h"
#include "menuitem.h"
#include "world.h"

class Game
{
public:
    // Constructors
    Game();
    ~Game();
    
    // Accessors
    bool isRunning();
    
    // Member Functions
    void update();
	void handleMenuSelection(MenuItem);

private:
	// Private Member Functions
    void handleInput();
    void render();
    void showMenu();
	
	// Private Properties
    bool _isRunning;
    bool _isMenuVisible;
    
    World _world;
    GameTime _time;
    
    WINDOW* _window;
    Menu* _menu;
	
	// Static Constants
	static const int SECONDS_PER_UPDATE;
};

#endif
