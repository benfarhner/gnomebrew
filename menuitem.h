/*

menuitem.h

Defines a MenuItem object used in Menus

*/

#ifndef MENUITEM_H
#define MENUITEM_H

#include <string>
using namespace std;

class MenuItem
{
	public:
		// Constructors
		MenuItem();
		MenuItem(string);
		MenuItem(const MenuItem&);
		~MenuItem();
		
		// Accessors
		string getText();
		
	private:
		// Private Properties
		string _text;
};

#endif
