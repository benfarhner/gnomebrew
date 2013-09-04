/*

menu.cpp

Implementation of the Menu class

*/

#include "menu.h"



/*
 * Constructors
 */

Menu::Menu(Game* owner)
{
    _owner = owner;
    
    _selected = _items.begin();
    
    // Initialize menu
    _menu = newwin(_items.size() + 4, COLS / 4, 5, COLS / 8 * 3);
    keypad(_menu, TRUE);
    box(_menu, 0, 0);
}

Menu::Menu(Game* owner, list<MenuItem> items)
{
    _owner = owner;
    
    setItems(items);
}

Menu::Menu(const Menu& other)
{
    _owner = other._owner;
    
	setItems(other._items);
}

Menu::~Menu()
{
    delwin(_menu);
}

/*
 * Mutator Functions
 */

void Menu::setItems(list<MenuItem> items)
{
    _items = items;
    _selected = _items.begin();
    
    // Initialize menu
    delwin(_menu);
    _menu = newwin(_items.size() + 4, COLS / 4, 5, COLS / 8 * 3);
    keypad(_menu, TRUE);
    box(_menu, 0, 0);
}

/*
 * Member Functions
 */

void Menu::update()
{
    render();
    handleInput();
}

/*
 * Private Member Functions
 */

void Menu::handleInput()
{
    int input = wgetch(_menu);
    
    switch (input)
    {
        case KEY_UP:
            if (_selected == _items.begin())
            {
                _selected = _items.end();
            }
            else
            {
                --_selected;
            }
            break;
        case KEY_DOWN:
            if (_selected == _items.end())
            {
                _selected = _items.begin();
            }
            else
            {
                ++_selected;
            }
            break;
        case 10:
        case 13:
        case 32:
            _owner->handleMenuSelection(*_selected);
            break;
    }
}

void Menu::render()
{
    int row = 2, y, col;
    
    for (list<MenuItem>::iterator it = _items.begin();
         it != _items.end();
         ++it)
    {
        if (it == _selected)
        {
            wattron(_menu, A_REVERSE);
        }
        
        getmaxyx(_menu, y, col);
        col = (col - it->getText().length()) / 2;
        mvwprintw(_menu, row++, col, it->getText().c_str());
        
        wattroff(_menu, A_REVERSE);
    }
    
    touchwin(_menu);
    wrefresh(_menu);
}
