#include "BinaryTree.h"

BinaryTree::BinaryTree() {
	_tree = new std::pair<int,bool>[_size];
}

BinaryTree::BinaryTree(int value) {
	_tree = new std::pair<int, bool>[_size];
	addElement(value);
}

void BinaryTree::changeLength(int size) {
	std::pair<int, bool>* tempTree = new std::pair<int, bool>[size];
	for (int i = 0; i < _size; i++) {
		tempTree[i].first = _tree[i].first;
		tempTree[i].second = _tree[i].second;
	}
	_size = size;
	delete[]_tree;
	_tree = tempTree;
}

void BinaryTree::addElement(int value) {
	if (_counterOfElements == _size) {
		changeLength(_size * _MULTIPLIER);
	}
	/*for (int i = 0; i < _counterOfElements / 2; i++) {
		if (value == _tree[i])
			return;
		if (value < _tree[i]) {
			if (2 * (i + 1) < _size)
				changeLength(_size * _MULTIPLIER);

		}
	}*/
	if (_counterOfElements == 0) {
		_tree[0] = value;
	}
	addElement(value, 1);
}

void BinaryTree::addElement(int value, int index) {
	if (_tree[index - 1] == value) {
		return;
	}
	if (_tree[index - 1] > value) {
		if (index * 2 > _size) {
			changeLength(_size * _MULTIPLIER);
			_tree[(index - 1) * 2] = value;
			_counterOfElements++;
		}
		else {
			addElement(value, index * 2);//
		}
	}
	else {
		if (index * 2 + 1 > _size) {
			changeLength(_size * _MULTIPLIER);
			_tree[index * 2] = value;
			_counterOfElements++;
		}
		else {
			addElement(value, index * 2 + 1);
		}
	}
}

void BinaryTree::popElement(int value) {
	if (isEmpty())
		return;

}

void BinaryTree::popElement(int value, int index) {

}

int BinaryTree::getRoot() {
	return _tree[0];
}

bool BinaryTree::isEmpty() {
	return _counterOfElements == 0;
}