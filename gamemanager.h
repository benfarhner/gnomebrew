/*

gamemanager.h

Manager class to oversee all window and input management.

*/

//#define DEBUG 1

#ifndef GAMEMANAGER_H
#define GAMEMANAGER_H

#include "game.h"
#include "menu.h"

// Game color pairs
#define COLOR_PAIR_NORMAL 1

// Game operation modes
namespace GameMode
{
    enum Enum
    {
        NewGame,
        Game,
        MainMenu,
        PauseMenu,
        Quit,
    };
}

class GameManager
{
    public:
        // Constructors
        GameManager();
        GameManager(const GameManager&);
        ~GameManager();
        
        // Member Functions
        GameMode::Enum update();
        void handleMenuSelection(MenuItem);
        
    private:
        // Private Member Functions
        void handleInput();
        
        // Properties
        GameMode::Enum _mode;
        Game* _game;
        Menu* _mainMenu;
        Menu* _pauseMenu;
};

#endif
