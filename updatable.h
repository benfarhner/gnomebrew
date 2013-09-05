/*

updatable.h

Abstract class defining the update() method to be used across classes.

*/

#ifndef UPDATABLE_H
#define UPDATABLE_H

class Updatable
{
    public:
        virtual void update(int seconds = 0) = 0;
};

#endif
