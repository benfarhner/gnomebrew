/*

gamemanager.cpp

Implementation of the GameManager class

*/

#include "gamemanager.h"

/*
 * Constructors
 */

GameManager::GameManager()
{
    _mode = GameMode::MainMenu;
    
    _game = new Game();
    
    // Initialize main menu
    list<MenuItem> items;
    
    // TODO: Make this conditional on there being an existing saved game
    items.push_back(MenuItem("Continue Game", false));
    items.push_back(MenuItem("New Game"));
    items.push_back(MenuItem("Quit"));
	_mainMenu = new Menu(this, items);
    
    // Initialize pause menu
    items.clear();
    items.push_back(MenuItem("Resume"));
    items.push_back(MenuItem("Save", false));
    items.push_back(MenuItem("Quit"));
	_pauseMenu = new Menu(this, items);
	
#ifndef DEBUG
	// Initialize game color pairs
    init_pair(COLOR_PAIR_NORMAL, COLOR_WHITE, COLOR_BLACK);
    
    keypad(stdscr, true);
    nodelay(stdscr, true);
#endif
}

GameManager::GameManager(const GameManager& other)
{
    *_game = *(other._game);
    *_mainMenu = *(other._mainMenu);
    *_pauseMenu = *(other._pauseMenu);
}

GameManager::~GameManager()
{
    delete _game;
    delete _mainMenu;
    delete _pauseMenu;
}

/*
 * Member Functions
 */

GameMode::Enum GameManager::update()
{
    if (_mode == GameMode::MainMenu)
    {
        _mainMenu->update();
    }
    else if (_mode == GameMode::NewGame)
    {
        // TEMPORARY
        _mode = GameMode::Game;
    }
    else if (_mode == GameMode::Game)
    {
        handleInput();
        _game->update();
    }
    else if (_mode == GameMode::PauseMenu)
    {
        _pauseMenu->update();
    }
    
    return _mode;
}

void GameManager::handleMenuSelection(MenuItem selection)
{
    if (_mode == GameMode::MainMenu)
    {
        if (selection.getText() == "New Game")
        {
            _mode = GameMode::NewGame;
        }
        else if (selection.getText() == "Quit")
        {
            _mode = GameMode::Quit;
        }
    }
    else if (_mode == GameMode::PauseMenu)
    {
        if (selection.getText() == "Resume")
        {
            _mode = GameMode::Game;
        }
        else if (selection.getText() == "Quit")
        {
            _mode = GameMode::Quit;
        }
	}
}

/*
 * Private Member Functions
 */

void GameManager::handleInput()
{
#ifndef DEBUG
    int input = getch();
    
    if (input != ERR)
    {
        if (input == KEY_F(1))
        {
            _mode = GameMode::PauseMenu;
        }
        else if (_mode == GameMode::Game)
        {
            _game->handleInput(input);
        }
    }
#endif
}
