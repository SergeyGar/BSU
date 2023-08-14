#include "List.h"
#include <initializer_list>

List::List() {
    _list = new int[_size];
}

List::List(int number) {
    _list = new int[_size];
    pushBack(number);
}

List::List(const List& list) {
    _list = new int[list._size];
    if (!list.isEmpty()) {
        for (int i = 0; i < list._counterOfElements; i++) {
            pushBack(list._list[i]);
        }
    }
}

List::List(List&& list) {
    _list = list._list;
    _size = list._size;
    _counterOfElements = list._counterOfElements;
    _head = list._head;
    _tail = list._tail;
    list._list = nullptr;
}

List::List(const std::initializer_list<int> list) {
    _list = new int[_size];
    for (auto element : list)
        pushBack(element);
}

List::~List() {
    deleteList();
}

void List::changeLengthOfList(int newSize) {
    int* tempList = new int[newSize];
    for (int i = 0; i < _counterOfElements; i++) {
        tempList[i] = _list[(_head + i) % _size];
    }
    _size = newSize;
    _head = 0;
    _tail = _counterOfElements - 1;
    deleteList();
    _list = tempList;
}

void List::pushBack(int value) {
    if (_counterOfElements == _size) {
        changeLengthOfList(_size * _MULTIPLIER);
    }
    _tail = (_tail + 1) % _size;
    _list[_tail] = value;
    _counterOfElements++;
}

void List::pushFront(int value) {
    if (_counterOfElements == _size) {
        changeLengthOfList(_size * _MULTIPLIER);
    }
    if (_head == 0)
        _head = _size - 1;
    else
        _head--;
    _list[_head] = value;
    _counterOfElements++;
}

void List::popBack() {
    if (!isEmpty()) {
        if (_counterOfElements == _size / 4) {
            changeLengthOfList(_size / _MULTIPLIER);
        }
        if (_tail == 0)
            _tail = _size - 1;
        else
            _tail--;
        _counterOfElements--;
    }
    else
        std::cout << "Error!" << std::endl;
}

void List::popFront() {
    if (!isEmpty()) {
        if (_counterOfElements == _size / 4) {
            changeLengthOfList(_size / _MULTIPLIER);
        }
        _head = (_head + 1) % _size;
        _counterOfElements--;
    }
    else
        std::cout << "Error!" << std::endl;
}

bool List::isEmpty() const {
    return _counterOfElements == 0;
}

int List::back() const {
    return _list[_tail];
}

int List::front() const {
    return _list[_head];
}

int List::size() const {
    return _counterOfElements;
}

void List::deleteList() {
    delete[] _list;
}

void List::clear() {
    deleteList();
    _size = 1;
    _list = new int[_size];
    _counterOfElements = 0;
    _head = 0;
    _tail = 0;
}

void List::swap(List& list) {
    List tempList = list;
    list = *this;
    *this = tempList;   //?
   /* _size = tempList._size;
    delete[] _list;
    _list = new int[_size];
    for (int i = 0; i < _size; i++) {
        _list[i] = tempList._list[i];
    }
    _counterOfElements = tempList._counterOfElements;
    _head = tempList._head;
    _tail = tempList._tail;*/

}

List& List::operator=(const List& list) {
    if (this == &list)
        return *this;
    _size = list._size;
    deleteList();
    _list = new int[_size];
    for (int i = 0; i < _size; i++) {
        _list[i] = list._list[i];
    }
    _counterOfElements = list._counterOfElements;
    _head = list._head;
    _tail = list._tail;
    return *this;
}

List& List::operator=(List&& list) {
    if (this == &list)
        return *this;
    _list = list._list;
    _size = list._size;
    _counterOfElements = list._counterOfElements;
    _head = list._head;
    _tail = list._tail;
    list._list = nullptr;
    return *this;
}

bool operator == (const List& list1, const List& list2) {

    if (list1._counterOfElements != list2._counterOfElements || list1._head != list2._head)
        return false;
    int i = list1._head;
    int j = list2._head;
    while (true) {
        if (i == list1._tail)
            break;
        if (list1._list[i] != list2._list[j])
            return false;
        i = (i + 1) % list1._size;
        j = (j + 1) % list2._size;
    }
    return true;
}

bool operator != (const List& list1, const List& list2) {
    return !(list1 == list2);
}

List& operator += (List& list1, const List& list2) {
    /*for (int i = list2._head; i != list2._tail; i = (i + 1) % list2._size) {
        list1.pushBack(list2._list[i]);
    }
    if (list2._counterOfElements != 0)
        list1.pushBack(list2._list[list2._tail]);*/
    auto it = list2.createIterator();
    for (it->first(); !it->reachedTheEnd(); it->next()) {
        list1.pushBack(it->element());
    }
    return list1;
}

List operator + (const List& list1, const List& list2) {
    List result = list1;
    result += list2;
    return result;
}

std::ostream& operator<<(std::ostream& ostream, const List& list) {
    if (!list.isEmpty()) {
        for (int i = list._head; i != list._tail; i = (i + 1) % list._size) {
            ostream << list._list[i] << " ";
        }
        ostream << list._list[list._tail] << std::endl;
    }
    return ostream;
}

std::istream& operator>>(std::istream& istream, List& list) {
    int numberOfElements = 0;
    istream >> numberOfElements;
    for (int i = 0; i < numberOfElements; i++) {
        int value;
        istream >> value;
        list.pushBack(value);
    }
    return istream;
}

List::Iterator* List::createIterator() const {
    return new Iterator(this);
}

void VisitorForContainer::visit(List& list) {
    int sumOfAllElements = 0;
    if (!list.isEmpty()) {
        for (auto it = list.createIterator(); !it->reachedTheEnd(); it->next()) {
            sumOfAllElements += it->element();
        }
        _average = (double)sumOfAllElements / list.size();
    }
    else
        _average = 0;

}

void List::accept(Visitor& visitor) {
    visitor.visit(*this);
}