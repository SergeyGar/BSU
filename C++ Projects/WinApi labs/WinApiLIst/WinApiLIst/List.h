#pragma once

#include<iostream>

class List;

class Visitor {
public:
    virtual void visit(List&) = 0;
};

class Container {
    virtual void accept(Visitor&) = 0;
};

class VisitorForContainer : public Visitor {
private:
    double _average;
public:
    void visit(List&) override;
    double getAverage() {
        return _average;
    }
};

class List: public Container {
private:
    const int _MULTIPLIER = 2;
    int* _list;
    int _size = 1;
    int _counterOfElements = 0;
    int _head = 0;
    int _tail = 0;

    void changeLengthOfList(int);
    void deleteList();
public:
    class Iterator;
    List();
    List(int);
    List(List&&);
    List(const List&);
    List(const std::initializer_list<int>);
    ~List();

    bool isEmpty() const;
    int size() const;
    void clear();
    int front() const;
    int back() const;
    void pushFront(int);
    void pushBack(int);
    void popFront();
    void popBack();
    void swap(List&);

    Iterator* createIterator() const;

    void accept(Visitor&) override;

    List& operator=(const List&);
    List& operator=(List&&);
    friend bool operator == (const List&, const List&);
    friend bool operator != (const List&, const List&);
    friend List& operator += (List&, const List&);
    friend List operator + (const List&, const List&);
    friend std::ostream& operator<<(std::ostream&, const List&);
    friend std::istream& operator>>(std::istream&, List&);

    class Iterator {
        const List* _listPointer;
        int _position;
        bool isEnd = false;
    public:
        Iterator(const List* list1) {
            _listPointer = list1;
            _position = _listPointer->_head;
        }
        void next() {
            if (_position == _listPointer->_tail) {
                isEnd = true;
                return;
            }
            else
                isEnd = false;
            _position = (_position + 1) % _listPointer->_size;
        }
        void previous() {
            if (_position == _listPointer->_head) {
                isEnd = true;
                return;
            }
            _position = _position == 0 ? _listPointer->_size - 1 : _position - 1;
        }
        void first() {
            _position = _listPointer->_head;
            isEnd = false;
        }
        void last() {
            _position = _listPointer->_tail;
            isEnd = false;
        }
        int element() {
            return _listPointer->_list[_position];
        }
        bool reachedTheEnd() {
            if (_listPointer->isEmpty())
                return true;
            return  isEnd;
        }
    };
};
