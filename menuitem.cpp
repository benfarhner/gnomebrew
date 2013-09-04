/*

menuitem.cpp

Implementation of the MenuItem class

*/

#include "menuitem.h"

/*
 * Constructors
 */

MenuItem::MenuItem()
{
	_text = "";
}

MenuItem::MenuItem(string text)
{
	_text = text;
}

MenuItem::MenuItem(const MenuItem& other)
{
	_text = other._text;
}

MenuItem::~MenuItem()
{
}

/*
 * Accessors
 */

string MenuItem::getText()
{
	return _text;
}
