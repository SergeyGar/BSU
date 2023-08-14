#pragma once
#include <fstream>

class Iterator;
class Set;

class Visitor {
public:
	virtual void visit(Set&) = 0;
};

class Container {
	virtual void accept(Visitor&) = 0;
};

class VisitorForContainer : public Visitor {
private:
	double _cardinality;
public:
	void visit(Set& set) override {
		//set.save();
		//_cardinality = set.size();
	}
	double getCardinality() {
		return _cardinality;
	}
};

class Set: public Container
{
private:
	const int _MULTIPLIER = 2;
	bool* _set;
	int _size = 1;
	int _counterOfElements = 0;
public:
	//std::ofstream _output("outputFile.txt");
	Set();
	void add(int);
	void changeLength(int);
	void save();
	void pop(int);
	void clear() {
		delete[]_set;
		_size = 1;
		_set = new bool[_size];
		_counterOfElements = 0;
	}
	bool isEmpty() { return _counterOfElements == 0; }
	int size() { return _counterOfElements; }
	void accept(Visitor& visitor)override {
		visitor.visit(*this);
	}

	class Iterator {
	private:
		int _position = 0;
		const Set* _set;
	public:
		Iterator(const Set* set) {
			_set = set;
		}

		bool reachedTheEnd() {
			for (int i = _set->_size - 1; i >= 0; i--) {
				if (_set->_set[i] && i==_position) {
					return true;
				}
			}
			return false;
		}

		void first() {
			for (int i = 0; i<_set->_size; i++) {
				if (_set->_set[i]) {
					_position = i;
					return;
				}
			}
		}

		void last() {
			for (int i = _set->_size - 1; i >= 0; i--) {
				if (_set->_set[i]) {
					_position = i;
					return;
				}
			}
		}

		void next() {
			for (int i = _position + 1; i < _set->_size; i++) {
				if (_set->_set[i]) {
					_position = i;
					return;
				}
			}
		}

		bool element() {
			return _set->_set[_position];
		}


	};
	Iterator* createIterator();
};

