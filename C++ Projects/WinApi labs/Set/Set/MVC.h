#pragma once

#ifndef FIRST_WINAP_MVC_H
#define FIRST_WINAP_MVC_H

#include <windows.h>
#include <windowsx.h>
#include <commctrl.h>

#include "Set.h"

class Model : public Set
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

    void addElement(HWND);
    void pop(HWND);
    void clear();
    void update(HWND);
    void accept(Visitor&);
};
#endif //FIRST_WINAP_MVC_H
