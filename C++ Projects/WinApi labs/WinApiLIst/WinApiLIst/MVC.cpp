#include "MVC.h"
#include <string>

void View::update(HWND hwnd)
{
    SendDlgItemMessage(hwnd, _textBoxId, LB_RESETCONTENT, NULL, NULL);
    if (!_model->isEmpty()) {
        auto it = _model->createIterator();
        for (it->first(); !it->reachedTheEnd(); it->next())
        {
            //std::string str_elem = std::to_string(it->element());
            SendDlgItemMessage(hwnd, _textBoxId, LB_INSERTSTRING, -1, (LPARAM)std::to_string(it->element()).data());
        }
    }
}

void Controller::pushBack(HWND valueToPush)
{
    char value[256];
    GetWindowText(valueToPush, value, 20);
    _model->pushBack(atoi(value));
    //_viewer->update();
}

void Controller::popBack()
{
    _model->popBack();
    //_viewer->update();
}

void Controller::pushFront(HWND valueToPush) {
    char value[256];
    GetWindowText(valueToPush, value, 20);
    _model->pushFront(atoi(value));
    //_viewer->update();
}

void Controller::popFront()
{
    _model->popFront();
    //_viewer->update();
}

void Controller::clear()
{
    _model->clear();
    //_viewer->update();
}

void Controller::update(HWND hwnd) {
    _view->update(hwnd);
}

void Controller::swap(Controller& controller) {
    _model->swap(*controller._model);
    //_viewer->update();
    //controller._viewer->update();
}

Controller& operator+=(Controller& first, const Controller& second)
{
    *first._model += *(second._model);
    //first._viewer->update();
    return first;
}

void Controller::accept(Visitor& visitor) {
    visitor.visit(*_model);
}