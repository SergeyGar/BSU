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

void Controller::addElement(HWND valueToPush)
{
    char value[256];
    GetWindowText(valueToPush, value, 20);
    _model->add(atoi(value));
    //_viewer->update();
}

void Controller::pop(HWND hwnd)
{
    char value[256];
    GetWindowText(hwnd, value, 20);
    _model->pop(atoi(value));
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

void Controller::accept(Visitor& visitor) {
    visitor.visit(*_model);
}