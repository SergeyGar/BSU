#include "Set.h"

Set::Set() {
	_set = new bool[_size];
	//_output;
}

void Set::changeLength(int value) {
	bool* tempset = new bool[value + 1];
	for (int i = 0; i < _size; i++) {
		tempset[i] = _set[i];
	}
	_size = value + 1;
	_set = tempset;
}

void Set::add(int value) {
	if (value >= _size) {
		changeLength(value);
	}
	_set[value] = true;
	_counterOfElements++;
}

void Set::save() {
	for (int i = 0; i < _size; i++) {
		if (_set[i]);
			//_output << i;
	}
}

void Set::pop(int value) {
	if (value >= _size)
		return;
	_set[value] = false;
}

Set::Iterator* Set::createIterator() {
	return new Iterator(this);
}

