/*

game.h

Manager class to handle the whole game

*/

#ifndef GAME_H
#define GAME_H

#include <curses.h>
#include <list>
#include <string>
#include <unistd.h>
using namespace std;

#include "gametime.h"
#include "world.h"

class Game
{
    public:
        // Constructors
        Game();
        ~Game();
    
        // Member Functions
        void update();
        void handleInput(int);

    private:
        // Private Member Functions
        void render();
    
        World* _world;
        GameTime _time;
    
        WINDOW* _description;
        WINDOW* _footer;
    
        // Static Constants
        static const int SECONDS_PER_UPDATE;
};

#endif
