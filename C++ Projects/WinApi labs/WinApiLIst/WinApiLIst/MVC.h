#pragma once

#ifndef FIRST_WINAP_MVC_H
#define FIRST_WINAP_MVC_H

#include <windows.h>
#include <windowsx.h>
#include <commctrl.h>

#include "List.h"

class Model : public List
{
public:
    Model() = default;
    ~Model() = default;
};

class View
{
private:
    Model* _model;
    int _textBoxId;
public:
    View(Model* m, int id) : _model(m), _textBoxId(id) { }
    ~View() { delete _model; }

    void update(HWND);
};

class Controller
{
private:
    Model* _model;
    View* _view;
public:
    Controller(Model* m, View* v) : _model(m), _view(v) { }
    ~Controller()
    {
        delete _view;
        //delete _model;
    }

    void pushBack(HWND);
    void popBack();
    void pushFront(HWND);
    void popFront();
    void clear();
    void update(HWND);
    void swap(Controller&);
    friend Controller& operator+=(Controller&, const Controller&);
    void accept(Visitor&);
};
#endif //FIRST_WINAP_MVC_H