/*

gameobject.h

Represents a generic game object.

*/

#ifndef GAMEOBJECT_H
#define GAMEOBJECT_H

#include <string>
using namespace std;

class GameObject
{
    public:
        // Constructors
        GameObject();
        
        // Accessors
        int X();
        int Y();
        char Symbol();
        string Description();
        
        // Mutators
        void X(int);
        void Y(int);
        void Symbol(char);
        void Description(string);
        
        virtual void Update(int hours = 0);
    
    protected:
        int x;
        int y;
        char symbol;
        string description;
};

#endif
